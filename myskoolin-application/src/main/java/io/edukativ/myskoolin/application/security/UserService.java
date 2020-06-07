package io.edukativ.myskoolin.application.security;

import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.app.exceptions.EmailAlreadyUsedException;
import io.edukativ.myskoolin.infrastructure.app.exceptions.InvalidPasswordException;
import io.edukativ.myskoolin.infrastructure.app.exceptions.UsernameAlreadyUsedException;
import io.edukativ.myskoolin.infrastructure.app.repository.AuthorityRepository;
import io.edukativ.myskoolin.infrastructure.app.repository.search.UserSearchRepository;
import io.edukativ.myskoolin.infrastructure.config.Constants;
import io.edukativ.myskoolin.infrastructure.app.repository.UserRepository;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDTO;
import io.github.jhipster.security.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserSearchRepository userSearchRepository;

    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserSearchRepository userSearchRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSearchRepository = userSearchRepository;
        this.authorityRepository = authorityRepository;
    }

    public Optional<UserDbDTO> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
                .map(dbUser -> {
                    UserDbDTO user = new UserDbDTO.UserDbDTOBuilder(dbUser)
                            .activated(true)
                            .activationKey(null)
                            .build();
                    // activate given user for the registration key.
                    userRepository.save(user);
                    userSearchRepository.save(user);
                    log.debug("Activated user: {}", user);
                    return user;
                });
    }

    public Optional<UserDbDTO> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userRepository.findOneByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(dbUser -> {
                    UserDbDTO user = new UserDbDTO.UserDbDTOBuilder(dbUser)
                            .password(passwordEncoder.encode(newPassword))
                            .resetKey(null)
                            .resetDate(null).build();
                    userRepository.save(user);
                    return dbUser;
                });
    }

    public Optional<UserDbDTO> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
                .filter(UserDbDTO::isActivated)
                .map(dbUser -> {
                    UserDbDTO user = new UserDbDTO.UserDbDTOBuilder(dbUser)
                            .resetKey(RandomUtil.generateResetKey())
                            .resetDate(Instant.now())
                            .build();
                    userRepository.save(user);
                    return dbUser;
                });
    }

    public UserDbDTO registerUser(UserDTO userDTO, String password) {
        userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new UsernameAlreadyUsedException();
            }
        });
        userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new EmailAlreadyUsedException();
            }
        });
        String encryptedPassword = passwordEncoder.encode(password);
        Set<AuthorityDbDTO> authorities = new HashSet<>();
        authorityRepository.findAllById(userDTO.getAuthorities()).forEach(authorities::add);
        UserDbDTO newUser = new UserDbDTO.UserDbDTOBuilder()
                .login(userDTO.getLogin().toLowerCase())
                .password(encryptedPassword)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .imageUrl(userDTO.getImageUrl())
                .langKey(userDTO.getLangKey())
                .activated(false)
                .authorities(authorities)
                .activationKey(RandomUtil.generateActivationKey())
                .build();
        userRepository.save(newUser);
        userSearchRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    private boolean removeNonActivatedUser(UserDbDTO existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        return true;
    }

    public UserDbDTO createUser(UserDTO userDTO) {
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        Set<AuthorityDbDTO> authorities = null;
        if (userDTO.getAuthorities() != null) {
            authorities = userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }
        UserDbDTO user = new UserDbDTO.UserDbDTOBuilder()
                .login(userDTO.getLogin().toLowerCase())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .imageUrl(userDTO.getImageUrl())
                .langKey(userDTO.getLangKey())
                .password(encryptedPassword)
                .resetKey(RandomUtil.generateResetKey())
                .resetDate(Instant.now())
                .activated(true)
                .authorities(authorities)
                .build();
        userRepository.save(user);
        userSearchRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
                .findById(userDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(dbUser -> {
                    Set<AuthorityDbDTO> managedAuthorities = dbUser.getAuthorities();
                    managedAuthorities.clear();
                    userDTO.getAuthorities().stream()
                            .map(authorityRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(managedAuthorities::add);

                    UserDbDTO user = new UserDbDTO.UserDbDTOBuilder(dbUser)
                            .login(userDTO.getLogin().toLowerCase())
                            .firstName(userDTO.getFirstName())
                            .lastName(userDTO.getLastName())
                            .email(userDTO.getEmail())
                            .imageUrl(userDTO.getImageUrl())
                            .activated(userDTO.isActivated())
                            .langKey(userDTO.getLangKey())
                            .authorities(managedAuthorities)
                            .build();

                    userRepository.save(user);
                    userSearchRepository.save(user);
                    log.debug("Changed Information for User: {}", dbUser);
                    return dbUser;
                })
                .map(UserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            userSearchRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user.
     * @param lastName  last name of user.
     * @param email     email id of user.
     * @param langKey   language key.
     * @param imageUrl  image URL of user.
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .ifPresent(dbUser -> {
                    UserDbDTO user = new UserDbDTO.UserDbDTOBuilder(dbUser)
                            .firstName(firstName)
                            .lastName(lastName)
                            .email(email)
                            .langKey(langKey)
                            .imageUrl(imageUrl)
                            .build();
                    userRepository.save(user);
                    userSearchRepository.save(user);
                    log.debug("Changed Information for User: {}", user);
                });
    }


    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .ifPresent(dbUser -> {
                    String currentEncryptedPassword = dbUser.getPassword();
                    if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    UserDbDTO user = new UserDbDTO.UserDbDTOBuilder(dbUser)
                            .password(encryptedPassword)
                            .build();
                    userRepository.save(user);
                    log.debug("Changed password for User: {}", user);
                });
    }

    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    public Optional<UserDbDTO> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public Optional<UserDbDTO> getCurrentUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(s -> {
            return userRepository.findOneByLogin(s);
        });
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        userRepository
                .findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
                .forEach(user -> {
                    log.debug("Deleting not activated user {}", user.getLogin());
                    userRepository.delete(user);
                    userSearchRepository.delete(user);
                });
    }

    /**
     * Gets a list of all the authorities.
     *
     * @return a list of all the authorities.
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(AuthorityDbDTO::getName).collect(Collectors.toList());
    }
}
