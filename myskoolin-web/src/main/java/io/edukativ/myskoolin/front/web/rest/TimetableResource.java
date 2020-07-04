package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.TimeTableApplication;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Void> solveTimetablesFromScratch() {
        logger.debug("generating timetables for all school classes");
        try {
            timeTableApplication.solveNewTimeTablesForSchoolClasses();
            return ResponseEntity.ok().build();
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
    public ResponseEntity<Void> solveTimetableFromScratch(@PathVariable(name = "id") String id) {
        logger.debug("generating timetables for all school classes");
        try {
            timeTableApplication.solveNewTimeTablesForSchoolClass(id);
            return ResponseEntity.ok().build();
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

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<SchoolClassTimeTableDTO> schoolClassTimeTable(@PathVariable(name = "id") String id) {
        return timeTableApplication.timeTableById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }
}
