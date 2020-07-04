package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.MyskoolinApp;
import io.edukativ.myskoolin.config.MySkoolinIntegrationTests;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MyskoolinApp.class)
@ActiveProfiles("it")
@AutoConfigureMockMvc
class TimetableResourceTest extends MySkoolinIntegrationTests {

    @Test
    void generateTimetableFromScratch() throws Exception {
        endPointCaller.authenticateAndPerform(
            Users.DEFAULT_ADMINISTRATION_LOGIN,
            Users.DEFAULT_ADMINISTRATION_PASSWORD,
            get(EndPoints.TIMETABLES_GENERATE_URL))
        .andExpect(status().isOk());
    }
}
