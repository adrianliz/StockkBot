/**
 * BotTimer
 * @author Borja Rando Jarque, Adrián Lizaga Isaac
 */

package ingweb.stockkbot.ejb;

import ingweb.stockkbot.control.Bot;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class BotTimer {  
  @Schedule(hour = "*", minute = "*", second = "59", persistent = false)
  public void timer() {
    Bot.getInstance().executeRules();
  }
}