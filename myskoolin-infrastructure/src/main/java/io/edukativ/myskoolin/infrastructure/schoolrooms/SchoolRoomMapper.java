package io.edukativ.myskoolin.infrastructure.schoolrooms;

import io.edukativ.myskoolin.domain.entity.SchoolRoom;
import io.edukativ.myskoolin.infrastructure.temp.ObjectIdMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ObjectIdMapper.class})
public interface SchoolRoomMapper {

    SchoolRoomDTO dbDTOToDTO(SchoolRoomDbDTO schoolRoom);

    SchoolRoomDbDTO dtoToDbDTO(SchoolRoomDTO schoolRoom);

    List<SchoolRoomDTO> dbDtosToDtos(List<SchoolRoomDbDTO> all);

    SchoolRoomDTO domainToDto(SchoolRoom schoolRoom);

    SchoolRoom dtoToDomain(SchoolRoomDTO schoolRoom);

    SchoolRoom dbDtoToDomain(SchoolRoomDbDTO schoolRoomDbDTO);

    SchoolRoomDbDTO domainToDbDto(SchoolRoom schoolRoom);
}
