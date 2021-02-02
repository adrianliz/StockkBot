/**
 * ActionStrategy
 * @author Borja Rando Jarque
 */

package ingweb.stockkbot.control;

import ingweb.stockkbot.common.RESTrule;

import javax.ws.rs.ClientErrorException;

interface ActionStrategy {
  void executeRule(RESTrule rule, String token) throws ClientErrorException;
}