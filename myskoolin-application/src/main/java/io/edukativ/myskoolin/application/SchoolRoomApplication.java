package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDTO;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomMapper;
import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.SchoolRoom;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoomAPI;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomRepository;
import io.edukativ.myskoolin.infrastructure.app.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class SchoolRoomApplication {

    private final SchoolRoomMapper schoolRoomMapper;
    private final UserMapper userMapper;
    private final SchoolRoomRepository schoolRoomRepository;
    private final UserService userService;
    private final SchoolRoomAPI schoolRoomAPI;

    public SchoolRoomApplication(SchoolRoomMapper schoolRoomMapper, UserMapper userMapper, SchoolRoomRepository schoolRoomRepository,
                                 UserService userService, SchoolRoomAPI schoolRoomAPI) {
        this.schoolRoomMapper = schoolRoomMapper;
        this.userMapper = userMapper;
        this.schoolRoomRepository = schoolRoomRepository;
        this.userService = userService;
        this.schoolRoomAPI = schoolRoomAPI;
    }

    public List<SchoolRoomDTO> findSchoolRooms() {
        Optional<UserDbDTO> optCurrentUser = userService.getCurrentUserWithAuthorities();
        return optCurrentUser.map(user -> {
            final List<SchoolRoomDbDTO> schoolRooms = schoolRoomRepository.findByClientId(false, user.getClientId());
            return schoolRoomMapper.dbDtosToDtos(schoolRooms);
        }).orElse(Collections.emptyList());
    }

    public Optional<SchoolRoomDTO> findOneByName(String name) {
        final Optional<UserDbDTO> optUserWithAuthorities = userService.getCurrentUserWithAuthorities();
        return optUserWithAuthorities.flatMap(user -> {
            final Optional<SchoolRoomDbDTO> optSchoolRoomDTO = schoolRoomRepository.findOneByName(name, false, user.getClientId());
            return optSchoolRoomDTO.map(schoolRoomMapper::dbDTOToDTO);
        });
    }

    public SchoolRoomDTO createOrUpdateSchoolRoom(SchoolRoomDTO schoolRoomDTO) {
        final Optional<UserDbDTO> optUserWithAuthorities = userService.getCurrentUserWithAuthorities();
        return optUserWithAuthorities.flatMap(userDbDTO -> {
            final SchoolRoom schoolRoom = schoolRoomMapper.dtoToDomain(schoolRoomDTO);
            Optional<SchoolRoom> optSavedSchoolRoom = schoolRoomAPI.createOrUpdateSchoolRoom(schoolRoom, userMapper.dbDtoToDomain(userDbDTO));
            return optSavedSchoolRoom.map(schoolRoomMapper::domainToDto);
        }).orElseThrow();
    }

    public void deleteSchoolRoom(String id) {
        schoolRoomAPI.deleteSchoolRoom(id);
    }
}
