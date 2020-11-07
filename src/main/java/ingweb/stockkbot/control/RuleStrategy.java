package ingweb.stockkbot.control;

import ingweb.stockkbot.common.RESTrule;

public interface RuleStrategy {
  boolean triggerRule(RESTrule rule);
  void executeRule(RESTrule rule);
}
