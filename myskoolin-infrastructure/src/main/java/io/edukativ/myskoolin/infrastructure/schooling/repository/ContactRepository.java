package io.edukativ.myskoolin.infrastructure.schooling.repository;

import io.edukativ.myskoolin.infrastructure.commercial.dto.ContactDbDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Contact entity.
 */
@Repository
public interface ContactRepository extends MongoRepository<ContactDbDTO, String> {

}
