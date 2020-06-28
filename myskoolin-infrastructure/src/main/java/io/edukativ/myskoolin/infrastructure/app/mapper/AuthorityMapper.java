package io.edukativ.myskoolin.infrastructure.app.mapper;

import io.edukativ.myskoolin.domain.commons.entity.Authority;
import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorityMapper {

    Authority dbDtoToDomain(AuthorityDbDTO authority);

    default String dbDtoToDto(AuthorityDbDTO authority) {
        return authority.getName();
    }

    //    default AuthorityDbDTO dtoToDbDto(String authorityName) {
//
//    }
//
//    default Authority dtoToDomain(String authorityName) {
//
//    }
//
    AuthorityDbDTO domainToDbDto(Authority authority);

    default String domainToDto(Authority authority) {
        return authority.getName();
    }


    Set<Authority> dbDtosToDomains(Set<AuthorityDbDTO> authority);

    default Set<Authority> dtosToDomains(Set<String> authorities) {
        return authorities.stream().map(Authority::new).collect(Collectors.toSet());
    }

    default Set<String> domainsToDtos(Set<Authority> authorities) {
        return authorities.stream().map(Authority::getName).collect(Collectors.toSet());
    }

    default Set<AuthorityDbDTO> dtosToDbDtos(Set<String> authorities) {
        return authorities.stream().map(AuthorityDbDTO::new).collect(Collectors.toSet());
    }

    default Set<String> dbDtosToDtos(Set<AuthorityDbDTO> authorities) {
        return authorities.stream().map(AuthorityDbDTO::getName).collect(Collectors.toSet());
    }


    default Set<AuthorityDbDTO> domainsToDbDtos(Set<Authority> authorities) {
        return authorities
                .stream()
                .map(authority -> new AuthorityDbDTO(authority.getName()))
                .collect(Collectors.toSet());
    }

}
