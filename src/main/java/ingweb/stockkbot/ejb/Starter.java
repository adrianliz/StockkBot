/**
 * Starter
 * @author Borja Rando Jarque, Adrián Lizaga Isaac
 */

package ingweb.stockkbot.ejb;

import ingweb.stockkbot.common.StatusCode;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Starter {
  @EJB
  TouchTimer touchTimer;
  @EJB
  BotTimer botTimer;
  
  @PostConstruct
  public void init() {
    touchTimer.touch(StatusCode.BOOTING);

    System.out.println("--------StockkBot is up--------");
  }

  @PreDestroy
  public void down() {
    touchTimer.touch(StatusCode.DOWN);
    
    System.out.println("--------StockkBot is down--------");
  }
}