package io.edukativ.myskoolin.infrastructure.schooling.vo;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class DelayVO implements Serializable {

    private Instant arrivingTime;
    private SchoolClassTimeSlotVO timeslot;

    public Instant getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Instant arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public SchoolClassTimeSlotVO getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(SchoolClassTimeSlotVO timeslot) {
        this.timeslot = timeslot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DelayVO delay = (DelayVO) o;
        return arrivingTime.equals(delay.arrivingTime) &&
                Objects.equals(timeslot, delay.timeslot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivingTime, timeslot);
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {

            return "Delay{" +
                    "arrivingTime=" + arrivingTime +
                    ", timeslot=" + timeslot +
                    '}';
        }
    }
}
