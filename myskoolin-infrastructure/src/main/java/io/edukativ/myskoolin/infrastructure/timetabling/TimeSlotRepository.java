package io.edukativ.myskoolin.infrastructure.timetabling;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the TimeSlot entity.
 */
@Repository
public interface TimeSlotRepository extends MongoRepository<TimeSlotDbVO, String> {

}
