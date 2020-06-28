package io.edukativ.myskoolin.infrastructure.delays;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.edukativ.myskoolin.infrastructure.timetabling.LessonDbVO;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class DelayDbVO implements Serializable {

    private Instant arrivingTime;
    private LessonDbVO lesson;

    public Instant getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Instant arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public LessonDbVO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDbVO lesson) {
        this.lesson = lesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DelayDbVO delay = (DelayDbVO) o;
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
