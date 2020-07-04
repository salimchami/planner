package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.solver.SolverStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface TimeTableSolverAPI {

    List<String> solveForAllSchoolClasses(String clientId, List<SchoolRoom> schoolRooms,
                                          List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses)
            throws ExecutionException, InterruptedException;

    String solveForSchoolClass(String schoolClassId, String clientId, List<SchoolRoom> schoolRooms,
                               List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses)
            throws ExecutionException, InterruptedException;

    Optional<SchoolClassTimeTable> getTimeTable(String timeTableId);

    void stopSolving(String timeTableId);

    void stopSolving(List<String> timeTableIds);

    String solverStatus(String timeTableId);

    Map<String, SolverStatus> solverStatus(List<String> timeTableId);
}
