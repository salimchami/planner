package io.edukativ.myskoolin.domain.timetabling;

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
import java.util.Optional;

public class TimeTablesGeneration implements TimeTableGenerationAPI {

    private final SolverManager<SchoolClassTimeTable, String> solverManager;
    private final SchoolClassSPI schoolClassSPI;
    private final ScoreManager<SchoolClassTimeTable> scoreManager;
    private final TimeTableSPI timeTableSPI;

    public TimeTablesGeneration(SolverManager<SchoolClassTimeTable, String> solverManager,
                                SchoolClassSPI schoolClassSPI, ScoreManager<SchoolClassTimeTable> scoreManager,
                                TimeTableSPI timeTableSPI) {
        this.solverManager = solverManager;
        this.schoolClassSPI = schoolClassSPI;
        this.scoreManager = scoreManager;
        this.timeTableSPI = timeTableSPI;
    }

    @Override
    public List<String> solveForAllSchoolClasses(String clientId, List<SchoolRoom> schoolRooms, List<Subject> subjects,
                                                 List<Teacher> teachers, List<SchoolClass> schoolClasses) {
        List<String> ids = new ArrayList<>();
        schoolClasses.forEach(schoolClass ->
                ids.add(this.solveForSchoolClass(schoolClass.getId(), clientId, schoolRooms, subjects, teachers, schoolClasses)));
        return ids;
    }

    @Override
    public Optional<SchoolClassTimeTable> getTimeTable(String timeTableId) {
        SolverStatus solverStatus = solverStatus(timeTableId);
        Optional<SchoolClassTimeTable> optTimeTable = timeTableSPI.findById(timeTableId);
        return optTimeTable.map(timeTable -> {
            scoreManager.updateScore(timeTable); // Sets the score
            timeTable.setSolverStatus(solverStatus);
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
                                      List<Subject> subjects, List<Teacher> teachers, List<SchoolClass> schoolClasses) {
        Optional<SchoolClass> optSchoolClass = schoolClassSPI.findById(schoolClassId);
        return optSchoolClass.map(schoolClass -> {
            String timeTableSolvingId = DateUtils.concatClientIdAndNowTimestamp(clientId);
            solverManager.solveAndListen(timeTableSolvingId,
                    id -> new SchoolClassTimeTable(schoolClasses, schoolRooms, subjects, teachers),
                    schoolClassTimeTable -> saveTimeTable(schoolClass, schoolClassTimeTable));
            return timeTableSolvingId;
        }).orElse(null);
    }

    @Override
    public SolverStatus solverStatus(String timeTableId) {
        return solverManager.getSolverStatus(timeTableId);
    }

    private void saveTimeTable(SchoolClass schoolClass, SchoolClassTimeTable schoolClassTimeTable) {
        schoolClassTimeTable.setSchoolClass(schoolClass);
        schoolClassSPI.saveTimeTable(schoolClassTimeTable);
    }
}
