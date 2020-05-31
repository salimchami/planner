package io.edukativ.myskoolin.infrastructure.schooling.repository;

import io.edukativ.myskoolin.infrastructure.commercial.dto.ClientDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Contact entity.
 */
@Repository
public interface ClientRepository extends MongoRepository<ClientDbDTO, String> {

    @Query("{ 'name' :  ?0, 'deleted': ?1 }")
    Optional<ClientDbDTO> findOneByName(@Param("name") String name, @Param("deleted") boolean deleted);

    @Query("{ 'duns' :  ?0, 'deleted': ?1 }")
    Optional<ClientDbDTO> findOneByDuns(@Param("duns") String duns, @Param("deleted") boolean deleted);

    Optional<ClientDbDTO> findOneById(ObjectId userId);


}
