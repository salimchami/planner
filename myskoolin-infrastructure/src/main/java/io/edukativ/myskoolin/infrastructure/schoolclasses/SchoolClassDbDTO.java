package io.edukativ.myskoolin.infrastructure.schoolclasses;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumSchoolClassNotation;
import io.edukativ.myskoolin.infrastructure.grades.GradeDbDTO;
import io.edukativ.myskoolin.infrastructure.dailybook.DailyBookTimeSlotDbVO;
import io.edukativ.myskoolin.infrastructure.grades.GradeSerieDbVO;
import io.edukativ.myskoolin.infrastructure.timetabling.SchoolClassTimeTableDbVO;
import io.edukativ.myskoolin.infrastructure.teachers.TeachersBySubjectDbVO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A School Class.
 */
@Document(collection = SchoolClassDbDTO.MONGODB_COLLECTION_NAME)
public class SchoolClassDbDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String MONGODB_COLLECTION_NAME = "school_classes";
    public static final String MONGO_FIELD_CLIENT_ID = "clientId";
    public static final String MONGO_FIELD_COURSES_START_DATE = "courses_start_date";
    public static final String MONGO_FIELD_COURSES_END_DATE = "courses_end_date";
    public static final String MONGO_FIELD_COUNCILS_DATES = "councils_dates";
    public static final String MONGO_FIELD_CUSTOM_NAME = "custom_name";
    public static final String MONGO_FIELD_NAME = "name";
    public static final String MONGO_FIELD_DELETED = "deleted";
    public static final String MONGO_FIELD_HEAD_TEACHERS = "head_teachers";
    public static final String MONGO_FIELD_GRADE = "grade";
    public static final String MONGO_FIELD_GRADE_SERIE = "grade_serie";
    public static final String MONGO_FIELD_TIMETABLE = "timetable";
    public static final String MONGO_FIELD_DAILY_BOOK = "daily_book";
    public static final String MONGO_FIELD_NOTATION = "notation";
    public static final String MONGO_FIELD_TEACHERS_BY_SUBJECTS = "teachers_by_subjects";

    @Id
    private ObjectId id;

    @Field(MONGO_FIELD_CLIENT_ID)
    @Indexed
    @NotNull
    private ObjectId clientId;

    @Field(MONGO_FIELD_COURSES_START_DATE)
    private ZonedDateTime coursesStartDate;

    @Field(MONGO_FIELD_COURSES_END_DATE)
    private ZonedDateTime coursesEndDate;

    @Field(MONGO_FIELD_COUNCILS_DATES)
    private List<ZonedDateTime> councilsDates;

    @Field(MONGO_FIELD_CUSTOM_NAME)
    private String customName;

    @NotNull
    @Field(MONGO_FIELD_NAME)
    private String name;

    @NotNull
    @Field(MONGO_FIELD_DELETED)
    private Boolean deleted = false;

    @Field(MONGO_FIELD_HEAD_TEACHERS)
    private List<ObjectId> headTeachers;

    @NotNull
    @DBRef
    @Field(MONGO_FIELD_GRADE)
    private GradeDbDTO grade;

    @Field(MONGO_FIELD_GRADE_SERIE)
    private GradeSerieDbVO gradeSerie;

    @Field(MONGO_FIELD_TIMETABLE)
    private SchoolClassTimeTableDbVO timetable;

    @Field(MONGO_FIELD_DAILY_BOOK)
    private List<DailyBookTimeSlotDbVO> dailyBook;

    @Field(MONGO_FIELD_NOTATION)
    private EnumSchoolClassNotation notation = EnumSchoolClassNotation.QUARTER;

    @Field(MONGO_FIELD_TEACHERS_BY_SUBJECTS)
    private List<TeachersBySubjectDbVO> teachersBySubjects;

    public SchoolClassDbDTO() {
    }

    public SchoolClassDbDTO(ObjectId id, ObjectId clientId, ZonedDateTime coursesStartDate,
                            ZonedDateTime coursesEndDate, List<ZonedDateTime> councilsDates, String customName,
                            String name, Boolean deleted, List<ObjectId> headTeachers, GradeDbDTO grade,
                            GradeSerieDbVO gradeSerie, SchoolClassTimeTableDbVO timetable, List<DailyBookTimeSlotDbVO> dailyBook,
                            EnumSchoolClassNotation notation, List<TeachersBySubjectDbVO> teachersBySubjects) {
        this.id = id;
        this.clientId = clientId;
        this.coursesStartDate = coursesStartDate;
        this.coursesEndDate = coursesEndDate;
        this.councilsDates = councilsDates;
        this.customName = customName;
        this.name = name;
        this.deleted = deleted;
        this.headTeachers = headTeachers;
        this.grade = grade;
        this.gradeSerie = gradeSerie;
        this.timetable = timetable;
        this.dailyBook = dailyBook;
        this.notation = notation;
        this.teachersBySubjects = teachersBySubjects;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }

    public ZonedDateTime getCoursesStartDate() {
        return coursesStartDate;
    }

    public void setCoursesStartDate(ZonedDateTime coursesStartDate) {
        this.coursesStartDate = coursesStartDate;
    }

    public ZonedDateTime getCoursesEndDate() {
        return coursesEndDate;
    }

    public void setCoursesEndDate(ZonedDateTime coursesEndDate) {
        this.coursesEndDate = coursesEndDate;
    }

    public List<ZonedDateTime> getCouncilsDates() {
        return councilsDates;
    }

    public void setCouncilsDates(List<ZonedDateTime> councilsDates) {
        this.councilsDates = councilsDates;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<ObjectId> getHeadTeachers() {
        return headTeachers;
    }

    public void setHeadTeachers(List<ObjectId> headTeachers) {
        this.headTeachers = headTeachers;
    }

    public GradeDbDTO getGrade() {
        return grade;
    }

    public void setGrade(GradeDbDTO grade) {
        this.grade = grade;
    }

    public GradeSerieDbVO getGradeSerie() {
        return gradeSerie;
    }

    public void setGradeSerie(GradeSerieDbVO gradeSerie) {
        this.gradeSerie = gradeSerie;
    }

    public SchoolClassTimeTableDbVO getTimetable() {
        return timetable;
    }

    public void setTimetable(SchoolClassTimeTableDbVO timetable) {
        this.timetable = timetable;
    }

    public EnumSchoolClassNotation getNotation() {
        return notation;
    }

    public void setNotation(EnumSchoolClassNotation notation) {
        this.notation = notation;
    }

    public List<DailyBookTimeSlotDbVO> getDailyBook() {
        return dailyBook;
    }

    public void setDailyBook(List<DailyBookTimeSlotDbVO> dailyBook) {
        this.dailyBook = dailyBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolClassDbDTO schoolClass = (SchoolClassDbDTO) o;
        return !(schoolClass.id == null || id == null) && Objects.equals(id, schoolClass.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", coursesStartDate='" + coursesStartDate + "'" +
                ", coursesEndDate='" + coursesEndDate + "'" +
                ", customName='" + customName + "'" +
                ", name='" + name + "'" +
                ", deleted='" + deleted + "'" +
                '}';
    }

    public List<TeachersBySubjectDbVO> getTeachersBySubjects() {
        return teachersBySubjects;
    }

    public void setTeachersBySubjects(List<TeachersBySubjectDbVO> teachersBySubjects) {
        this.teachersBySubjects = teachersBySubjects;
    }
}
