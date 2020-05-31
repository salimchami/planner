package io.edukativ.myskoolin.infrastructure.common.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumPartsOfDay;
import io.edukativ.myskoolin.infrastructure.schooling.dto.SubjectDbDTO;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Objects;

public class TimeDbVO implements Serializable {

    public static final String MONGO_FIELD_NAME_HOUR = "hour";
    public static final String MONGO_FIELD_NAME_MINUTES = "minutes";
    public static final String MONGO_FIELD_NAME_SECONDS = "seconds";
    public static final String MONGO_FIELD_NAME_PART_OF_DAY = "part_of_day";

    @Field(MONGO_FIELD_NAME_HOUR)
    @NotNull
    private Integer hour;

    @Field(MONGO_FIELD_NAME_MINUTES)
    @NotNull
    private Integer minutes;

    @Field(MONGO_FIELD_NAME_SECONDS)
    private Integer seconds;

    @Field(MONGO_FIELD_NAME_PART_OF_DAY)
    private EnumPartsOfDay partOfDay;

    public TimeDbVO() {
    }

    public TimeDbVO(Integer hour, Integer minutes, Integer seconds, EnumPartsOfDay partOfDay) {
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
        this.partOfDay = partOfDay;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public EnumPartsOfDay getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(EnumPartsOfDay partOfDay) {
        this.partOfDay = partOfDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeDbVO time = (TimeDbVO) o;
        return hour.equals(time.hour) &&
                minutes.equals(time.minutes) &&
                partOfDay == time.partOfDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minutes, seconds, partOfDay);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Time{" +
                    "hour='" + hour + '\'' +
                    ", minutes='" + minutes + '\'' +
                    ", seconds='" + seconds + '\'' +
                    ", partOfDay=" + partOfDay +
                    '}';
        }
    }

    /**
     * @param time time must be lower than current time
     * @return duration in minutes
     */
    public long durationInMinutesFrom(TimeDbVO time) {
        Temporal start = time.toLocalTime();
        Temporal end = this.toLocalTime();
        return Duration.between(start, end).toMinutes();
    }

    public LocalTime toLocalTime() {
        return LocalTime.of(this.hour, this.minutes, this.seconds);
    }

    public TimeDbVO endTimeFromStartTimeBySubject(SubjectDbDTO subject, int remainingMinutesToAdd) {
        final LocalTime startTime = toLocalTime();
        LocalTime endLocalTime;
        if (remainingMinutesToAdd > subject.getMaxMinutesPerDay()) {
            endLocalTime = startTime.plusMinutes(subject.getMaxMinutesPerDay());
        } else {
            endLocalTime = startTime.plusMinutes(remainingMinutesToAdd);
        }
        return new TimeDbVO(endLocalTime.getHour(), endLocalTime.getMinute(), endLocalTime.getSecond(), EnumPartsOfDay.fromLocalTime(endLocalTime));
    }
}
