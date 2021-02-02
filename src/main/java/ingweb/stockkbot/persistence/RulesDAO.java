/**
 * RulesDAO
 * @author Adrián Lizaga Isaac
 */

package ingweb.stockkbot.persistence;

import ingweb.stockkbot.common.RESTrule;

import java.util.List;

public interface RulesDAO {
  List<RESTrule> getRules();
  List<RESTrule> getRulesFromUser(String token);
  RESTrule getRuleFromUser(String token, long idRule);
  void addRule(String token, RESTrule rule);
  void editRule(String token, RESTrule rule);
  void deleteRule(String token, long idRule);
}
