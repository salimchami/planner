package io.edukativ.myskoolin.domain.delays;

import io.edukativ.myskoolin.domain.timetabling.SchoolClassTimeSlot;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class Delay implements Serializable {

    private Instant arrivingTime;
    private SchoolClassTimeSlot timeslot;

    public Instant getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Instant arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public SchoolClassTimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(SchoolClassTimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delay delay = (Delay) o;
        return arrivingTime.equals(delay.arrivingTime) &&
                Objects.equals(timeslot, delay.timeslot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivingTime, timeslot);
    }

    @Override
    public String toString() {
        return "Delay{" +
                "arrivingTime=" + arrivingTime +
                ", timeslot=" + timeslot +
                '}';
    }
}
