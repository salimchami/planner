package io.edukativ.myskoolin.domain.commons;

import java.util.List;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ADMINISTRATION = "ROLE_ADMINISTRATION";
    public static final String INFIRMARY = "ROLE_INFIRMARY";
    public static final String SCHOOL_LIFE = "ROLE_SCHOOL_LIFE";
    public static final String SCHOOLME_ADMIN = "ROLE_SCHOOLME_ADMIN";
    public static final String STUDENTS = "ROLE_STUDENTS";
    public static final String STUDENT_RESPONSIBLE = "ROLE_STUDENT_RESPONSIBLE";
    public static final String TEACHERS = "ROLE_TEACHERS";
    public static final List<String> ALL_AUTHORITIES = List.of(ADMINISTRATION, INFIRMARY, SCHOOL_LIFE, SCHOOLME_ADMIN,
            STUDENTS, STUDENT_RESPONSIBLE, TEACHERS);

    private AuthoritiesConstants() {
    }

    public static List<String> allAuthenticatedAuthorities() {
        return List.of(
                ADMINISTRATION,
                INFIRMARY,
                SCHOOL_LIFE,
                SCHOOLME_ADMIN,
                STUDENTS,
                STUDENT_RESPONSIBLE,
                TEACHERS
        );
    }
}
