package ingweb.stockkbot.rest;

import ingweb.stockkbot.common.RESTrule;
import ingweb.stockkbot.persistence.RulesDAO;
import ingweb.stockkbot.persistence.RulesStore;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("rule")
@RequestScoped
public class StockkBotSerivce {
  @Context
  private UriInfo context;
  private RulesDAO rulesDAO;

  public StockkBotSerivce() {
    rulesDAO = RulesStore.getInstance();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<RESTrule> getRules() {
    return rulesDAO.getRules();
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{token}")
  public void addRule(@PathParam("token") String token, RESTrule rule) {
    rulesDAO.addRule(token, rule);
  }
}
