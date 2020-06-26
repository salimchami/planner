package io.edukativ.myskoolin.infrastructure.app.mapper;

import io.edukativ.myskoolin.domain.entity.Authority;
import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

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

    Authority dbDtoToDomain(AuthorityDbDTO authority);
}
