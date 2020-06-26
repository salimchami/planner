package io.edukativ.myskoolin.infrastructure.app.repository.search;

import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<UserDbDTO, String> {
}
