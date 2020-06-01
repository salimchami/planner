package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.SchoolRoomApplication;
import io.edukativ.myskoolin.application.dto.SchoolRoomDTO;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolRoomsTypes;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import io.github.jhipster.web.util.HeaderUtil;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
class SchoolRoomResource {

    private final Logger log = LoggerFactory.getLogger(SchoolRoomResource.class);

    private final SchoolRoomApplication schoolRoomApplication;

    public SchoolRoomResource(SchoolRoomApplication schoolRoomApplication) {
        this.schoolRoomApplication = schoolRoomApplication;
    }

    @Timed
    @Transactional
    @PostMapping(value = "/school-rooms")
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    public ResponseEntity<SchoolRoomDTO> createSchoolRoom(@RequestBody SchoolRoomDTO schoolRoomDTO) throws URISyntaxException {
        log.debug("REST request to create SchoolRoom : {}", schoolRoomDTO);
        if (schoolRoomDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(Constants.APPLICATION_NAME, false,
                SchoolRoomDTO.ENTITY_NAME, "", "A new schoolRoom cannot already have an ID")).body(null);
        } else if (schoolRoomDTO.getClientId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(Constants.APPLICATION_NAME, false,
                SchoolRoomDTO.ENTITY_NAME, "", "A new schoolRoom must have a client")).body(null);
        }
        SchoolRoomDTO schoolRoom = schoolRoomApplication.createSchoolRoom(schoolRoomDTO);
        return ResponseEntity.created(new URI("/api/school-rooms/" + schoolRoom.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(Constants.APPLICATION_NAME, false, "schoolRoom", schoolRoom.getId()))
            .body(schoolRoom);
    }

    @Timed
    @PutMapping(value = "/school-rooms")
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    public ResponseEntity<SchoolRoomDTO> updateSchoolRoom(@RequestBody SchoolRoomDTO schoolRoomDTO) {
        log.debug("REST request to update SchoolRoom : {}", schoolRoomDTO);
        if (schoolRoomDTO.getClientId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(Constants.APPLICATION_NAME,
                false, SchoolRoomDTO.ENTITY_NAME, "", "The schoolRoom must have a client")).body(null);
        } else if (schoolRoomDTO.getId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(Constants.APPLICATION_NAME,
                false, SchoolRoomDTO.ENTITY_NAME, "", "The schoolRoom must have an id")).body(null);
        }

        SchoolRoomDTO updatedSchoolRoom = schoolRoomApplication.updateSchoolRoom(schoolRoomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(Constants.APPLICATION_NAME, false,
                SchoolRoomDTO.ENTITY_NAME, updatedSchoolRoom.getId()))
            .body(updatedSchoolRoom);
    }

    @GetMapping(value = "/school-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @Timed
    public ResponseEntity<List<SchoolRoomDTO>> getAllSchoolRooms() {
        log.debug("REST request to get all SchoolRooms");
        List<SchoolRoomDTO> schoolRooms = schoolRoomApplication.findSchoolRooms();
        if (schoolRooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(schoolRooms, HttpStatus.OK);
        }
    }

    @Timed
    @GetMapping(value = "/school-rooms/{name}")
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    public ResponseEntity<SchoolRoomDTO> getSchoolRoom(@PathVariable String name) {
        log.debug("REST request to get SchoolRoom : {}", name);
        Optional<SchoolRoomDTO> schoolRoom = schoolRoomApplication.findOneByName(name);
        return schoolRoom
            .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Timed
    @Transactional
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @GetMapping(value = "/school-rooms/nameAvailable/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> getSchoolRoomByName(@PathVariable String name) {
        log.debug("REST request to get SchoolRoom by name : {}", name);
        Optional<SchoolRoomDTO> schoolRoom = schoolRoomApplication.findOneByName(name);
        return schoolRoom
            .map(result -> new ResponseEntity<>(true, HttpStatus.OK))
            .orElse(new ResponseEntity<>(false, HttpStatus.NO_CONTENT));
    }

    @Timed
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    @DeleteMapping(value = "/school-rooms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSchoolRoom(@PathVariable String id) {
        log.debug("REST request to delete SchoolRoom : {}", id);
        schoolRoomApplication.deleteSchoolRoom(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(Constants.APPLICATION_NAME,
            false, SchoolRoomDTO.ENTITY_NAME, id)).build();
    }

    @Timed
    @GetMapping(value = "/school-rooms/schoolRoomsTypes")
    @Secured({
        AuthoritiesConstants.ADMINISTRATION,
        AuthoritiesConstants.INFIRMARY,
        AuthoritiesConstants.TEACHERS,
        AuthoritiesConstants.SCHOOL_LIFE
    })
    public ResponseEntity<List<String>> schoolRoomsTypes() {
        return new ResponseEntity<>(
            Arrays.stream(EnumSchoolRoomsTypes.values())
                .map(EnumSchoolRoomsTypes::getCode)
                .collect(Collectors.toList()),
            HttpStatus.OK
        );
    }
}
