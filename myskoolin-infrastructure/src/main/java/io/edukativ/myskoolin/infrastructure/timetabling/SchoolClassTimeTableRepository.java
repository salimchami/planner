package io.edukativ.myskoolin.infrastructure.timetabling;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the TimeTableOptions entity.
 */
@Repository
public interface SchoolClassTimeTableRepository extends MongoRepository<SchoolClassTimeTableDbDTO, String> {

    @Query("{ 'clientId': ?0 }")
    List<SchoolClassTimeTableDbDTO> findByClientId(ObjectId objectId);
}
