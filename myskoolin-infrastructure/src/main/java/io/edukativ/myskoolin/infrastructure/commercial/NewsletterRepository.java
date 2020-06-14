package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.app.dto.NewsletterDbDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Newsletter entity.
 */
@Repository
public interface NewsletterRepository extends MongoRepository<NewsletterDbDTO, String> {

    /**
     * Find newsletter by email.
     *
     * @param email newsletter email
     * @param now   current date
     * @return the searched newsletter
     */
    @Query("{ 'email' :  ?0, confirmTokenExpires: {$gt: ?1} }")
    Optional<NewsletterDbDTO> findNotExpiredEmail(@Param("email") String email, @Param("confirmTokenExpires") ZonedDateTime now);

    /**
     * Find not expired newsletter by confirmation token
     *
     * @param confirmToken confirmation token
     * @param now          current date
     * @return the searched newsletter
     */
    @Query("{ 'confirmToken' :  ?0, confirmTokenExpires: {$gt: ?1} }")
    Optional<NewsletterDbDTO> findNotExpiredByConfirmToken(@Param("confirmToken") String confirmToken, @Param("confirmTokenExpires") ZonedDateTime now);
}
