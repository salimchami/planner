package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.SchoolRoom;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoomAPI;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.app.mapper.UserMapper;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDTO;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomMapper;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        UserDbDTO user = userService.currentUserWithAuthorities();
        final List<SchoolRoomDbDTO> schoolRooms = schoolRoomRepository.findByClientId(false, user.getClientId());
        return schoolRoomMapper.dbDtosToDtos(schoolRooms);
    }

    public Optional<SchoolRoomDTO> findOneByName(String name) {
        UserDbDTO user = userService.currentUserWithAuthorities();
        final Optional<SchoolRoomDbDTO> optSchoolRoomDTO = schoolRoomRepository.findOneByName(name, false, user.getClientId());
        return optSchoolRoomDTO.map(schoolRoomMapper::dbDTOToDTO);
    }

    public SchoolRoomDTO createOrUpdateSchoolRoom(SchoolRoomDTO schoolRoomDTO) {
        UserDbDTO user = userService.currentUserWithAuthorities();
        final SchoolRoom schoolRoom = schoolRoomMapper.dtoToDomain(schoolRoomDTO);
        Optional<SchoolRoom> optSavedSchoolRoom = schoolRoomAPI.createOrUpdateSchoolRoom(schoolRoom, userMapper.dbDtoToDomain(user));
        // FIXME: delete orElse(null)
        return optSavedSchoolRoom.map(schoolRoomMapper::domainToDto).orElse(null);
    }

    public void deleteSchoolRoom(String id) {
        schoolRoomAPI.deleteSchoolRoom(id);
    }
}
