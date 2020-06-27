package io.edukativ.myskoolin.infrastructure.timetabling;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.vo.TimeDbVO;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

public class TimePeriodDbVO implements Serializable {

    public static final String MONGO_FIELD_DAY = "day";
    public static final String MONGO_FIELD_START = "start";
    public static final String MONGO_FIELD_END = "end";

    @Field(MONGO_FIELD_DAY)
    private String day;

    @Field(MONGO_FIELD_START)
    private TimeDbVO start;

    @Field(MONGO_FIELD_END)
    private TimeDbVO end;

    public TimePeriodDbVO() {
    }

    public TimePeriodDbVO(String day, TimeDbVO start, TimeDbVO end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public TimeDbVO getStart() {
        return start;
    }

    public void setStart(TimeDbVO start) {
        this.start = start;
    }

    public TimeDbVO getEnd() {
        return end;
    }

    public void setEnd(TimeDbVO end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePeriodDbVO that = (TimePeriodDbVO) o;
        return day.equals(that.day) &&
                start.equals(that.start) &&
                end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, start, end);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "TimePeriod{" +
                    "day='" + day + '\'' +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
