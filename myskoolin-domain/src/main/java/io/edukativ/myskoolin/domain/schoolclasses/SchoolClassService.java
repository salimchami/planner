package io.edukativ.myskoolin.domain.schoolclasses;

import io.edukativ.myskoolin.domain.commons.entity.User;

import java.util.List;

public class SchoolClassService implements SchoolClassAPI {

    private final SchoolClassSPI schoolClassSPI;

    public SchoolClassService(SchoolClassSPI schoolClassSPI) {
        this.schoolClassSPI = schoolClassSPI;
    }

}
