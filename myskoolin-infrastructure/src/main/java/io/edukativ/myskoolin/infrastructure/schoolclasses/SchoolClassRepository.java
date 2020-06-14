package io.edukativ.myskoolin.infrastructure.schoolclasses;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the SchoolClass entity.
 */
@Repository
public interface SchoolClassRepository extends MongoRepository<SchoolClassDbDTO, ObjectId> {

    /**
     * Find all not deleted teachers
     *
     * @return List of not deleted teachers
     */
    @Query("{ 'deleted': false, 'clientId': ?0 , 'name': ?1}")
    SchoolClassDbDTO findOneByNameNotDeleted(ObjectId clientId, String name);

    /**
     * Find all not deleted students
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false }")
    List<SchoolClassDbDTO> findAllNotDeletedSchoolClasses();

    /**
     * Find all not deleted students
     *
     * @return List of not deleted students
     */
    @Query("{ 'deleted': false, 'clientId': ?0 }")
    List<SchoolClassDbDTO> findAllNotDeletedSchoolClasses(ObjectId clientId);
}
