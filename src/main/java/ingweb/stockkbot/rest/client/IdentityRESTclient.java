package ingweb.stockkbot.rest.client;

import ingweb.stockkbot.common.RESTstockkService;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class IdentityRESTclient {
  private WebTarget webTarget;
  private Client client;

  public IdentityRESTclient(String serviceDirectoryBaseURI, 
                            String serviceName) {

    ServicesDirectoryRESTclient directoryService = 
      new ServicesDirectoryRESTclient(serviceDirectoryBaseURI);
    
    RESTstockkService identityService = directoryService.getService(serviceName);
    directoryService.close();

    if (identityService != null) {
      client = javax.ws.rs.client.ClientBuilder.newClient();
      webTarget = client.target(identityService.getUri()).path("user");
    }
  }

  public <T> T getUser(Class<T> responseType, String token) 
    throws ClientErrorException {
    
    WebTarget resource = webTarget;
    resource = 
      resource.path(java.text.MessageFormat.format("{0}", new Object[]{token}));

    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
            .get(responseType);
  }

  public void createUser(Object requestEntity, String clearPassword) 
    throws ClientErrorException {
    
    webTarget.path(java.text.MessageFormat.format("{0}",
            new Object[]{clearPassword}))
            .request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
            .post(
                    javax.ws.rs.client.Entity.entity(
                            requestEntity,
                            javax.ws.rs.core.MediaType.APPLICATION_JSON));
  }

  public String changePassword(String token, String clearPassword) 
    throws ClientErrorException {
    
    return webTarget.path(java.text.MessageFormat.format("{0}/{1}",
            new Object[]{token, clearPassword}))
            .request().put(null, String.class);
  }

  public void close() {
    client.close();
  }
}