package ingweb.stockkbot.common;

import com.google.gson.Gson;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RESTuser implements Serializable {
  private String login;
  private String name;
  private byte[] publicKey;

  public RESTuser() {

  }

  public RESTuser(String login, String name, byte[] publicKey) {
    this.login = login;
    this.name = name;
    this.publicKey = publicKey;
  }

  public String getLogin() {
    return login;
  }

  public String getName() {
    return name;
  }

  public byte[] getPublicKey() {
    return publicKey;
  }

  public String toJson() {
    return new Gson().toJson(this);
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public void setPublicKey(byte[] publicKey) {
    this.publicKey = publicKey;
  }
}