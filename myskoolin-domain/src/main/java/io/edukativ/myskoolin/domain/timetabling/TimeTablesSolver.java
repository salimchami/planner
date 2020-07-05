package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassSPI;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TimeTablesSolver implements TimeTableSolverAPI {

    private final SolverManager<SchoolClassTimeTable, String> solverManager;
    private final SchoolClassSPI schoolClassSPI;
    private final ScoreManager<SchoolClassTimeTable> scoreManager;
    private final TimeTableSPI timeTableSPI;

    public TimeTablesSolver(SolverManager<SchoolClassTimeTable, String> solverManager,
                            ScoreManager<SchoolClassTimeTable> scoreManager,
                            SchoolClassSPI schoolClassSPI,
                            TimeTableSPI timeTableSPI) {
        this.solverManager = solverManager;
        this.schoolClassSPI = schoolClassSPI;
        this.scoreManager = scoreManager;
        this.timeTableSPI = timeTableSPI;
    }

    @Override
    public void solveForAllSchoolClasses(String clientId, List<SchoolRoom> schoolRooms, List<Subject> subjects,
                                         List<Teacher> teachers, List<SchoolClass> schoolClasses) {
        for (SchoolClass schoolClass : schoolClasses) {
            this.solveForSchoolClass(schoolClass.getId(),
                    clientId, schoolRooms, subjects, teachers, schoolClasses);
        }
    }

    @Override
    public Optional<SchoolClassTimeTable> timeTableById(String timeTableId) {
        String solverStatus = solverStatus(timeTableId);
        Optional<SchoolClassTimeTable> optTimeTable = timeTableSPI.findById(timeTableId);
        return optTimeTable.map(timeTable -> {
            scoreManager.updateScore(timeTable); // Sets the score
            timeTable.setSolverStatus(SolverStatus.valueOf(solverStatus));
            return timeTable;
        });
    }

    @Override
    public void stopSolving(String timeTableId) {
        solverManager.terminateEarly(timeTableId);
    }

    @Override
    public void stopSolving(List<String> timeTableIds) {
        timeTableIds.forEach(this::stopSolving);
    }

    @Override
    public void solveForSchoolClass(String schoolClassId, String clientId, List<SchoolRoom> schoolRooms,
                                    List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses) {

        Optional<SchoolClass> optSchoolClass = schoolClassSPI.findById(schoolClassId);
        optSchoolClass.ifPresent(schoolClass -> {
            final SchoolClassTimeTable schoolClassTimeTable = new SchoolClassTimeTable(schoolClasses, schoolRooms, subjects, teachers);
            solverManager.solveAndListen(
                    schoolClassId,
                    id -> schoolClassTimeTable,
                    savedSchoolClassTimeTable -> saveTimeTable(schoolClassId, schoolClassTimeTable)
            );
        });
    }

    @Override
    public String solverStatus(String timeTableId) {
        return solverManager.getSolverStatus(timeTableId).name();
    }

    @Override
    public Map<String, SolverStatus> solverStatus(List<String> timeTableIds) {
        return timeTableIds.stream().collect(Collectors.toMap(id -> id, solverManager::getSolverStatus));
    }

    private void saveTimeTable(String schoolClassId, SchoolClassTimeTable schoolClassTimeTable) {
        final SchoolClassTimeTable savedSchoolClassTimeTable = timeTableSPI.saveTimeTable(schoolClassTimeTable);
        schoolClassSPI.saveTimeTable(schoolClassId, savedSchoolClassTimeTable);
    }
}
