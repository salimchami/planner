package io.edukativ.myskoolin.front.config.injections;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyskoolinCommonInjectionConfiguration {

    private final UserService userService;

    public MyskoolinCommonInjectionConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public UserDbDTO currentUserWithAuthorities() {
        return userService.currentUserWithAuthorities();
    }

    @Bean
    public User currentUser() {
        return userService.currentUser();
    }
}
