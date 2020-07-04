package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.MyskoolinApp;
import io.edukativ.myskoolin.config.MySkoolinIntegrationTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

import static io.edukativ.myskoolin.config.JsonReader.toExpectedJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MyskoolinApp.class)
@ActiveProfiles("it")
@AutoConfigureMockMvc
class TimetableResourceTest extends MySkoolinIntegrationTests {

    @Test
    @Timeout(600_000)
    void generateTimetableFromScratch() throws Exception {
        String expectedSchoolClassTimeTable = toExpectedJson(TestsFolders.TIMETABLES_FOLDER, "school_class_timetable");
        final MvcResult mvcResult = endPointCaller.authenticateAndPerform(
            Users.DEFAULT_ADMINISTRATION_LOGIN,
            Users.DEFAULT_ADMINISTRATION_PASSWORD,
            get(EndPoints.TIMETABLES_SOLVE_URL + "/1002d38a7407395bcf9ef411"))
            .andExpect(status().isOk()).andReturn();
        String schoolClassTimeTableId = mvcResult.getResponse().getContentAsString();

        while (true) {
            final String anObject = solverStatus(schoolClassTimeTableId);
            if (SolverStatus.NOT_SOLVING.name().equals(anObject)) {
                break;
            }
            // Quick polling (not a Test Thread Sleep anti-pattern)
            // Test is still fast on fast machines and doesn't randomly fail on slow machines.
            Thread.sleep(20L);
//            getTimeTable();
            System.out.println("oh nooooooo !!!!");
        }
//        assertFalse(timeTable.getLessonList().isEmpty());
//        for (Lesson lesson : timeTable.getLessonList()) {
//            assertNotNull(lesson.getTimeslot());
//            assertNotNull(lesson.getRoom());
//        }
//        assertTrue(timeTable.getScore().isFeasible());

    }

    private String solverStatus(String schoolClassTimeTableId) throws Exception {
        final MvcResult mvcResult = endPointCaller.authenticateAndPerform(Users.DEFAULT_ADMINISTRATION_LOGIN,
            Users.DEFAULT_ADMINISTRATION_PASSWORD,
            get(EndPoints.TIMETABLES_SOLVER_STATUS_URL + "/" + schoolClassTimeTableId))
            .andExpect(status().isOk())
            .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

//    private void waitTimeTableSolverEnds(Instant start) {
//        while (true) {
//            Instant now = Instant.now();
//            final Duration between = Duration.between(start, now);
//            System.out.println(between.getSeconds());
//            if (between.getSeconds() >= 30) {
//                break;
//            }
//        }
//    }
}
