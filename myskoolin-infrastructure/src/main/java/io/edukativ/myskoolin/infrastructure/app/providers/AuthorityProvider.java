package io.edukativ.myskoolin.infrastructure.app.providers;

import io.edukativ.myskoolin.domain.commons.AuthoritySPI;
import io.edukativ.myskoolin.domain.commons.entity.Authority;
import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.app.repository.AuthorityRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorityProvider implements AuthoritySPI {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    public AuthorityProvider(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    @Override
    public Authority findByName(String authorityName) {
        final Optional<AuthorityDbDTO> optAuthority = authorityRepository.findById(authorityName);
        return optAuthority.map(authorityMapper::dbDtoToDomain).orElseThrow();
    }

    @Override
    public Optional<Authority> findById(String authorityName) {
        final Optional<AuthorityDbDTO> optAuthority = authorityRepository.findById(authorityName);
        return optAuthority.map(authorityMapper::dbDtoToDomain);
    }
}
