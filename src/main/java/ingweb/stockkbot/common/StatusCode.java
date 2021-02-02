/**
 * StatusCode
 * @author Félix Serna Fortea
 */

package ingweb.stockkbot.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum StatusCode {
  UNDEFINED,
  NOT_REGISTERED,
  DOWN,
  OUTDATED,
  NOMINAL,
  REGISTERED,
  BOOTING,
  DEGRADED;
}