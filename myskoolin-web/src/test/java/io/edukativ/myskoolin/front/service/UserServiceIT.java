package io.edukativ.myskoolin.front.service;

import io.edukativ.myskoolin.infrastructure.app.dto.UserDTO;
import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.front.MyskoolinApp;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.app.repository.search.UserSearchRepository;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import io.edukativ.myskoolin.infrastructure.app.repository.UserRepository;
import io.github.jhipster.security.RandomUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Integration tests for {@link UserService}.
 */
@SpringBootTest(classes = MyskoolinApp.class)
public class UserServiceIT {

    private static final String DEFAULT_LOGIN = "johndoe";

    private static final String DEFAULT_EMAIL = "johndoe@localhost";

    private static final String DEFAULT_FIRSTNAME = "john";

    private static final String DEFAULT_LASTNAME = "doe";

    private static final String DEFAULT_IMAGEURL = "http://placehold.it/50x50";

    private static final String DEFAULT_LANGKEY = "dummy";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * This repository is mocked in the io.edukativ.schooling.repository.search test package.
     */
    @Autowired
    private UserSearchRepository mockUserSearchRepository;

    private UserDbDTO user;

    @BeforeEach
    public void init() {
        userRepository.deleteAll();
        user = new UserDbDTO.UserDbDTOBuilder()
            .login(DEFAULT_LOGIN)
            .password(RandomStringUtils.random(60))
            .activated(true)
            .email(DEFAULT_EMAIL)
            .firstName(DEFAULT_FIRSTNAME)
            .lastName(DEFAULT_LASTNAME)
            .imageUrl(DEFAULT_IMAGEURL)
            .langKey(DEFAULT_LANGKEY)
            .build();
    }

    @Test
    public void assertThatUserMustExistToResetPassword() {
        userRepository.save(user);
        Optional<UserDbDTO> maybeUser = userService.requestPasswordReset("invalid.login@localhost");
        assertThat(maybeUser).isNotPresent();

        maybeUser = userService.requestPasswordReset(user.getEmail());
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.orElse(null).getEmail()).isEqualTo(user.getEmail());
        assertThat(maybeUser.orElse(null).getResetDate()).isNotNull();
        assertThat(maybeUser.orElse(null).getResetKey()).isNotNull();
    }

    @Test
    public void assertThatOnlyActivatedUserCanRequestPasswordReset() {
        user = new UserDbDTO.UserDbDTOBuilder(user)
            .activated(false).build();
        userRepository.save(user);

        Optional<UserDbDTO> maybeUser = userService.requestPasswordReset(user.getLogin());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    public void assertThatResetKeyMustNotBeOlderThan24Hours() {
        Instant daysAgo = Instant.now().minus(25, ChronoUnit.HOURS);
        String resetKey = RandomUtil.generateResetKey();
        user = new UserDbDTO.UserDbDTOBuilder(user)
            .activated(true)
            .resetDate(daysAgo)
            .resetKey(resetKey)
            .build();
        userRepository.save(user);

        Optional<UserDbDTO> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    public void assertThatResetKeyMustBeValid() {
        Instant daysAgo = Instant.now().minus(25, ChronoUnit.HOURS);
        user = new UserDbDTO.UserDbDTOBuilder(user)
            .activated(true)
            .resetDate(daysAgo)
            .resetKey("1234")
            .build();
        userRepository.save(user);

        Optional<UserDbDTO> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isNotPresent();
        userRepository.delete(user);
    }

    @Test
    public void assertThatUserCanResetPassword() {
        String oldPassword = user.getPassword();
        Instant daysAgo = Instant.now().minus(2, ChronoUnit.HOURS);
        String resetKey = RandomUtil.generateResetKey();
        user = new UserDbDTO.UserDbDTOBuilder(user)
            .activated(true)
            .resetDate(daysAgo)
            .resetKey(resetKey)
            .build();
        userRepository.save(user);

        Optional<UserDbDTO> maybeUser = userService.completePasswordReset("johndoe2", user.getResetKey());
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.orElse(null).getResetDate()).isNull();
        assertThat(maybeUser.orElse(null).getResetKey()).isNull();
        assertThat(maybeUser.orElse(null).getPassword()).isNotEqualTo(oldPassword);

        userRepository.delete(user);
    }

    @Test
    public void assertThatNotActivatedUsersWithNotNullActivationKeyCreatedBefore3DaysAreDeleted() {
        Instant now = Instant.now();
        user = new UserDbDTO.UserDbDTOBuilder(user)
            .activated(false)
            .activationKey(RandomStringUtils.random(20))
            .build();
        UserDbDTO dbUser = userRepository.save(user);
        dbUser.setCreatedDate(now.minus(4, ChronoUnit.DAYS));
        userRepository.save(user);
        Instant threeDaysAgo = now.minus(3, ChronoUnit.DAYS);
        List<UserDbDTO> users = userRepository.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(threeDaysAgo);
        assertThat(users).isNotEmpty();
        userService.removeNotActivatedUsers();
        users = userRepository.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(threeDaysAgo);
        assertThat(users).isEmpty();

        // Verify Elasticsearch mock
        verify(mockUserSearchRepository, times(1)).delete(user);
    }

    @Test
    public void assertThatNotActivatedUsersWithNullActivationKeyCreatedBefore3DaysAreNotDeleted() {
        Instant now = Instant.now();
        user = new UserDbDTO.UserDbDTOBuilder(user)
            .activated(false)
            .build();
        UserDbDTO dbUser = userRepository.save(user);
        dbUser.setCreatedDate(now.minus(4, ChronoUnit.DAYS));
        userRepository.save(user);
        Instant threeDaysAgo = now.minus(3, ChronoUnit.DAYS);
        List<UserDbDTO> users = userRepository.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(threeDaysAgo);
        assertThat(users).isEmpty();
        userService.removeNotActivatedUsers();
        Optional<UserDbDTO> maybeDbUser = userRepository.findById(dbUser.getId());
        assertThat(maybeDbUser).contains(dbUser);

        // Verify Elasticsearch mock
        verify(mockUserSearchRepository, never()).delete(user);
    }

    @Test
    public void assertThatAnonymousUserIsNotGet() {
        user = new UserDbDTO.UserDbDTOBuilder(user)
            .login(Constants.ANONYMOUS_USER)
            .build();
        final Optional<UserDbDTO> oneByLogin = userRepository.findOneByLogin(Constants.ANONYMOUS_USER);
        if (oneByLogin.isEmpty()) {
            userRepository.save(user);
        }
        final PageRequest pageable = PageRequest.of(0, (int) userRepository.count());
        final Page<UserDTO> allManagedUsers = userService.getAllManagedUsers(pageable);
        assertThat(allManagedUsers.getContent().stream()
            .noneMatch(user -> Constants.ANONYMOUS_USER.equals(user.getLogin())))
            .isTrue();
    }

}
