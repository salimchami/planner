package io.edukativ.myskoolin.infrastructure.config.dbmigrations;

import io.edukativ.myskoolin.infrastructure.app.dto.AuthorityDbDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.FeatureDbDTO;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.PricingDbDTO;
import io.edukativ.myskoolin.infrastructure.common.dto.OptionDbDTO;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolrooms.SchoolRoomDbDTO;
import io.edukativ.myskoolin.infrastructure.students.StudentDbDTO;
import io.edukativ.myskoolin.infrastructure.subjects.SubjectDbDTO;
import io.edukativ.myskoolin.infrastructure.teachers.TeacherDbDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class MigrationTempData {

    public static final List<PricingDbDTO> pricings = new ArrayList<>();
    public static final List<UserDbDTO> users = new ArrayList<>();
    public static final List<AuthorityDbDTO> authorities = new ArrayList<>();
    public static final List<FeatureDbDTO> features = new ArrayList<>();
    public static final List<ClientDbDTO> clients = new ArrayList<>();
    public static final List<GradeDbDTO> grades = new ArrayList<>();
    public static final List<SchoolRoomDbDTO> schoolRooms = new ArrayList<>();
    public static final List<SubjectDbDTO> subjects = new ArrayList<>();
    public static final List<OptionDbDTO> options = new ArrayList<>();
    public static final List<TeacherDbDTO> teachers = new ArrayList<>();
    public static List<StudentDbDTO> students = new ArrayList<>();
    public static List<SchoolClassDbDTO> schoolClasses = new ArrayList<>();

    private MigrationTempData() {
    }

    public static List<TeacherDbDTO> teachersByClientId(String clientId) {
        return teachers
                .stream()
                .filter(teacherDbDTO -> teacherDbDTO.getClientId().toString().equals(clientId))
                .collect(Collectors.toList());
    }
}
