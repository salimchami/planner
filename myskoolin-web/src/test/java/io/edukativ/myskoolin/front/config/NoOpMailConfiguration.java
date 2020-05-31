package io.edukativ.myskoolin.front.config;

import io.edukativ.myskoolin.infrastructure.app.providers.MailProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@Configuration
public class NoOpMailConfiguration {
    private final MailProvider mockMailService;

    public NoOpMailConfiguration() {
        mockMailService = mock(MailProvider.class);
        doNothing().when(mockMailService).sendActivationEmail(any());
    }

    @Bean
    public MailProvider mailService() {
        return mockMailService;
    }
}
