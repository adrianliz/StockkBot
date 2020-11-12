package ingweb.stockkbot.common;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class Config {
  private static final String CONFIG_FILE = "config.properties";
  
  public static final String STOCKK_BOT_NAME = "STOCKK_BOT_NAME";
  public static final String STOCKK_BOT_URI = "STOCKK_BOT_URI";
  public static final String STOCKK_MASTER_PWD = "STOCKK_MASTER_PWD";
  public static final String SERVICES_DIRECTORY_BASE_URI = 
    "SERVICES_DIRECTORY_BASE_URI";
  public static final String IDENTITY_SERVICE_NAME = "IDENTITY_SERVICE_NAME";
  public static final String BUFFERED_SERVICE_NAME = "BUFFERED_SERVICE_NAME";
  public static final String PORTFOLIO_SERVICE_NAME = "PORTFOLIO_SERVICE_NAME";
  public static final String USERS_RULES_DIRECTORY_NAME = 
    "USERS_RULES_DIRECTORY_NAME";
  public static final String USERS_RULES_FILE_PATH = "USERS_RULES_FILE_PATH";
 
  private static Config instance = null;
  private Configuration config;
  
  private Config() throws ConfigurationException {
    Configurations configs = new Configurations();
    config = configs.properties(new File(CONFIG_FILE));
  }
  
  public static synchronized Config getInstance() throws ConfigurationException {
    if (instance == null) {
      instance = new Config();
    }
    
    return instance;
  }
  
  public String getString(String clave) {
    return config.getString(clave);
  }
  
  public int getInt(String key) {
    return config.getInt(key);
  }
}
