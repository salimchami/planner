package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.commercial.SubscriptionDbDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Subscription entity.
 */
@Repository
public interface SubscriptionRepository extends MongoRepository<SubscriptionDbDTO, String> {

    @Query("{ 'contractualised' :  false }")
    List<SubscriptionDbDTO> findAllNotContractualised();
}
