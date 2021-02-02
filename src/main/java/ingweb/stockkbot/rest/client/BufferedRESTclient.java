/**
 * BufferedRESTclient
 * @author Félix Serna Fortea
 */

package ingweb.stockkbot.rest.client;

import ingweb.stockkbot.common.Quote;
import ingweb.stockkbot.common.RESTstockkService;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class BufferedRESTclient {
  private WebTarget webTarget;
  private Client client;

  public BufferedRESTclient(String serviceDirectoryBaseURI, 
                            String serviceName) {
    
    ServicesDirectoryRESTclient directoryService = 
      new ServicesDirectoryRESTclient(serviceDirectoryBaseURI);

    RESTstockkService bufferedService = directoryService.getService(serviceName);
    directoryService.close();

    if (bufferedService != null) {
      client = javax.ws.rs.client.ClientBuilder.newClient();
      webTarget = client.target(bufferedService.getUri()).path("stockk");
    }
  }

  public Quote[] getQuotes(String tickers) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("quotes/{0}", new Object[]{tickers}));
      
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(Quote[].class);
  }

  public String getIndexesCredits() throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path("indexesCredits");
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
  }

  public String getAvailableSymbols() throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path("availableSymbols");

    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
  }

  public Quote getQuote(String ticker) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("quote/{0}", new Object[]{ticker}));

    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(Quote.class);
  }

  public <T> T getAllIndexes(Class<T> responseType) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path("allIndexes");

    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
  }

  public Double getPrice(String ticker) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("price/{0}", new Object[]{ticker}));

    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(Double.class);
  }

  public <T> T getLastIndex(Class<T> responseType, String index) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("lastIndex/{0}", new Object[]{index}));
       
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
  }

  public <T> T getMsLastIndexesUpdate(Class<T> responseType) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path("msLastIndexesUpdate");
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
  }

  public <T> T getMsLastUpdate(Class<T> responseType) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path("msLastUpdate");
      
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
  }

  public String getAllIndex(String index) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("allIndex/{0}", new Object[]{index}));
  
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
  }

  public String getCredits() throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path("credits");
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
  }

  public String getIndexFromTo(String index, String from, String to) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("index/{0}/{1}/{2}", new Object[]{index, from, to}));
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
  }

  public String getAllIndexesFromTo(String from, String to) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("allIndexes/{0}/{1}", new Object[]{from, to}));
    
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
  }

  public void close() {
    client.close();
  }
}