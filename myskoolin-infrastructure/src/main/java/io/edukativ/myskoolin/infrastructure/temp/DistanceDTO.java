package io.edukativ.myskoolin.infrastructure.temp;

import java.io.Serializable;
import java.math.BigDecimal;

public class DistanceDTO implements Serializable {

    private BigDecimal value;
    private String type;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
