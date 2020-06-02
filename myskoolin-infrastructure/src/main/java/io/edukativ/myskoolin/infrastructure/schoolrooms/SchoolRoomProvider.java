package io.edukativ.myskoolin.infrastructure.schoolrooms;

import io.edukativ.myskoolin.domain.entity.SchoolRoom;
import io.edukativ.myskoolin.domain.schoolrooms.spi.SchoolRoomSPI;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SchoolRoomProvider implements SchoolRoomSPI {

    private final SchoolRoomRepository schoolRoomRepository;
    private final SchoolRoomMapper schoolRoomMapper;

    public SchoolRoomProvider(SchoolRoomRepository schoolRoomRepository, SchoolRoomMapper schoolRoomMapper) {
        this.schoolRoomRepository = schoolRoomRepository;
        this.schoolRoomMapper = schoolRoomMapper;
    }

    @Override
    public Optional<SchoolRoom> findById(String id) {
        final Optional<SchoolRoomDbDTO> optSchoolRoomDbDTO = schoolRoomRepository.findById(id);
        return optSchoolRoomDbDTO.map(schoolRoomMapper::dbDtoToDomain);
    }

    @Override
    public SchoolRoom persist(SchoolRoom schoolRoom) {
        final SchoolRoomDbDTO schoolRoomToSave = schoolRoomMapper.domainToDbDto(schoolRoom);
        final SchoolRoomDbDTO savedSchoolRoom = schoolRoomRepository.save(schoolRoomToSave);
        return schoolRoomMapper.dbDtoToDomain(savedSchoolRoom);
    }

    @Override
    public Optional<SchoolRoom> findByName(String schoolRoomName, boolean deleted, String clientId) {
        final Optional<SchoolRoomDbDTO> schoolRoomDbDTO = schoolRoomRepository.findOneByName(schoolRoomName, deleted, new ObjectId(clientId));
        return schoolRoomDbDTO.map(schoolRoomMapper::dbDtoToDomain);
    }
}
