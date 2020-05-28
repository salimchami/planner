package io.edukativ.schooling.myskoolin.domain.timetabling;

import io.edukativ.schooling.myskoolin.domain.entity.*;
import io.edukativ.schooling.myskoolin.domain.providers.GradeProvider;
import io.edukativ.schooling.myskoolin.domain.providers.SchoolRoomProvider;
import io.edukativ.schooling.myskoolin.domain.providers.UserProvider;
import io.edukativ.schooling.myskoolin.domain.providers.schoolclasses.SchoolClassProvider;
import io.edukativ.schooling.myskoolin.domain.providers.subjects.SubjectProvider;
import io.edukativ.schooling.myskoolin.domain.vo.TimeTableOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TimeTablesGenerationTest {

    private List<SchoolRoom> allSchoolRooms;
    private List<Subject> subjects;
    private List<User> teachers;
    private Map<Grade, TimeTableOptions> timeTableOptionsByGrade;
    private TimeTablesGeneration sut;
    private TimeTableOptions clientTimeTableOptions;

    @BeforeEach
    void setUp() {
        List<Grade> grades = GradeProvider.allGrades();
        allSchoolRooms = SchoolRoomProvider.allSchoolRooms();
        subjects = SubjectProvider.allSubjects();
        teachers = UserProvider.teacherUsers(grades);
        clientTimeTableOptions = Client.defaultTimeTableOptions();
        timeTableOptionsByGrade = GradeProvider.timeTableOptionsByGrade(clientTimeTableOptions);
    }

    @RepeatedTest(50)
    void should_generateAllTimeTables_for_sixieme_grade_with_one_school_class() {
        given(1, 0, 0, 0);
        sut.generateTimeTables();
        then(allSchoolRooms, true);
    }

    @RepeatedTest(50)
    void should_generateAllTimeTables_for_sixieme_grade_with_4_school_classes() {
        given(5, 0, 0, 0);
        sut.generateTimeTables();
        then(allSchoolRooms, true);
    }

    @RepeatedTest(50)
    void should_not_generateAllTimeTables_for_sixieme_grade_with_5_school_classes() {
        given(9, 0, 0, 0);
        sut.generateTimeTables();
        then(allSchoolRooms, false);
    }

    @RepeatedTest(50)
    void should_generateAllTimeTables_for_each_grade_with_one_school_class() {
        given(1, 0, 1, 0);
        sut.generateTimeTables();
        then(allSchoolRooms, true);
    }

    @RepeatedTest(50)
    void should_generateAllTimeTables_for_sixieme_and_cinquieme_1_each() {
        given(1, 1, 1, 1);
        sut.generateTimeTables();
        then(allSchoolRooms, true);
    }

    @RepeatedTest(5)
    void should_generateAllTimeTables_for_each_grade_with_ten_school_classes() {
        given(3, 3, 3, 3);
        sut.generateTimeTables();
        then(allSchoolRooms, false);
    }

    private void given(int sixiemes, int cinquiemes, int quatriemes, int troisiemes) {
        List<SchoolClass> schoolClasses = SchoolClassProvider.schoolClasses(teachers, sixiemes, cinquiemes, quatriemes, troisiemes);
        sut = new TimeTablesGeneration(timeTableOptionsByGrade, schoolClasses, subjects, teachers, allSchoolRooms);
    }

    private void then(List<SchoolRoom> schoolRooms, boolean expectedValid) {
        final List<TimeTableValidation> validations = sut.timeTablesAreValid(clientTimeTableOptions, schoolRooms);
        validations.forEach(validation -> {
            if (!validation.isValid()) {
                System.out.println(validation.validationToString());
                validation.subjectValidationToString();
            }
        });
        final boolean valid = validations.stream().allMatch(TimeTableValidation::isValid);
        assertThat(valid).isEqualTo(expectedValid);
    }
}
