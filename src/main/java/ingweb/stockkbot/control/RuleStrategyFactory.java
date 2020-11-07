package ingweb.stockkbot.control;

import ingweb.stockkbot.common.Action;

public class RuleStrategyFactory {
  private static RuleStrategyFactory instance = null;
  private DefaultRuleStrategy defaultStrategy;
  
  private RuleStrategyFactory() {
    defaultStrategy = new DefaultRuleStrategy();
  };
  
  protected static synchronized RuleStrategyFactory getInstance() {
    if (instance == null) {
      instance = new RuleStrategyFactory();
    }
    
    return instance;
  }
  
  protected RuleStrategy makeRuleStrategy(Action action) {
    return defaultStrategy;
  }
}