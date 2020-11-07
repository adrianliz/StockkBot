package ingweb.stockkbot.control;

import ingweb.stockkbot.common.Config;
import ingweb.stockkbot.common.RESTrule;
import ingweb.stockkbot.persistence.RulesDAO;
import ingweb.stockkbot.persistence.RulesStore;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Bot {
  private static Bot instance = null;
  
  private Config config;
  private RulesDAO rulesDAO;
  private RuleStrategyFactory ruleStrategyFactory;
  private Timer timer;
  private long rulesExecuted;
  
  private Bot() {
    try {
      config = Config.getInstance();
      rulesDAO = RulesStore.getInstance();
      ruleStrategyFactory = RuleStrategyFactory.getInstance();
      rulesExecuted = 0;
      
      timer = new Timer(config.getInt(Config.DELAY_EXECUTING_RULES), (ActionEvent e) -> {
        executeRules();
      });
      
      timer.start();
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
  
  private void executeRules() {
    System.out.println("Executing bot rules...");
    
    for (RESTrule rule: rulesDAO.getRules()) {
      RuleStrategy ruleStrategy = 
        ruleStrategyFactory.makeRuleStrategy(rule.getWhatToDo());
      
      if (ruleStrategy.triggerRule(rule)) {
        ruleStrategy.executeRule(rule);
        rulesExecuted++;
      }
    }
    
    System.out.println("Bot rules executed!");
    System.out.println("Bot has executed " + rulesExecuted + " rules!");
  }
  
  public void stopTimer() {
    if (timer != null) {
      if (timer.isRunning()) {
        timer.stop();
      }
    }
  }
}