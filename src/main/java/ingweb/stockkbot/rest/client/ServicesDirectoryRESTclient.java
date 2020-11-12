package ingweb.stockkbot.rest.client;

import ingweb.stockkbot.common.RESTstockkService;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class ServicesDirectoryRESTclient {
  private WebTarget webTarget;
  private Client client;
    
  public ServicesDirectoryRESTclient(String baseURI) {
    client = javax.ws.rs.client.ClientBuilder.newClient();
    webTarget = client.target(baseURI).path("service");
  }

  public RESTstockkService getService(String serviceName) 
    throws ClientErrorException {
    
    WebTarget resource = webTarget;
    resource = resource.path(serviceName);
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
            .get(RESTstockkService.class);
  }

  public RESTstockkService getServices() throws ClientErrorException {
    WebTarget resource = webTarget;
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
            .get(RESTstockkService.class);
  }

  public String registerService(RESTstockkService service) 
    throws ClientErrorException {
    
    WebTarget resource = webTarget;
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
            .post(javax.ws.rs.client.Entity.entity(service, 
                  javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
  }

  public void close() {
    client.close();
  }
}