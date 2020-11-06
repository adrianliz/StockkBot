package ingweb.stockkbot.persistence;

import ingweb.stockkbot.common.Config;
import ingweb.stockkbot.common.RESTrule;
import ingweb.stockkbot.common.RESTuser;
import ingweb.stockkbot.common.RulesTuple;
import ingweb.stockkbot.rest.client.IdentityRESTclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RulesStore implements RulesDAO {
  private static RulesDAO instance = null;

  private Config config;
  private Map<String, List<RESTrule>> usersRules;
  private Gson gson;
  private long nextRuleID;

  private RulesStore() {
    try {
      config = Config.getInstance();
      usersRules = new HashMap<>();
      gson = new GsonBuilder().serializeNulls().create();
      nextRuleID = 0;
      
      load();
    } catch (ConfigurationException ex) {
      System.err.println("Error: can't load config file");
      ex.printStackTrace(System.err);
    } catch (FileNotFoundException ex) {
      System.err.println("Error: can't load rule's file");
      ex.printStackTrace(System.err);
    }
  }

  public static synchronized RulesDAO getInstance() {
    if (instance == null) {
      instance = new RulesStore();
    }

    return instance;
  }

  private void load() throws FileNotFoundException {
    File file = new File(config.getString(Config.USERS_RULES_FILE_PATH));

    if (! file.exists()) {
      File fd = new File(config.getString(Config.USERS_RULES_DIRECTORY_NAME));

      if (! fd.exists()) {
        fd.mkdir();
      }

      save();
    } else {
      Type type = 
        new TypeToken<RulesTuple<Map<String, List<RESTrule>>, Long>>(){}.getType();

      RulesTuple rulesTuple = gson.fromJson(new FileReader(file), type);
      usersRules = (Map<String, List<RESTrule>>) rulesTuple.usersRules;
      nextRuleID = (Long) rulesTuple.nextRuleID;
    }
  }

  private void save() {
    try {
      Writer writer
        = new FileWriter(config.getString(Config.USERS_RULES_FILE_PATH));

      RulesTuple rulesTuple = new RulesTuple(usersRules, nextRuleID);
      gson.toJson(rulesTuple, writer);

      writer.flush();
      writer.close();
    } catch (IOException ex) {
      System.err.println("Error: can't save rule's file");
      ex.printStackTrace(System.err);
    }
  }

  @Override
  public synchronized List<RESTrule> getRules() {
    return
      usersRules.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
  }

  @Override
  public List<RESTrule> getRulesFromUser(String token) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public RESTrule getRuleFromUser(String token, long idRule) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public synchronized void addRule(String token, RESTrule rule) {
    IdentityRESTclient identityService
            = new IdentityRESTclient(
                    config.getString(Config.SERVICES_DIRECTORY_BASE_URI),
                    config.getString(Config.IDENTITY_SERVICE_NAME));

    RESTuser user = identityService.getUser(RESTuser.class, token);
    identityService.close();

    if ((user != null) && (rule != null)) {
      String login = user.getLogin();

      if (login != null) {
        List<RESTrule> userRules = usersRules.get(login);
        
        if (userRules == null) {
          userRules = new ArrayList();
        }
        
        rule.setLogin(login);
        rule.setId(nextRuleID++);
        userRules.add(rule);
        usersRules.put(login, userRules);
                
        save();
      }
    }
  }

  @Override
  public void editRule(String token, RESTrule rule) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void deleteRule(String token, long idRule) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
