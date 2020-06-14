package io.edukativ.myskoolin.infrastructure.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String APPLICATION_NAME = "myskoolin";

    public static final int COUNTRY_RESOURCE_CITYNAME_MIN_LENGTH = 2;
    public static final int TEACHER_NAME_FOR_SEARCH_MIN_LENGTH = 2;


    private Constants() {
    }
}
