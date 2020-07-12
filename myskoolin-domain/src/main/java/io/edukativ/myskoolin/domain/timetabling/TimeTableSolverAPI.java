package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.solver.SolverStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TimeTableSolverAPI {

    void solveForAllSchoolClasses(String clientId, List<SchoolRoom> schoolRooms,
                                  List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses, TimeTableOptions timeTableOptions);

    void solveForSchoolClass(String schoolClassId, String clientId, List<SchoolRoom> schoolRooms,
                             List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses, TimeTableOptions timeTableOptions);

    Optional<SchoolClassTimeTable> timeTableById(String timeTableId, List<SchoolRoom> schoolRooms, List<Subject> subjects,
                                                 List<Teacher> teachers, List<SchoolClass> schoolClasses);

    void stopSolving(String timeTableId);

    void stopSolving(List<String> timeTableIds);

    String solverStatus(String timeTableId);

    Map<String, SolverStatus> solverStatus(List<String> timeTableId);

    List<SchoolClassTimeTable> timeTables(String clientId, List<SchoolRoom> schoolRooms, List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses);
}
