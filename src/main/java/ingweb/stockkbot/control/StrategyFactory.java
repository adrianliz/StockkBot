package ingweb.stockkbot.control;

import ingweb.stockkbot.common.Action;
import ingweb.stockkbot.common.Config;
import java.util.EnumMap;
import java.util.Map;

class StrategyFactory {
  private static StrategyFactory instance = null;
  private Map<Action, ActionStrategy> actionStrategies;
  private Map<Action, TriggerStrategy> triggerStrategies;
         
  private StrategyFactory(Config config) {
    actionStrategies = new EnumMap<>(Action.class);
    triggerStrategies = new EnumMap<>(Action.class);
    
    MockStrategy mockStrategy = new MockStrategy();
    
    SellStrategy sellStrategy = new SellStrategy(config);
    BuyStrategy buyStrategy = new BuyStrategy(config);
    actionStrategies.put(Action.UNDEFINED, mockStrategy);
    actionStrategies.put(Action.SELL_HIGHER_THAN, sellStrategy);
    actionStrategies.put(Action.SELL_LOWER_THAN, sellStrategy);
    actionStrategies.put(Action.BUY_LOWER_THAN, buyStrategy);
    
    TriggerHigherStrategy higherThanStrategy = new TriggerHigherStrategy();
    TriggerLowerStrategy lowerThanStrategy = new TriggerLowerStrategy();
    triggerStrategies.put(Action.UNDEFINED, mockStrategy);
    triggerStrategies.put(Action.SELL_HIGHER_THAN, higherThanStrategy);
    triggerStrategies.put(Action.SELL_LOWER_THAN, lowerThanStrategy);
    triggerStrategies.put(Action.BUY_LOWER_THAN, lowerThanStrategy);
  };
  
  static synchronized StrategyFactory getInstance(Config config) {
    if (instance == null) {
      instance = new StrategyFactory(config);
    }
    
    return instance;
  }
  
  TriggerStrategy makeTrigger(Action action) {
    return triggerStrategies.get(action);
  }
  
  ActionStrategy makeRuleAction(Action action) {
    return actionStrategies.get(action);
  }
}