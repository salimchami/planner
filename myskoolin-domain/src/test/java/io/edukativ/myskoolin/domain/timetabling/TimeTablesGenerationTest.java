package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.providers.GradeTestProvider;
import io.edukativ.myskoolin.domain.providers.SchoolRoomTestProvider;
import io.edukativ.myskoolin.domain.providers.UserTestProvider;
import io.edukativ.myskoolin.domain.providers.schoolclasses.SchoolClassTestProvider;
import io.edukativ.myskoolin.domain.providers.subjects.SubjectTestProvider;
import io.edukativ.myskoolin.domain.entity.*;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.Map;

class TimeTablesGenerationTest {

    private List<SchoolRoom> allSchoolRooms;
    private List<Subject> subjects;
    private List<Teacher> teachers;
    private Map<Grade, TimeTableOptions> timeTableOptionsByGrade;
    private TimeTablesGeneration sut;
    private TimeTableOptions clientTimeTableOptions;

    @BeforeEach
    void setUp() {
        List<Grade> grades = GradeTestProvider.allGrades();
        allSchoolRooms = SchoolRoomTestProvider.allSchoolRooms();
        subjects = SubjectTestProvider.allSubjects();
        teachers = UserTestProvider.teacherUsers(grades);
        clientTimeTableOptions = Client.defaultTimeTableOptions();
        timeTableOptionsByGrade = GradeTestProvider.timeTableOptionsByGrade(clientTimeTableOptions);
    }

    @RepeatedTest(50)
    void should_generateAllTimeTables_for_sixieme_grade_with_one_school_class() {
        given(1, 0, 0, 0);
        // sut.generateTimeTables();
        then(allSchoolRooms, true);
    }

    @RepeatedTest(50)
    void should_generateAllTimeTables_for_sixieme_grade_with_4_school_classes() {
        given(5, 0, 0, 0);
        // sut.generateTimeTables();
        then(allSchoolRooms, true);
    }

    @RepeatedTest(50)
    void should_not_generateAllTimeTables_for_sixieme_grade_with_5_school_classes() {
        given(9, 0, 0, 0);
        // sut.generateTimeTables();
        then(allSchoolRooms, false);
    }

    @RepeatedTest(50)
    void should_generateAllTimeTables_for_each_grade_with_one_school_class() {
        given(1, 0, 1, 0);
        // sut.generateTimeTables();
        then(allSchoolRooms, true);
    }

    @RepeatedTest(50)
    void should_generateAllTimeTables_for_sixieme_and_cinquieme_1_each() {
        given(1, 1, 1, 1);
        // sut.generateTimeTables();
        then(allSchoolRooms, true);
    }

    @RepeatedTest(5)
    void should_generateAllTimeTables_for_each_grade_with_ten_school_classes() {
        given(3, 3, 3, 3);
        // sut.generateTimeTables();
        then(allSchoolRooms, false);
    }

    private void given(int sixiemes, int cinquiemes, int quatriemes, int troisiemes) {
        List<SchoolClass> schoolClasses = SchoolClassTestProvider.schoolClasses(teachers, sixiemes, cinquiemes, quatriemes, troisiemes);
//        sut = new TimeTablesGeneration(timeTableOptionsByGrade, schoolClasses, subjects, teachers, allSchoolRooms);
    }

    private void then(List<SchoolRoom> schoolRooms, boolean expectedValid) {
//        final List<TimeTableValidation> validations = sut.timeTablesAreValid(clientTimeTableOptions, schoolRooms);
//        validations.forEach(validation -> {
//            if (!validation.isValid()) {
//                System.out.println(validation.validationToString());
//                validation.subjectValidationToString();
//            }
//        });
//        final boolean valid = validations.stream().allMatch(TimeTableValidation::isValid);
//        assertThat(valid).isEqualTo(expectedValid);
    }
}
