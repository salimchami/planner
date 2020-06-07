package io.edukativ.myskoolin.front.security;

import io.edukativ.myskoolin.application.security.DomainUserDetailsService;
import io.edukativ.myskoolin.application.security.UserNotActivatedException;
import io.edukativ.myskoolin.front.MyskoolinApp;
import io.edukativ.myskoolin.infrastructure.app.repository.UserRepository;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Integrations tests for {@link DomainUserDetailsService}.
 */
@SpringBootTest(classes = MyskoolinApp.class)
public class DomainUserDetailsServiceIT {

    private static final String USER_ONE_LOGIN = "test-user-one";
    private static final String USER_ONE_EMAIL = "test-user-one@localhost";
    private static final String USER_TWO_LOGIN = "test-user-two";
    private static final String USER_TWO_EMAIL = "test-user-two@localhost";
    private static final String USER_THREE_LOGIN = "test-user-three";
    private static final String USER_THREE_EMAIL = "test-user-three@localhost";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService domainUserDetailsService;

    @BeforeEach
    public void init() {
        userRepository.deleteAll();

        UserDbDTO userOne = new UserDbDTO.UserDbDTOBuilder()
            .login(USER_ONE_LOGIN)
            .password(RandomStringUtils.random(60))
            .activated(true)
            .email(USER_ONE_EMAIL)
            .firstName("userOne")
            .lastName("doe")
            .langKey("en")
            .build();
        userRepository.save(userOne);

        UserDbDTO userTwo = new UserDbDTO.UserDbDTOBuilder()
            .login(USER_TWO_LOGIN)
            .password(RandomStringUtils.random(60))
            .activated(true)
            .email(USER_TWO_EMAIL)
            .firstName("userTwo")
            .lastName("doe")
            .langKey("en")
            .build();
        userRepository.save(userTwo);

        UserDbDTO userThree = new UserDbDTO.UserDbDTOBuilder()
            .login(USER_THREE_LOGIN)
            .password(RandomStringUtils.random(60))
            .activated(false)
            .email(USER_THREE_EMAIL)
            .firstName("userThree")
            .lastName("doe")
            .langKey("en")
            .build();
        userRepository.save(userThree);
    }

    @Test
    public void assertThatUserCanBeFoundByLogin() {
        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_ONE_LOGIN);
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(USER_ONE_LOGIN);
    }

    @Test
    public void assertThatUserCanBeFoundByLoginIgnoreCase() {
        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_ONE_LOGIN.toUpperCase(Locale.ENGLISH));
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(USER_ONE_LOGIN);
    }

    @Test
    public void assertThatUserCanBeFoundByEmail() {
        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_TWO_EMAIL);
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(USER_TWO_LOGIN);
    }

    @Test
    public void assertThatUserCanBeFoundByEmailIgnoreCase() {
        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_TWO_EMAIL.toUpperCase(Locale.ENGLISH));
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(USER_TWO_LOGIN);
    }

    @Test
    public void assertThatEmailIsPrioritizedOverLogin() {
        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(USER_ONE_EMAIL);
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(USER_ONE_LOGIN);
    }

    @Test
    public void assertThatUserNotActivatedExceptionIsThrownForNotActivatedUsers() {
        assertThatExceptionOfType(UserNotActivatedException.class).isThrownBy(
            () -> domainUserDetailsService.loadUserByUsername(USER_THREE_LOGIN));
    }

}
