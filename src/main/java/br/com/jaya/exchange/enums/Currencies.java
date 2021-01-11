
package br.com.jaya.exchange.enums;

public enum Currencies {

     BRL(986), EUR(978), JPY(392), USD(840);

     private Integer code;

     Currencies(Integer code) {

          this.code = code;
     }

     public Integer getCode() {

          return this.code;
     }

     public static Currencies getEnum(Integer currencyCode) {

          Currencies currency = null;

          for (Currencies c : Currencies.values()) {
               if (c.code != null && c.code.equals(currencyCode)) {
                    currency = c;
                    break;
               }
          }
          return currency;
     }
}
