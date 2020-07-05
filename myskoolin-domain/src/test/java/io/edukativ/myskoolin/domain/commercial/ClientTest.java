package io.edukativ.myskoolin.domain.commercial;

import io.edukativ.myskoolin.domain.timetabling.TimeSlot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void defaultCoursesTimeSlots() {
        final List<TimeSlot> timeSlots = Client.defaultCoursesTimeSlots();
        Assertions.assertThat(timeSlots)
                .extracting(TimeSlot::getId)
                .doesNotHaveDuplicates();
    }
}
