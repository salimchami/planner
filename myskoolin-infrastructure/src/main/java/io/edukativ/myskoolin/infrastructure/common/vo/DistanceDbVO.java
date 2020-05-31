package io.edukativ.myskoolin.infrastructure.common.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumDistances;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class DistanceDbVO implements Serializable {

    public static final String MONGO_FIELD_VALUE = "value";
    public static final String MONGO_FIELD_TYPE = "type";
    @Field(MONGO_FIELD_VALUE)
    private BigDecimal value;
    /**
     * Distance type. See {@link EnumDistances}
     */
    @Field(MONGO_FIELD_TYPE)
    private String type;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Distance type. See {@link EnumDistances}
     *
     * @return distance type
     */
    public String getType() {
        return type;
    }

    /**
     * Distance type. See {@link EnumDistances}
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceDbVO distance = (DistanceDbVO) o;
        return value.equals(distance.value) &&
                type.equals(distance.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Distance{" +
                    "value=" + value +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
