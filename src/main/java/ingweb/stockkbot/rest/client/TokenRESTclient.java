package ingweb.stockkbot.rest.client;

import ingweb.stockkbot.common.RESTstockkService;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class TokenRESTclient {
  private WebTarget webTarget;
  private Client client;

  public TokenRESTclient(String serviceDirectoryBaseURI, String serviceName) {
    ServicesDirectoryRESTclient directoryService = 
      new ServicesDirectoryRESTclient(serviceDirectoryBaseURI);
    
    RESTstockkService tokenService = directoryService.getService(serviceName);
    directoryService.close();
    
    if (tokenService != null) {
      client = javax.ws.rs.client.ClientBuilder.newClient();
      webTarget = client.target(tokenService.getUri()).path("token");
    }
  }

  public String getToken(String login, String cleanPassword) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{login, cleanPassword}));

    System.out.println(resource);
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
  }

  public void cancelToken(String token) throws ClientErrorException {
    webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{token})).request().delete();
  }

  public void close() {
    client.close();
  }
}