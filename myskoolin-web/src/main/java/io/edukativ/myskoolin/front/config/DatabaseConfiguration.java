package io.edukativ.myskoolin.front.config;

import io.github.jhipster.config.JHipsterConstants;
import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import io.github.jhipster.domain.util.JSR310DateConverters.DateToZonedDateTimeConverter;
import io.github.jhipster.domain.util.JSR310DateConverters.ZonedDateTimeToDateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableElasticsearchRepositories("io.edukativ.myskoolin.infrastructure.app.repository.search")
@EnableMongoRepositories(basePackages = {
    "io.edukativ.myskoolin.infrastructure.app.repository",
    "io.edukativ.myskoolin.infrastructure.schooling.repository"
}, includeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = MongoRepository.class))
@Profile("!" + JHipsterConstants.SPRING_PROFILE_CLOUD)
@Import(value = MongoAutoConfiguration.class)
@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class DatabaseConfiguration {

    private static final String BASE_DB_MIGRATION_PACKAGE = "io.edukativ.myskoolin.infrastructure.config.dbmigrations";
    private static final String COMMON_DB_MIGRATION_PACKAGE = BASE_DB_MIGRATION_PACKAGE + ".common";
    private static final String DEV_DB_MIGRATION_PACKAGE = BASE_DB_MIGRATION_PACKAGE + ".dev";
    private static final String PROD_DB_MIGRATION_PACKAGE = BASE_DB_MIGRATION_PACKAGE + ".prod";

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private Environment environment;

    public DatabaseConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(DateToZonedDateTimeConverter.INSTANCE);
        converters.add(ZonedDateTimeToDateConverter.INSTANCE);
        return new MongoCustomConversions(converters);
    }

    @Bean
    public Mongobee mongobeeCommonEnv(MongoClient mongoClient, MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        Mongobee mongobee = commonConfig(mongoClient, mongoTemplate, mongoProperties);
        log.debug("--------------> Configuring Mongobee for Common Env...");
        mongobee.setChangeLogsScanPackage(COMMON_DB_MIGRATION_PACKAGE);
        return mongobee;
    }

    @Bean
    public Mongobee mongobeeByEnv(MongoClient mongoClient, MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        Mongobee mongobee = commonConfig(mongoClient, mongoTemplate, mongoProperties);
        List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            log.debug("--------------> Configuring Mongobee for PROD...");
            mongobee.setChangeLogsScanPackage(PROD_DB_MIGRATION_PACKAGE);
        } else if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)) {
            log.debug("--------------> Configuring Mongobee for DEV...");
            mongobee.setChangeLogsScanPackage(DEV_DB_MIGRATION_PACKAGE);
        }
        return mongobee;
    }

    private Mongobee commonConfig(MongoClient mongoClient, MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        log.debug("Configuring Mongobee");
        Mongobee mongobee = new Mongobee(mongoClient);
        mongobee.setSpringEnvironment(environment);
        mongobee.setDbName(mongoProperties.getDatabase());
        mongobee.setMongoTemplate(mongoTemplate);
        mongobee.setEnabled(true);
        return mongobee;
    }
}
