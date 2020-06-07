package io.edukativ.myskoolin.infrastructure.schooling;

import io.edukativ.myskoolin.infrastructure.schooling.vo.TimeTableOptionsDbVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the TimeTableOptions entity.
 */
@Repository
public interface TimeTableOptionsRepository extends MongoRepository<TimeTableOptionsDbVO, String> {

}
