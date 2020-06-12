package io.edukativ.myskoolin.infrastructure.app.mapper;

import io.edukativ.myskoolin.domain.entity.Authority;
import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    Set<Authority> dbDtosToDomains(Set<AuthorityDbDTO> authority);

    Authority map(AuthorityDbDTO authority);
}
