package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.dto.SchoolRoomDTO;
import io.edukativ.myskoolin.application.mapper.SchoolRoomMapper;
import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.dto.SchoolRoomDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.repository.SchoolRoomRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SchoolRoomApplication {

    private final SchoolRoomMapper schoolRoomMapper;
    private final SchoolRoomRepository schoolRoomRepository;
    private final UserService userService;

    public SchoolRoomApplication(SchoolRoomMapper schoolRoomMapper, SchoolRoomRepository schoolRoomRepository, UserService userService) {
        this.schoolRoomMapper = schoolRoomMapper;
        this.schoolRoomRepository = schoolRoomRepository;
        this.userService = userService;
    }

    public List<SchoolRoomDTO> findSchoolRooms() {
        Optional<UserDbDTO> optCurrentUser = userService.getCurrentUserWithAuthorities();
        return optCurrentUser.map(user -> {
            final List<SchoolRoomDbDTO> schoolRooms = schoolRoomRepository.findByClientId(false, user.getClientId());
            return schoolRooms.stream().map(schoolRoomMapper::schoolRoomDbDTOToSchoolRoomDTO).collect(Collectors.toList());
        }).orElse(Collections.emptyList());
    }

    public Optional<SchoolRoomDTO> findOneByName(String name) {
        final Optional<UserDbDTO> optUserWithAuthorities = userService.getCurrentUserWithAuthorities();
        return optUserWithAuthorities.flatMap(user -> {
            final Optional<SchoolRoomDbDTO> optSchoolRoomDTO = schoolRoomRepository.findOneByName(name, false, user.getClientId());
            return optSchoolRoomDTO.map(schoolRoomMapper::schoolRoomDbDTOToSchoolRoomDTO);
        });
    }
}
