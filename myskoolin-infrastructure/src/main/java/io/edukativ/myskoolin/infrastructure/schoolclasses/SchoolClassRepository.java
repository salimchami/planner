package io.edukativ.myskoolin.infrastructure.schoolclasses;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the SchoolClass entity.
 */
@Repository
public interface SchoolClassRepository extends MongoRepository<SchoolClassDbDTO, ObjectId> {

    @Query("{ 'deleted': false, 'clientId': ?0 , 'name': ?1}")
    SchoolClassDbDTO findOneByNameNotDeleted(ObjectId clientId, String name);

    @Query("{ 'deleted': false, 'clientId': ?0 , 'id': ?1}")
    Optional<SchoolClassDbDTO> findOneByIdNotDeleted(ObjectId clientId, ObjectId id);

    @Query("{ 'deleted': false }")
    List<SchoolClassDbDTO> findAllNotDeletedSchoolClasses();

    @Query("{ 'deleted': false, 'clientId': ?0 }")
    List<SchoolClassDbDTO> findAllNotDeletedSchoolClasses(ObjectId clientId);
}
