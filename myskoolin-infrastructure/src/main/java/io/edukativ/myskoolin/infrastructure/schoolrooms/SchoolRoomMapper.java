package io.edukativ.myskoolin.infrastructure.schoolrooms;

import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ObjectIdMapper.class})
public interface SchoolRoomMapper {

    SchoolRoomDTO dbDTOToDTO(SchoolRoomDbDTO schoolRoom);

    SchoolRoomDbDTO dtoToDbDTO(SchoolRoomDTO schoolRoom);

    List<SchoolRoomDTO> dbDtosToDtos(List<SchoolRoomDbDTO> all);

    SchoolRoomDTO domainToDto(SchoolRoom schoolRoom);

    SchoolRoom dtoToDomain(SchoolRoomDTO schoolRoom);

    SchoolRoom dbDtoToDomain(SchoolRoomDbDTO schoolRoomDbDTO);

    SchoolRoomDbDTO domainToDbDto(SchoolRoom schoolRoom);

    List<SchoolRoom> dbDtosToDomains(List<SchoolRoomDbDTO> schoolRooms);

}
