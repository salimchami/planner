package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.MyskoolinApp;
import io.edukativ.myskoolin.config.MySkoolinIntegrationTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Duration;
import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = MyskoolinApp.class)
@ActiveProfiles("it")
@AutoConfigureMockMvc
class TimetableResourceTest extends MySkoolinIntegrationTests {

    @Test
    void generateTimetableFromScratch() throws Exception {
        Instant start = Instant.now();
        Instant end = start.plusSeconds(10);
        final MvcResult mvcResult = endPointCaller.authenticateAndPerform(
            Users.DEFAULT_ADMINISTRATION_LOGIN,
            Users.DEFAULT_ADMINISTRATION_PASSWORD,
            get(EndPoints.TIMETABLES_GENERATE_URL + "/1002d38a7407395bcf9ef411")).andReturn();
        while (true) {
            final Duration between = Duration.between(start, end);
            if (between.getSeconds() >= 10) {
                break;
            }
        }
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }
}
