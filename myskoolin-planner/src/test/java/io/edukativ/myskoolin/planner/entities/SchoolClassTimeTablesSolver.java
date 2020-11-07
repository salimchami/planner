package io.edukativ.myskoolin.planner.entities;

import io.edukativ.myskoolin.planner.ScoreManager;
import io.edukativ.myskoolin.planner.SolverJob;
import io.edukativ.myskoolin.planner.SolverManager;
import io.edukativ.myskoolin.planner.SolverStatus;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SchoolClassTimeTablesSolver implements TimeTablesSolver {

    private final SolverManager<TimeTable, String> solverManager;
    private final ScoreManager<TimeTable> scoreManager;
    private final SchoolClassSPI schoolClassSPI;
    private TimeTableSPI timeTableSPI;

    public SchoolClassTimeTablesSolver(SolverManager<TimeTable, String> solverManager,
                                       ScoreManager<TimeTable> scoreManager,
                                       SchoolClassSPI schoolClassSPI) {
        this.solverManager = solverManager;
        this.scoreManager = scoreManager;
        this.schoolClassSPI = schoolClassSPI;
    }

    @Override
    public void stopSolving(String timeTableId) {
        solverManager.terminateEarly(timeTableId);
    }

    @Override
    public void solveForSchoolClass(String schoolClassId, List<Subject> subjects) {
        Optional<SchoolClass> optSchoolClass = schoolClassSPI.findById(schoolClassId);
        optSchoolClass.ifPresent(schoolClass -> {
            final List<Timeslot> baseTimeslots = baseTimeTable(subjects);
            final TimeTable schoolClassTimeTable = new TimeTable(baseTimeslots, subjects);
            solveSaveAndListen(schoolClass, schoolClassTimeTable);
        });
    }

    @Override
    public SolverStatus solverStatus(String timeTableId) {
        return solverManager.getSolverStatus(timeTableId);
    }

    private List<Timeslot> baseTimeTable(List<Subject> subjects) {
        Subject francais = subjects.stream().filter(s -> s.getName().equals("Français")).findFirst().orElse(null);
        Subject maths = subjects.stream().filter(s -> s.getName().equals("Mathematiques")).findFirst().orElse(null);
        return Arrays.asList(
                new Timeslot(1L, DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 30), null),
                new Timeslot(2L, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 0), null),
                new Timeslot(3L, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(9, 30), null),
                new Timeslot(4L, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 0), null),
                new Timeslot(5L, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(10, 30), null),
                new Timeslot(6L, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 0), null),
                new Timeslot(7L, DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(11, 30), null),
                new Timeslot(8L, DayOfWeek.MONDAY, LocalTime.of(11, 30), LocalTime.of(12, 0), null),
                new Timeslot(9L, DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(12, 30), null),
                new Timeslot(10L, DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(14, 30), null),
                new Timeslot(11L, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 0), null),
                new Timeslot(12L, DayOfWeek.MONDAY, LocalTime.of(15, 0), LocalTime.of(15, 30), null),
                new Timeslot(13L, DayOfWeek.MONDAY, LocalTime.of(15, 30), LocalTime.of(16, 0), null),
                new Timeslot(14L, DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(16, 30), null),
                new Timeslot(15L, DayOfWeek.MONDAY, LocalTime.of(16, 30), LocalTime.of(17, 0), null),
                new Timeslot(16L, DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(17, 30), null),
                new Timeslot(17L, DayOfWeek.MONDAY, LocalTime.of(17, 30), LocalTime.of(18, 0), null),
                new Timeslot(18L, DayOfWeek.MONDAY, LocalTime.of(18, 0), LocalTime.of(18, 30), null)
        );
    }

    private List<Timeslot> generateFirstStepTimeTable() {
        return null;
    }

    private void solveSaveAndListen(SchoolClass schoolClass, TimeTable schoolClassTimeTable) {
        final SolverJob<TimeTable, String> solverJob =
                solverManager.solveAndListen(schoolClass.getName(), schoolClassTimeTable, this::saveTimeTable);
        saveTimeTable(solverJob.getFinalBestSolution());
    }

    private String saveTimeTable(TimeTable timeTable) {
        timeTable.setLastGenerationDate(Instant.now());
        System.out.println(timeTable);
        return "";
    }
}
