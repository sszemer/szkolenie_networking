package nbp;

import java.math.BigDecimal;

public class Rate {
    private String Currency;
    private String Code;
    private BigDecimal Mid;

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public BigDecimal getMid() {
        return Mid;
    }

    public void setMid(BigDecimal mid) {
        Mid = mid;
    }
}
