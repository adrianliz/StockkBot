package ingweb.stockkbot.common;

public class RulesTuple<A, B> {
  public final A usersRules;
  public final B nextRuleID;
  
  public RulesTuple(A a, B b) {
    this.usersRules = a;
    this.nextRuleID = b;
  }
}