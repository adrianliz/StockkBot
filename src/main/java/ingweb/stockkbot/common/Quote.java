package ingweb.stockkbot.common;

import com.google.gson.Gson;
import java.io.Serializable;

public class Quote implements Serializable {
  private String symbol;  // refers to the stock ticker.
  private double price;   // refers to last sale price of the stock on IEX
  private int size;       // refers to last sale size of the stock on IEX.
  private long time;      // refers to last sale time in epoch time of the stock 
                          //on IEX.

  private Long msRx = System.currentTimeMillis();

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public Long getMsRx() {
    return msRx;
  }

  public void setMsRx(Long msRx) {
    this.msRx = msRx;
  }

  public long howOldAmI() {
    long now = System.currentTimeMillis();
    return (now - msRx);
  }

  public String toJson() {
    return new Gson().toJson(this);
  }
}