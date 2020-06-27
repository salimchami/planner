package io.edukativ.myskoolin.domain.commons;

import io.edukativ.myskoolin.domain.commons.entity.Authority;

import java.util.Optional;

public interface AuthoritySPI {

    Authority findByName(String authorityName);

    Optional<Authority> findById(String authorityName);
}
