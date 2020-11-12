package ingweb.stockkbot.control;

import ingweb.stockkbot.common.RESTrule;

class MockStrategy implements TriggerStrategy, ActionStrategy {
  @Override
  public boolean triggerRule(RESTrule rule, double currentTickerPrice) {
    return true;
  }
  
  @Override
  public void executeRule(RESTrule rule, String token) {
    System.out.println("I'm mock rule >.<");
  }
}