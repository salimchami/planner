package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.solver.SolverStatus;

import java.util.List;
import java.util.Optional;

public interface TimeTableGenerationAPI {

    List<String> solveForAllSchoolClasses(String clientId, List<SchoolRoom> schoolRooms,
                                  List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses);

    Optional<SchoolClassTimeTable> getTimeTable(String timeTableId);

    void stopSolving(String timeTableId);

    void stopSolving(List<String> timeTableIds);

    String solveForSchoolClass(String schoolClassId, String clientId, List<SchoolRoom> schoolRooms,
                               List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses);

    SolverStatus solverStatus(String timeTableId);
}
