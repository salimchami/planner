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
    @GetMapping(value = "/generate")
    public ResponseEntity<List<String>> generateTimetablesFromScratch() {
        logger.debug("generating timetables for all school classes");
        final List<String> timeTablesIds = timeTableApplication.generateNewTimeTablesForSchoolClasses();
        return ResponseEntity.ok(timeTablesIds);
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping(value = "/generate/{id}")
    public ResponseEntity<String> generateTimetableFromScratch(@PathVariable(name = "id") String id) {
        logger.debug("generating timetables for all school classes");
        final String timeTableId = timeTableApplication.generateNewTimeTablesForSchoolClass(id);
        return ResponseEntity.ok(timeTableId);
    }

}
