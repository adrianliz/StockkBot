package ingweb.stockkbot.control;

import ingweb.stockkbot.common.Config;
import ingweb.stockkbot.common.RESTrule;
import ingweb.stockkbot.common.RESTstock;
import ingweb.stockkbot.rest.client.PortfolioRESTclient;

class BuyStrategy implements ActionStrategy {
  private Config config;
  
  protected BuyStrategy(Config config) {
    this.config = config;
  }

  @Override
  public void executeRule(RESTrule rule, String token) {    
    if (token != null) {
      PortfolioRESTclient portfolioService =
        new PortfolioRESTclient(config.getString(Config.SERVICES_DIRECTORY_BASE_URI), 
                                config.getString(Config.PORTFOLIO_SERVICE_NAME));
      
      RESTstock stock = new RESTstock();
      stock.setTicker(rule.getTicker());
      stock.setNumShares(rule.getNumShares());
      
      portfolioService.buyStock(stock, token);
      
      portfolioService.close();
    }
  }
}