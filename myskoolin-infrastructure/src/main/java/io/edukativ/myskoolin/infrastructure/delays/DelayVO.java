package io.edukativ.myskoolin.infrastructure.delays;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.timetabling.LessonVO;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class DelayVO implements Serializable {

    private Instant arrivingTime;
    private LessonVO lesson;

    public Instant getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Instant arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public LessonVO getLesson() {
        return lesson;
    }

    public void setLesson(LessonVO lesson) {
        this.lesson = lesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DelayVO delay = (DelayVO) o;
        return arrivingTime.equals(delay.arrivingTime) &&
                Objects.equals(lesson, delay.lesson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivingTime, lesson);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Delay{" +
                    "arrivingTime=" + arrivingTime +
                    ", timeslot=" + lesson +
                    '}';
        }
    }
}
