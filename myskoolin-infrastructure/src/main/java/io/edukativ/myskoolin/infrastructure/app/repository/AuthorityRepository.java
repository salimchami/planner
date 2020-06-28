package io.edukativ.myskoolin.infrastructure.app.repository;

import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the {@link AuthorityDbDTO} entity.
 */
@Repository
public interface AuthorityRepository extends MongoRepository<AuthorityDbDTO, String> {


}
