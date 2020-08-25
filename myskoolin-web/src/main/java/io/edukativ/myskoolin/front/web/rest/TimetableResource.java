package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.TimeTableApplication;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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
    @PostMapping(value = "/solve")
    public ResponseEntity<Void> solveTimetablesFromScratch() {
        logger.debug("generating timetables for all school classes");
        timeTableApplication.solveNewTimeTablesForSchoolClasses();
        return ResponseEntity.ok().build();
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @PostMapping(value = "/solve/{id}")
    public ResponseEntity<Void> solveTimetableFromScratch(@PathVariable(name = "id") String id) {
        logger.debug("generating timetables for all school classes");
        timeTableApplication.solveNewTimeTablesForSchoolClass(id);
        return ResponseEntity.ok().build();
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping(value = "/{schoolCLassId}")
    public ResponseEntity<SchoolClassTimeTableDTO> schoolClassTimeTable(
        @PathVariable(name = "schoolCLassId") String schoolCLassId) {
        return timeTableApplication.timeTableBySchoolClassId(schoolCLassId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping(value = "/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(timeTableApplication.countTimetables());
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping(value = "/lastGenerationDate")
    public ResponseEntity<Instant> lastGenerationDate() {
        return ResponseEntity.ok(timeTableApplication.lastGenerationDate());
    }

    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE,
    })
    @GetMapping
    public ResponseEntity<List<SchoolClassTimeTableDTO>> schoolClassTimeTables() {
        return ResponseEntity.ok(timeTableApplication.timeTables());
    }
}
