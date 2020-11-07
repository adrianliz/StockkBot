package ingweb.stockkbot.control;

import ingweb.stockkbot.common.RESTrule;

public class DefaultRuleStrategy implements RuleStrategy {
  @Override
  public boolean triggerRule(RESTrule rule) {
    return true;
  }

  @Override
  public void executeRule(RESTrule rule) {
    System.out.println("I don't do nothing ;)");
  }
}