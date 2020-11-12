package ingweb.stockkbot.control;

import ingweb.stockkbot.common.RESTrule;

public class TriggerHigherStrategy implements TriggerStrategy {
  @Override
  public boolean triggerRule(RESTrule rule, double currentTickerPrice) {
    return currentTickerPrice > rule.getTriggerPrice();
  }
}