package ingweb.stockkbot.control;

import ingweb.stockkbot.common.RESTrule;

interface ActionStrategy {
  void executeRule(RESTrule rule, String token);
}
