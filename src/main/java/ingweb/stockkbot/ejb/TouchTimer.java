package ingweb.stockkbot.ejb;

import ingweb.stockkbot.common.Config;
import ingweb.stockkbot.common.StatusCode;
import ingweb.stockkbot.rest.client.ServicesDirectoryRESTclient;
import ingweb.stockkbot.common.RESTstockkService;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ws.rs.ClientErrorException;

@Stateless
public class TouchTimer {
  @Schedule(hour = "*", minute = "*", second = "22", persistent = false)
  public void timer() {
    touch(StatusCode.NOMINAL);
  }

  public void touch(StatusCode status) {
    try {
      Config config = Config.getInstance();

      RESTstockkService service = new RESTstockkService();
      service.setName(config.getString(Config.STOCKK_BOT_NAME));
      service.setUri(config.getString(Config.STOCKK_BOT_URI));
      service.setStatus(status);
      service.setMs(System.currentTimeMillis());

      try {
        ServicesDirectoryRESTclient directoryService = 
          new ServicesDirectoryRESTclient(
            config.getString(Config.SERVICES_DIRECTORY_BASE_URI));
        
        directoryService.registerService(service);
        directoryService.close();
      } catch (ClientErrorException ex) {
        System.err.println("Error: can't register service in services directory");
        ex.printStackTrace(System.err);
      }
    } catch (ConfigurationException ex) {
      System.err.println("Error: can't load config file");
      ex.printStackTrace(System.err);
    }
  }
}