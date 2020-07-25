package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.config.MySkoolinIntegrationTests;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import static io.edukativ.myskoolin.config.JsonReader.toExpectedJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TimetableResourceIT extends MySkoolinIntegrationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @Timeout(600_000)
    @Disabled
    void generateTimetableFromScratch() throws Exception {
        String schoolClassId = "1002d38a7407395bcf9ef411";
        endPointCaller.authenticateAndPerform(
            Users.DEFAULT_ADMINISTRATION_LOGIN,
            Users.DEFAULT_ADMINISTRATION_PASSWORD,
            get(EndPoints.TIMETABLES_SOLVE_URL + "/" + schoolClassId))
            .andExpect(status().isOk());
        waitForSolver(schoolClassId);
        callForAndCheckTimeTable(schoolClassId);
    }

    private void callForAndCheckTimeTable(String schoolClassId) throws Exception {
        String expectedSchoolClassTimeTable = toExpectedJson(TestsFolders.TIMETABLES_FOLDER, "school_class_timetable");
        endPointCaller.authenticateAndPerform(Users.DEFAULT_ADMINISTRATION_LOGIN,
            Users.DEFAULT_ADMINISTRATION_PASSWORD,
            get(EndPoints.TIMETABLES_BASE_URL + "/" + schoolClassId))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedSchoolClassTimeTable));


    }

    private void waitForSolver(String schoolClassTimeTableId) throws Exception {
        while (true) {
            System.out.println("Waiting...");
            final String anObject = solverStatus(schoolClassTimeTableId);
            if (SolverStatus.NOT_SOLVING.name().equals(anObject)) {
                break;
            }
            Thread.sleep(20L);
        }
    }

    private String solverStatus(String schoolClassTimeTableId) throws Exception {
        return endPointCaller.authenticateAndPerform(Users.DEFAULT_ADMINISTRATION_LOGIN,
            Users.DEFAULT_ADMINISTRATION_PASSWORD,
            get(EndPoints.TIMETABLES_SOLVER_STATUS_URL + "/" + schoolClassTimeTableId))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
    }

}
