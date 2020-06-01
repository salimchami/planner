package io.edukativ.myskoolin.application.mapper;

import io.edukativ.myskoolin.application.dto.SchoolRoomDTO;
import io.edukativ.myskoolin.infrastructure.schooling.dto.SchoolRoomDbDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ObjectIdMapper.class})
public interface SchoolRoomMapper {

    SchoolRoomDTO schoolRoomDbDTOToSchoolRoomDTO(SchoolRoomDbDTO school);

    SchoolRoomDbDTO schoolRoomDTOToSchoolRoomDbDTO(SchoolRoomDTO grade);

    List<SchoolRoomDTO> schoolRoomsDbDTOToSchoolRoomsDTO(List<SchoolRoomDbDTO> all);

}
