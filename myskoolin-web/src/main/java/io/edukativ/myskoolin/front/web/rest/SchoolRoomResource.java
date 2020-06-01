package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.SchoolRoomApplication;
import io.edukativ.myskoolin.application.dto.SchoolRoomDTO;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class SchoolRoomResource {

    private final Logger log = LoggerFactory.getLogger(SchoolRoomResource.class);

    private final SchoolRoomApplication schoolRoomApplication;

    public SchoolRoomResource(SchoolRoomApplication schoolRoomApplication) {
        this.schoolRoomApplication = schoolRoomApplication;
    }

//    @PostMapping(value = "/school-rooms")
//    @Timed
//    @Transactional
//    public ResponseEntity<SchoolRoomDTO> createSchoolRoom(@RequestBody SchoolRoomDTO schoolRoomDTO) throws URISyntaxException {
//        log.debug("REST request to save SchoolRoom : {}", schoolRoomDTO);
//        if (schoolRoomDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false,
//                entityName, "", "A new schoolRoom cannot already have an ID")).body(null);
//        } else if (schoolRoomDTO.getClientId() == null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, true,
//                entityName, "" , "A new schoolRoom must have a client")).body(null);
//        }
//        SchoolRoom schoolRoom = schoolRoomApplication.saveOrUpdateSchoolRoom(schoolRoomMapper.toSchoolRoom(schoolRoomDTO));
//        SchoolRoomDTO dto = schoolRoomMapper.toSchoolRoomDTO(schoolRoom);
//        return ResponseEntity.created(new URI("/api/school-rooms/" + dto.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert("schoolRoom", dto.getId()))
//            .body(dto);
//    }

//    @PutMapping(value = "/school-rooms")
//    @Timed
//    public ResponseEntity<SchoolRoomDTO> updateSchoolRoom(@RequestBody SchoolRoomDTO schoolRoomDTO) {
//        log.debug("REST request to update SchoolRoom : {}", schoolRoomDTO);
//        if (schoolRoomDTO.getClientId() == null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("schoolRoom", "clientNotExists", "The schoolRoom must have a client")).body(null);
//        } else if (schoolRoomDTO.getId() == null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("schoolRoom", "IdNotExists", "The schoolRoom must have an id")).body(null);
//        }
//        SchoolRoom schoolRoom = schoolRoomMapper.toSchoolRoom(schoolRoomDTO);
//        SchoolRoom updatedSchoolRoom = schoolRoomApplication.saveOrUpdateSchoolRoom(schoolRoom);
//        SchoolRoomDTO result = schoolRoomMapper.toSchoolRoomDTO(updatedSchoolRoom);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert("schoolRoom", result.getId()))
//            .body(result);
//    }

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

//    @RequestMapping(value = "/school-rooms/{id}",
//        method = RequestMethod.DELETE,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    public ResponseEntity<Void> deleteSchoolRoom(@PathVariable String id) {
//        log.debug("REST request to delete SchoolRoom : {}", id);
//        schoolRoomApplication.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("schoolRoom", id)).build();
//    }

//    @RequestMapping(value = "/school-rooms/nameAvailable/{name}",
//        method = RequestMethod.GET,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    @Transactional
//    public Boolean getSchoolRoomByName(@PathVariable String name) {
//        log.debug("REST request to get SchoolRoom by name : {}", name);
//        Optional<UserDTO> currentUser = userApplication.getUserWithAuthorities();
//        final Boolean[] result = {false};
//        currentUser.ifPresent(user -> {
//            Optional<SchoolRoom> schoolRoom = schoolRoomRepository.findOneByName(name, false, currentUser.get().getClientId());
//            schoolRoom.ifPresent(schoolRoom1 -> result[0] = true);
//        });
//        return result[0];
//    }

//    @RequestMapping(value = "/school-rooms/schoolRoomsTypes")
//    @Timed
//    public ResponseEntity<List<String>> schoolRoomsTypes() {
//        return new ResponseEntity<>(Arrays.stream(EnumSchoolRoomsTypes.values()).map(EnumSchoolRoomsTypes::getCode).collect(Collectors.toList()), HttpStatus.OK);
//    }
}
