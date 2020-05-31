package io.edukativ.myskoolin.infrastructure.schooling.repository;

import io.edukativ.myskoolin.infrastructure.commercial.dto.ClientContractRequestDbDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the Grade entity.
 */
@Repository
public interface ClientContractRequestRepository extends MongoRepository<ClientContractRequestDbDTO, String> {


}
