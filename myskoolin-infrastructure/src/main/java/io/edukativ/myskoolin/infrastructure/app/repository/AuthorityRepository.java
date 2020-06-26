package io.edukativ.myskoolin.infrastructure.app.repository;

import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the {@link AuthorityDbDTO} entity.
 */
public interface AuthorityRepository extends MongoRepository<AuthorityDbDTO, String> {


}
