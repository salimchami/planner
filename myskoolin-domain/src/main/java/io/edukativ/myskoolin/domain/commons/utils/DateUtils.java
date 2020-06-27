package io.edukativ.myskoolin.domain.commons.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    private DateUtils() {
        // private constructor
    }

    public static String concatClientIdAndNowTimestamp(String clientId) {
        return clientId.concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHhmmssSSS")));
    }
}
