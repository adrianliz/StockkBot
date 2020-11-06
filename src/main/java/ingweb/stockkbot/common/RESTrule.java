package ingweb.stockkbot.common;

import com.google.gson.Gson;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RESTrule implements Serializable {
  private Long id;
  private String login;
  private String ticker;
  private Integer numShares;
  private Action whatToDo = Action.UNDEFINED;
  private Double triggerPrice;
  private Boolean enabled = false;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Integer getNumShares() {
    return numShares;
  }

  public void setNumShares(Integer numShares) {
    this.numShares = numShares;
  }

  public Action getWhatToDo() {
    return whatToDo;
  }

  public void setWhatToDo(Action action) {
    this.whatToDo = action;
  }

  public Double getTriggerPrice() {
    return triggerPrice;
  }

  public void setTriggerPrice(Double triggerPrice) {
    this.triggerPrice = triggerPrice;
  }

  public Boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public String toJson() {
    return new Gson().toJson(this);
  }
}
