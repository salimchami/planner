package io.edukativ.myskoolin.application.security;

import io.edukativ.myskoolin.domain.commons.exceptions.MyskoolinException;

public class CurrentUserNotFoundException extends MyskoolinException {

    public CurrentUserNotFoundException(String message) {
        super(message);
    }
}
