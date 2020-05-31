package io.edukativ.myskoolin.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class Distance {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return value.equals(distance.value) &&
                type.equals(distance.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }
}
