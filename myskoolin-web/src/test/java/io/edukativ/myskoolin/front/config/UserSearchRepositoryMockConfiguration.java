package io.edukativ.myskoolin.front.config;

import io.edukativ.myskoolin.infrastructure.app.repository.UserSearchRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link UserSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class UserSearchRepositoryMockConfiguration {

    @MockBean
    private UserSearchRepository mockUserSearchRepository;

}
