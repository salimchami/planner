package io.edukativ.myskoolin.infrastructure.commercial;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Pricing entity.
 */
@Repository
public interface PricingRepository extends MongoRepository<PricingDbDTO, String> {

}
