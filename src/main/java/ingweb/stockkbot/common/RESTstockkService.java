package ingweb.stockkbot.common;

import com.google.gson.Gson;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RESTstockkService implements Serializable {
  private String name;
  private String uri;
  private StatusCode status = StatusCode.UNDEFINED;
  private Long ms;
  private Long howMsOld;

  public Long getHowMsOld() {
    return howMsOld;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public StatusCode getStatus() {
    return status;
  }

  public void setStatus(StatusCode status) {
    this.status = status;
  }

  public Long getMs() {
    return ms;
  }

  public void setMs(Long ms) {
    this.ms = ms;
  }

  public String toJson() {
    return new Gson().toJson(this);
  }
}