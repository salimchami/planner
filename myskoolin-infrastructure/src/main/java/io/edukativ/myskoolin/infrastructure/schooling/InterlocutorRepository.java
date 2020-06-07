package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.infrastructure.commercial.InterlocutorDbDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Interlocutor entity.
 */
@Repository
public interface InterlocutorRepository extends MongoRepository<InterlocutorDbDTO,String> {

}
