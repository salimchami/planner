package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
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
    private final MyskoolinLoggerSPI logger;

    public TimeTablesSolver(SolverManager<SchoolClassTimeTable, String> solverManager,
                            ScoreManager<SchoolClassTimeTable> scoreManager,
                            SchoolClassSPI schoolClassSPI,
                            TimeTableSPI timeTableSPI, MyskoolinLoggerSPI logger) {
        this.solverManager = solverManager;
        this.schoolClassSPI = schoolClassSPI;
        this.scoreManager = scoreManager;
        this.timeTableSPI = timeTableSPI;
        this.logger = logger;
    }

    @Override
    public void solveForAllSchoolClasses(String clientId, List<SchoolRoom> schoolRooms, List<Subject> subjects,
                                         List<Teacher> teachers, List<SchoolClass> schoolClasses, TimeTableOptions timeTableOptions) {
        for (SchoolClass schoolClass : schoolClasses) {
            this.solveForSchoolClass(schoolClass.getId(),
                    clientId, schoolRooms, subjects, teachers, schoolClasses, timeTableOptions);
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
                                    List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses, TimeTableOptions timeTableOptions) {
        Optional<SchoolClass> optSchoolClass = schoolClassSPI.findById(schoolClassId);
        optSchoolClass.ifPresent(schoolClass -> {
            logger.info(String.format("solving time table for class : %s", schoolClass.getName()));
            TimeTableFirstStepSolver firstStepSolver = new TimeTableFirstStepSolver(schoolRooms, subjects, teachers, schoolClass, timeTableOptions, logger);
            final List<Lesson> lessons = firstStepSolver.generateFirstStepTimeTable();

            final SchoolClassTimeTable schoolClassTimeTable = new SchoolClassTimeTable(schoolClass, schoolClasses,
                    schoolRooms, subjects, teachers, lessons, lessons.stream().map(Lesson::getTimeSlot).collect(Collectors.toList()));
            solverManager.solveAndListen(
                    schoolClassId,
                    id -> schoolClassTimeTable,
                    solvedSchoolClassTimeTable -> saveTimeTable(schoolClassId, solvedSchoolClassTimeTable)
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
        if (schoolClassTimeTable.getScore().isFeasible()) {
            final SchoolClassTimeTable savedSchoolClassTimeTable = timeTableSPI.saveTimeTable(schoolClassTimeTable);
            schoolClassSPI.saveTimeTable(schoolClassId, savedSchoolClassTimeTable);
        }
    }
}
