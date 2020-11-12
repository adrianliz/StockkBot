package ingweb.stockkbot.control;

import ingweb.stockkbot.common.Action;
import ingweb.stockkbot.common.Config;
import ingweb.stockkbot.common.RESTrule;
import ingweb.stockkbot.persistence.RulesDAO;
import ingweb.stockkbot.persistence.RulesStore;
import ingweb.stockkbot.rest.client.BufferedRESTclient;
import ingweb.stockkbot.rest.client.TokenRESTclient;

import org.apache.commons.configuration2.ex.ConfigurationException;

public class Bot {
  private static Bot instance = null;
  
  private Config config;
  private RulesDAO rulesDAO;
  private StrategyFactory strategyFactory;
  private long rulesExecuted;
  
  private Bot() {
    try {
      config = Config.getInstance();
      rulesDAO = RulesStore.getInstance();
      strategyFactory = StrategyFactory.getInstance(config);
      rulesExecuted = 0;
    } catch (ConfigurationException ex) {
      System.err.println("Error: can't load config file");
      ex.printStackTrace(System.err);      
    }
  }
  
  public static synchronized Bot getInstance() {
    if (instance == null) {
      instance = new Bot();
    }

    return instance;
  }

  public synchronized void executeRules() {
    System.out.println("--------Executing bot rules--------");
    
    for (RESTrule rule: rulesDAO.getRules()) {
      Action action = rule.getWhatToDo();
      
      if ((action != null) && (rule.isEnabled())) {
        TriggerStrategy triggerStrategy = strategyFactory.makeTrigger(action);
        
        if (triggerStrategy.triggerRule(rule, getPriceTicker(rule.getTicker()))) {
          System.out.println("Executing " + action + " rule");
          ActionStrategy actionStrategy = strategyFactory.makeAction(action);
          
          actionStrategy.executeRule(rule, getToken(rule.getLogin()));
          
          rule.setEnabled(false);
          rulesDAO.editRule(getToken(rule.getLogin()), rule);
          rulesExecuted++;
        }
      }
    }
    
    System.out.println("--------Bot rules executed--------");
    System.out.println("--------Bot has executed " + rulesExecuted + 
                       " rules in total--------");
  }
  
  private String getToken(String login) {
    TokenRESTclient tokenService =
      new TokenRESTclient(config.getString(Config.SERVICES_DIRECTORY_BASE_URI), 
                          config.getString(Config.IDENTITY_SERVICE_NAME));
    String token = 
      tokenService.getToken(login, config.getString(Config.STOCKK_MASTER_PWD));
    tokenService.close();
    
    return token;
  }
  
  private double getPriceTicker(String ticker) {
    BufferedRESTclient bufferedService = 
      new BufferedRESTclient(config.getString(Config.SERVICES_DIRECTORY_BASE_URI),
                             config.getString(Config.BUFFERED_SERVICE_NAME));
    double price = bufferedService.getPrice(ticker);
    bufferedService.close();
    
    return price;
  }
}