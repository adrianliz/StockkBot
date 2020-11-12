package ingweb.stockkbot.common;

import com.google.gson.Gson;

import java.io.Serializable;

public class RESTstock implements Serializable {
  private String ticker;
  private long msBuy;
  private double priceBuy = 0.0; // price when it was bought
  private int numShares = 0;
  private double priceSold = 0.0;
  private long msSold = 0;
  private double feeBuy = 0.0;
  private double feeSold = 0.0;

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public long getMsBuy() {
    return msBuy;
  }

  public void setMsBuy(long msBuy) {
    this.msBuy = msBuy;
  }

  public double getPriceBuy() {
    return priceBuy;
  }

  public void setPriceBuy(double priceBuy) {
    this.priceBuy = priceBuy;
  }

  public int getNumShares() {
    return numShares;
  }

  public void setNumShares(int numShares) {
    this.numShares = numShares;
  }

  public double getPriceSold() {
    return priceSold;
  }

  public void setPriceSold(double priceSold) {
    this.priceSold = priceSold;
  }

  public long getMsSold() {
    return msSold;
  }

  public void setMsSold(long msSold) {
    this.msSold = msSold;
  }

  public double getFeeBuy() {
    return feeBuy;
  }

  public void setFeeBuy(double feeBuy) {
    this.feeBuy = feeBuy;
  }

  public double getFeeSold() {
    return feeSold;
  }

  public void setFeeSold(double feeSold) {
    this.feeSold = feeSold;
  }

  public String toJson() {
    return new Gson().toJson(this);
  }
}