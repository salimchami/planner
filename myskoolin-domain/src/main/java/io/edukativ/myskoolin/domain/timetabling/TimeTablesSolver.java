package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.commons.exceptions.SchoolClassNotFoundException;
import io.edukativ.myskoolin.domain.commons.utils.DateUtils;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClass;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassSPI;
import io.edukativ.myskoolin.domain.schoolrooms.SchoolRoom;
import io.edukativ.myskoolin.domain.subjects.Subject;
import io.edukativ.myskoolin.domain.teachers.Teacher;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
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
    public List<String> solveForAllSchoolClasses(String clientId, List<SchoolRoom> schoolRooms, List<Subject> subjects,
                                                 List<Teacher> teachers, List<SchoolClass> schoolClasses) throws ExecutionException, InterruptedException {
        List<String> schoolClassTimeTablesId = new ArrayList<>();
        for (SchoolClass schoolClass : schoolClasses) {
            schoolClassTimeTablesId.add(this.solveForSchoolClass(schoolClass.getId(),
                    clientId, schoolRooms, subjects, teachers, schoolClasses));
        }
        return schoolClassTimeTablesId;
    }

    @Override
    public Optional<SchoolClassTimeTable> getTimeTable(String timeTableId) {
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
    public String solveForSchoolClass(String schoolClassId, String clientId, List<SchoolRoom> schoolRooms,
                                                    List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses) throws ExecutionException, InterruptedException {
        Optional<SchoolClass> optSchoolClass = schoolClassSPI.findById(schoolClassId);
        final SchoolClassTimeTable schoolClassTimeTable = new SchoolClassTimeTable(schoolClasses, schoolRooms, subjects, teachers);
        if (optSchoolClass.isPresent()) {
            String timeTableSolvingId = DateUtils.concatClientIdAndNowTimestamp(clientId);
            solverManager.solveAndListen(timeTableSolvingId,
                    id -> schoolClassTimeTable,
                    savedSchoolClassTimeTable -> saveTimeTable(optSchoolClass.get(), schoolClassTimeTable));
            return timeTableSolvingId;
        }
        throw new SchoolClassNotFoundException("no school class found : " + schoolClassId);
    }

    @Override
    public String solverStatus(String timeTableId) {
        return solverManager.getSolverStatus(timeTableId).name();
    }

    @Override
    public Map<String, SolverStatus> solverStatus(List<String> timeTableIds) {
        return timeTableIds.stream().collect(Collectors.toMap(id -> id, solverManager::getSolverStatus));
    }

    private void saveTimeTable(SchoolClass schoolClass, SchoolClassTimeTable schoolClassTimeTable) {
        schoolClassTimeTable.setSchoolClass(schoolClass);
        schoolClassSPI.saveTimeTable(schoolClassTimeTable);
    }
}
