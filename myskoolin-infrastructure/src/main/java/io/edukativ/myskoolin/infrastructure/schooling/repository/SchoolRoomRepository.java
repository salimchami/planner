package io.edukativ.myskoolin.infrastructure.schooling.repository;

import io.edukativ.myskoolin.infrastructure.schooling.dto.SchoolRoomDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the SchoolRoom entity.
 */
@Repository
public interface SchoolRoomRepository extends MongoRepository<SchoolRoomDbDTO, String> {


    @Query("{ 'deleted': ?0, 'clientId': ?1 }")
    Optional<List<SchoolRoomDbDTO>> findByClientId(@Param("deleted") boolean deleted, @Param("clientId") ObjectId clientId);

    @Query("{ 'name' :  ?0, 'deleted': ?1, 'clientId': ?2 }")
    Optional<SchoolRoomDbDTO> findOneByName(@Param("name") String name, @Param("deleted") boolean deleted, @Param("clientId") ObjectId clientId);
}
