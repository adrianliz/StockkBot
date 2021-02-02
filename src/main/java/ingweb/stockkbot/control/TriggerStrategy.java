/**
 * TriggerStrategy
 * @author Borja Rando Jarque
 */

package ingweb.stockkbot.control;

import ingweb.stockkbot.common.RESTrule;

interface TriggerStrategy {
  boolean triggerRule(RESTrule rule, double currentTickerPrice);
}