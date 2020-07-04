package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.TimeTableApplication;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping(value = "/api/timetables")
@RestController
class TimetableResource {

    private final TimeTableApplication timeTableApplication;
    private final MyskoolinLoggerSPI logger;

    TimetableResource(TimeTableApplication timeTableApplication, MyskoolinLoggerSPI logger) {
        this.timeTableApplication = timeTableApplication;
        this.logger = logger;
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping(value = "/solve")
    public ResponseEntity<List<String>> generateTimetablesFromScratch() {
        logger.debug("generating timetables for all school classes");
        try {
            return ResponseEntity.ok(timeTableApplication.generateNewTimeTablesForSchoolClasses());
        } catch (ExecutionException | InterruptedException e) {
            logger.error("solving timetable error", e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping(value = "/solve/{id}")
    public ResponseEntity<String> generateTimetableFromScratch(@PathVariable(name = "id") String id) {
        logger.debug("generating timetables for all school classes");
        try {
            return ResponseEntity.ok(timeTableApplication.generateNewTimeTablesForSchoolClass(id));
        } catch (ExecutionException | InterruptedException e) {
            logger.error("solving timetable error", e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping(value = "/solve/status/{id}")
    public ResponseEntity<String> solverStatus(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(timeTableApplication.solverStatus(id));
    }

}
