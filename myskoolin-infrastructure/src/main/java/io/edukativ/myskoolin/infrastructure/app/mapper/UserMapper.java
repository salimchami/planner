package io.edukativ.myskoolin.infrastructure.app.mapper;

import io.edukativ.myskoolin.domain.commons.entity.User;
import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link UserDbDTO} and its DTO called {@link UserDTO}.
 * <p>
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {

    private final AddressMapper addressMapper;
    private final AuthorityMapper authorityMapper;
    private final ObjectIdMapper objectIdMapper;

    public UserMapper(AddressMapper addressMapper, AuthorityMapper authorityMapper, ObjectIdMapper objectIdMapper) {
        this.addressMapper = addressMapper;
        this.authorityMapper = authorityMapper;
        this.objectIdMapper = objectIdMapper;
    }

    public List<UserDTO> usersToUserDTOs(List<UserDbDTO> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(this::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO userToUserDTO(UserDbDTO user) {
        return new UserDTO(user);
    }

    public List<UserDbDTO> userDTOsToUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .filter(Objects::nonNull)
                .map(this::userDTOToUser)
                .collect(Collectors.toList());
    }

    public UserDbDTO userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            return new UserDbDTO.UserDbDTOBuilder()
                    .id(userDTO.getId())
                    .login(userDTO.getLogin())
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .email(userDTO.getEmail())
                    .imageUrl(userDTO.getImageUrl())
                    .activated(userDTO.isActivated())
                    .langKey(userDTO.getLangKey())
                    .authorities(this.authoritiesFromStrings(userDTO.getAuthorities()))
                    .build();
        }
    }


    private Set<AuthorityDbDTO> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<AuthorityDbDTO> authorities = new HashSet<>();

        if (authoritiesAsString != null) {
            authorities = authoritiesAsString.stream().map(string -> {
                AuthorityDbDTO auth = new AuthorityDbDTO();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return authorities;
    }

    public UserDbDTO userFromId(String id) {
        if (id == null) {
            return null;
        }
        return new UserDbDTO.UserDbDTOBuilder().id(id).build();
    }

    public User dbDtoToDomain(UserDbDTO userDbDTO) {
        User user = new User();
        user.setId(userDbDTO.getId());
        user.setLogin(userDbDTO.getLogin());
        user.setClientId(objectIdMapper.map(userDbDTO.getClientId()));
        user.setGender(userDbDTO.getGender());
        user.setFirstName(userDbDTO.getFirstName());
        user.setLastName(userDbDTO.getLastName());
        user.setCellPhone(userDbDTO.getCellPhone());
        user.setHomePhone(userDbDTO.getHomePhone());
        user.setNationality(userDbDTO.getNationality());
        user.setEmail(userDbDTO.getEmail());
        user.setActivated(userDbDTO.isActivated());
        user.setLangKey(userDbDTO.getLangKey());
        user.setPassword(userDbDTO.getPassword());
        user.setImageUrl(userDbDTO.getImageUrl());
        user.setActivationKey(userDbDTO.getActivationKey());
        user.setResetKey(userDbDTO.getResetKey());
        user.setResetDate(userDbDTO.getResetDate());
        user.setAuthorities(authorityMapper.dbDtosToDomains(userDbDTO.getAuthorities()));
        user.setAddress(addressMapper.dbDtoToDomain(userDbDTO.getAddress()));
        user.setBirthDate(userDbDTO.getBirthDate());
        user.setDeleted(userDbDTO.isDeleted());
        user.setArchived(userDbDTO.isArchived());
        return user;
    }

    public UserDbDTO domainToDbDTO(User user) {
        return new UserDbDTO.UserDbDTOBuilder()
                .id(user.getId())
                .login(user.getLogin())
                .clientId(new ObjectId(user.getClientId()))
                .gender(user.getGender())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .cellPhone(user.getCellPhone())
                .homePhone(user.getHomePhone())
                .nationality(user.getNationality())
                .email(user.getEmail())
                .langKey(user.getLangKey())
                .password(user.getPassword())
                .imageUrl(user.getImageUrl())
                .activationKey(user.getActivationKey())
                .resetKey(user.getResetKey())
                .resetDate(user.getResetDate())
                .authorities(authorityMapper.domainsToDbDtos(user.getAuthorities()))
                .address(addressMapper.domainToDbDto(user.getAddress()))
                .birthDate(user.getBirthDate())
                .build();
    }

}
