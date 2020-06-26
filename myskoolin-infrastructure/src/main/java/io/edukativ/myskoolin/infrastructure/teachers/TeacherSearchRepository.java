package io.edukativ.myskoolin.infrastructure.teachers;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface TeacherSearchRepository extends ElasticsearchRepository<TeacherDbDTO, String> {
}
