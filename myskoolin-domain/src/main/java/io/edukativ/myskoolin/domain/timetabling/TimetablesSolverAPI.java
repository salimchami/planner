package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;

import java.util.List;

public interface TimetablesSolverAPI {

    void solveForAllSchoolClasses(String clientId, List<SchoolRoom> schoolRooms,
                                  List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses, TimeTableOptions timeTableOptions);

    void solveForSchoolClass(String schoolClassId, String clientId, List<SchoolRoom> schoolRooms,
                             List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses, TimeTableOptions timeTableOptions);

    void stopSolving(String timeTableId);

    void stopSolving(List<String> timeTableIds);

}
