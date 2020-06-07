package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.infrastructure.app.dto.FeatureDbDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Feature entity.
 */
@Repository
public interface FeatureRepository extends MongoRepository<FeatureDbDTO, String> {

    @Query(value = "{ 'name' :  ?0 }")
    Optional<FeatureDbDTO> findByName(String name);
}
