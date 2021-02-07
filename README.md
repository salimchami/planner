# SC Planner 
<h2>Java Constraints Solver</h3>

<h1 style="color:#7c3939">[UNDER IMPLEMENTATION]</h1>

![Java CI with Maven](https://github.com/salimchami/sc-planner/workflows/Java%20CI%20with%20Maven/badge.svg)


This constraints solver take a list of constraints and return the best solution defined by functional entities.

For example, the problem to solve could be a school timetable.

## How To

You must to create all the problem entities, and a list of constraints to solve your problem.

Check the tests directory containing an example of school timetable solving.

### Functional Entities

#### Solution Class

```java
    @PlanningSolution
    public class TimeTable {

        @SolutionId
        private String schoolClassName;
    
        @BasePlanningVariables
        private final List<Timeslot> baseTimeslots;
    
        @ModifiablePlanningVariables
        private List<Timeslot> timeslots = new ArrayList<>();
    
        @Facts
        private final List<Subject> subjects;

        //...
    }
```

#### Planning variable

```java
    @PlanningVariable
    public class Timeslot {

        @PlanningVariableId
        private Long id;
        @PlanningVariableItem
        private DayOfWeek day;
        @PlanningVariableItem
        private LocalTime startTime;
        @PlanningVariableItem
        private LocalTime endTime;
        @PlanningVariableItem
        private Subject subject;

        //...
    }
```
        
#### Fact class

You can define multiple fact classes. The fact class in this example is the Subject.

```java
    @Fact
    public class Subject {

        @FactId
        private final Long id;
        private final String name;
        @FactItem
        private final Integer maxMinutesPerDay;
        @FactItem
        private final Integer minMinutesPerDay;
        @FactItem
        private final Integer minutesPerWeek;
        @FactItem
        private final Integer coursesFrequencyPerWeek;
        //...
    }
```

#### Constraints Provider

In this provider, you can put all the constraint you need to solve your problem. 
Every constraint contains a name, a score (SOFT, MEDIUM, HARD) and a penalty function which penalize the final score.

```java

    @ConstraintsProvider
    public class TimeTableConstraintProvider implements ConstraintProvider {
    
        @Override
        public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
            return new Constraint[]{
                    // Hard constraints
                    subjectDurationByDayConflict(constraintFactory)
            };
        }
    
        private Constraint<Subject, Timeslot> subjectDurationByDayConflict(ConstraintFactory constraintFactory) {
            return constraintFactory
                    .fromMultiple(Timeslot.class)
                    .withFact(Subject.class)
                    .filter(Subject::getMaxMinutesPerDay)
                    .filter(Subject::getMinMinutesPerDay)
                    .apply("Subject Duration By Day", ScoreLevel.HARD, (Subject subject, List<Timeslot> timeSlots) ->
                            subject.maxMinutesPerDayPenalty(Timeslot.totalDuration(timeSlots)));
    
        }
    }
```    

#### Solver

And then, create a solver by defining 

```java
    public class SchoolClassTimeTablesSolver implements TimeTablesSolver {

        private final IWantToManageSolver<TimeTable, String, Timeslot> solverManager;
        private final ScoreManager<TimeTable> scoreManager;
        private final SchoolClassSPI schoolClassSPI;
        private final TimeTableSPI timeTableSPI;
    
        public SchoolClassTimeTablesSolver(IWantToManageSolver<TimeTable, String, Timeslot> solverManager,
                                           ScoreManager<TimeTable> scoreManager,
                                           SchoolClassSPI schoolClassSPI, TimeTableSPI timeTableSPI) {
            this.solverManager = solverManager;
            this.scoreManager = scoreManager;
            this.schoolClassSPI = schoolClassSPI;
            this.timeTableSPI = timeTableSPI;
        }
    
        @Override
        public void stopSolving(String timeTableId) throws SolutionSolvingException {
            solverManager.terminateEarly(timeTableId);
        }
    
        @Override
        public void solveForSchoolClass(String schoolClassId, List<Subject> subjects) throws SolutionConfigurationException, SolutionSolvingException {
            Optional<SchoolClass> optSchoolClass = schoolClassSPI.findById(schoolClassId);
            if (optSchoolClass.isPresent()) {
                final List<Timeslot> baseTimeslots = baseTimeTable();
                final TimeTable schoolClassTimeTable = new TimeTable(baseTimeslots, subjects);
                solverManager.solveAndListen(optSchoolClass.get().getName(), schoolClassTimeTable, this::saveTimeTable);
            }
        }
    
        @Override
        public SolverStatus solverStatus(String timeTableId) {
            return solverManager.getSolverStatus(timeTableId);
        }
    
        private List<Timeslot> baseTimeTable() {
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
    
        private String saveTimeTable(TimeTable timeTable) {
            timeTable.setLastGenerationDate(Instant.now());
            System.out.println("saving school class timetable");
            timeTableSPI.save(timeTable);
            return "";
        }
    }
```
