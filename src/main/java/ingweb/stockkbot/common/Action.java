package ingweb.stockkbot.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum Action {
  UNDEFINED,
  SELL_HIGHER_THAN,
  SELL_LOWER_THAN,
  BUY_LOWER_THAN
}