package io.edukativ.myskoolin.domain.commons;

import io.edukativ.myskoolin.domain.entity.Authority;

public interface AuthoritySPI {

    Authority findByName(String authorityName);
}
