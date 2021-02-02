/**
 * PortfolioRESTclient
 * @author Félix Serna Fortea
 */

package ingweb.stockkbot.rest.client;

import ingweb.stockkbot.common.RESTstockkService;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class PortfolioRESTclient {
  private WebTarget webTarget;
  private Client client;

  public PortfolioRESTclient(String serviceDirectoryBaseURI,
                             String serviceName) {
    
    ServicesDirectoryRESTclient directoryService = 
      new ServicesDirectoryRESTclient(serviceDirectoryBaseURI);
    
    RESTstockkService portfolioService = directoryService.getService(serviceName);
    directoryService.close();
    
    if (portfolioService != null) {
      client = javax.ws.rs.client.ClientBuilder.newClient();
      webTarget = client.target(portfolioService.getUri()).path("portfolio");
    }
  }

  public void deletePortfolio(String token) throws ClientErrorException {
    webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{token})).request().delete();
  }

  public <T> T getPortfolio(Class<T> responseType, String token) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{token}));
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
  }

  public void sellStock(Object requestEntity, String token) throws ClientErrorException {
    webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{token}))
            .request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
            .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
  }

  public void buyStock(Object requestEntity, String token) throws ClientErrorException {
    webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{token}))
            .request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
            .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
  }

  public void close() {
    client.close();
  }
}