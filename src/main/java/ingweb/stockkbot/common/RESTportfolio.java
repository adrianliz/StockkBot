/**
 * RESTportfolio
 * @author Félix Serna Fortea
 */

package ingweb.stockkbot.common;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RESTportfolio implements Serializable {
  private String login;
  private List<RESTstock> stocks;

  public RESTportfolio() {
    this.stocks = new ArrayList();
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setStocks(List<RESTstock> stocks) {
    this.stocks = stocks;
  }

  public void addStock(RESTstock stock) {
    this.stocks.add(stock);
  }

  public void removeStock(RESTstock stock) {
    this.stocks.remove(stock);
  }

  public RESTstock getStock(int id) {
    return this.stocks.get(id);
  }

  public Collection<RESTstock> getStocks() {
    return this.stocks;
  }
  
  public List<RESTstock> getStocksTicker(String ticker) {
    List<RESTstock> stocksTicker = new ArrayList<>();
    
    for (RESTstock stock: stocks) {
      if (stock.getTicker().equals(ticker)) {
        stocksTicker.add(stock);
      }
    }
    
    return stocksTicker;
  }

  public String toJson() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}