package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.vo.EnumPartsOfDay;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.Comparator;
import java.util.Objects;

public class Time implements Comparable<Time> {

    private Integer hour;
    private Integer minutes;
    private Integer seconds;
    private EnumPartsOfDay partOfDay;

    public Time() {
    }

    public Time(Integer hour, Integer minutes, Integer seconds, EnumPartsOfDay partOfDay) {
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
    public int compareTo(Time o) {
        return Comparator.comparing(Time::toLocalTime).compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Time time = (Time) o;
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
        return "Time{" +
                "hour=" + hour +
                ", minutes=" + minutes +
                ", partOfDay=" + partOfDay +
                '}';
    }

    public LocalTime toLocalTime() {
        return LocalTime.of(this.hour, this.minutes, this.seconds);
    }

    /**
     * @param time time must be lower than current time
     * @return duration in minutes
     */
    public long durationInMinutesFrom(Time time) {
        Temporal start = time.toLocalTime();
        Temporal end = this.toLocalTime();
        return Duration.between(start, end).toMinutes();
    }

    public Time endTimeFromStartTimeBySubject(int subjectMaxMinutesPerDay, int remainingMinutesToAdd) {
        //FIXME add max courses hour
        final LocalTime startTime = toLocalTime();
        LocalTime endLocalTime;
        if (remainingMinutesToAdd > subjectMaxMinutesPerDay) {
            endLocalTime = startTime.plusMinutes(subjectMaxMinutesPerDay);
        } else {
            endLocalTime = startTime.plusMinutes(remainingMinutesToAdd);
        }
        return new Time(endLocalTime.getHour(), endLocalTime.getMinute(), endLocalTime.getSecond(), EnumPartsOfDay.fromLocalTime(endLocalTime));
    }

    public Time addHours(int hours) {
        this.hour = this.hour + hours;
        return this;
    }

    public Instant toInstant(DayOfWeek day, ZonedDateTime now) {
        return now
                .with(ChronoField.DAY_OF_WEEK, day.getValue())
                .with(ChronoField.MINUTE_OF_HOUR, Long.valueOf(minutes))
                .with(ChronoField.SECOND_OF_MINUTE, Long.valueOf(seconds))
                .with(ChronoField.HOUR_OF_DAY, Long.valueOf(hour)).toInstant();
    }
}
