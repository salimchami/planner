package io.edukativ.myskoolin.front.config.injections;

import io.edukativ.myskoolin.domain.schoolrooms.api.SchoolRoomAPI;
import io.edukativ.myskoolin.domain.schoolrooms.core.SchoolRoomService;
import io.edukativ.myskoolin.domain.schoolrooms.spi.SchoolRoomSPI;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomMapper;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomProvider;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolRoomInjectionConfiguration {

    @Bean
    public SchoolRoomAPI schoolRoomService(SchoolRoomSPI schoolRoomSPI) {
        return new SchoolRoomService(schoolRoomSPI);
    }

    @Bean
    public SchoolRoomSPI schoolRoomSPI(SchoolRoomRepository schoolRoomRepository, SchoolRoomMapper schoolRoomMapper) {
        return new SchoolRoomProvider(schoolRoomRepository, schoolRoomMapper);
    }
}
