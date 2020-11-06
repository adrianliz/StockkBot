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
  TouchTimer timer;

  @PostConstruct
  public void init() {
    timer.touch(StatusCode.BOOTING);

    System.out.println("StockkBot is up!");
  }

  @PreDestroy
  public void down() {
    timer.touch(StatusCode.DOWN);
    
    System.out.println("StockkBot is down!");
  }
}
