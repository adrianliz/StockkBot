package ingweb.stockkbot.control;

import ingweb.stockkbot.common.Config;
import ingweb.stockkbot.common.RESTportfolio;
import ingweb.stockkbot.common.RESTrule;
import ingweb.stockkbot.common.RESTstock;
import ingweb.stockkbot.rest.client.PortfolioRESTclient;

public class SellStrategy implements ActionStrategy {
  private Config config;
  
  protected SellStrategy(Config config) {
    this.config = config;
  }

  @Override
  public void executeRule(RESTrule rule, String token) {
    if (token != null) {
      PortfolioRESTclient portfolioService =
        new PortfolioRESTclient(config.getString(Config.SERVICES_DIRECTORY_BASE_URI), 
                                config.getString(Config.PORTFOLIO_SERVICE_NAME));

      RESTportfolio portfolio = 
        portfolioService.getPortfolio(RESTportfolio.class, token);

      if (portfolio != null) {
        for (RESTstock stock: portfolio.getStocksTicker(rule.getTicker())) {
          if (rule.getNumShares() == stock.getNumShares()) {
            portfolioService.sellStock(stock, token);
            break;
          }
        }
      }
      portfolioService.close();
    }
  }
}