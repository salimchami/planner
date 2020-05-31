package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.entity.Subject;
import io.edukativ.myskoolin.domain.vo.EnumSchoolRoomsTypes;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class SchoolingResources {

    private Map<EnumSchoolRoomsTypes, Integer> schoolRoomsByType;
    private Map<Subject, Integer> teachersBySubject;

    public SchoolingResources(Map<EnumSchoolRoomsTypes, Integer> schoolRoomsByType,
                              Map<Subject, Integer> teachersBySubject) {
        this.schoolRoomsByType = schoolRoomsByType;
        this.teachersBySubject = teachersBySubject;
    }

    public Map<EnumSchoolRoomsTypes, Integer> getSchoolRoomsByType() {
        return Collections.unmodifiableMap(schoolRoomsByType);
    }

    public Map<Subject, Integer> getTeachersBySubject() {
        return Collections.unmodifiableMap(teachersBySubject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchoolingResources that = (SchoolingResources) o;
        return Objects.equals(schoolRoomsByType, that.schoolRoomsByType) &&
                Objects.equals(teachersBySubject, that.teachersBySubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolRoomsByType, teachersBySubject);
    }

}
