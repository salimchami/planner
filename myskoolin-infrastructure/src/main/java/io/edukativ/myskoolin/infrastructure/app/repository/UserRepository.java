package io.edukativ.myskoolin.infrastructure.app.repository;

import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the User entity.
 */
@Repository
public interface UserRepository extends MongoRepository<UserDbDTO, String> {

    Optional<UserDbDTO> findOneByActivationKey(String activationKey);

    List<UserDbDTO> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<UserDbDTO> findOneByResetKey(String resetKey);

    Optional<UserDbDTO> findOneByEmailIgnoreCase(String email);

    Optional<UserDbDTO> findOneByLogin(String login);

    Page<UserDbDTO> findAllByLoginNot(Pageable pageable, String login);

    List<UserDbDTO> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime);

    Optional<UserDbDTO> findOneByEmail(String email);

    Optional<UserDbDTO> findOneByLoginIgnoreCase(String login);

    Optional<UserDbDTO> findOneByLoginIgnoreCaseAndEmailIgnoreCase(String login, String email);

    Optional<UserDbDTO> findOneById(ObjectId userId);

    List<UserDbDTO> findAllByDeletedIsTrueAndCreatedDateBefore(Instant minus);

    @Query("{ '_id': {$in: ?0} }")
    List<UserDbDTO> findByIds(List<ObjectId> collect);

}
