package io.edukativ.myskoolin.domain.timetabling;

import io.edukativ.myskoolin.domain.subjects.Subject;

import java.util.List;

public class TimeTableValidation {

    private String schoolClassName;
    private boolean durationOk;
    private boolean allTimeSlotsHaveSchoolRooms;
    private boolean allTimeSlotsHaveTeachers;
    private boolean notOutside;
    private boolean standardTimeslotsNotOverlapping;
    private boolean halfTimeslotsNotOverlapping;
    private boolean timeSlotsNotEmpty;
    private List<Subject> subjects;
    private List<SchoolClassTimeSlot> timeSlots;

    public TimeTableValidation(String schoolClassName, boolean durationOk, boolean notOutside, boolean standardTimeslotsNotOverlapping,
                               boolean halfTimeslotsNotOverlapping, boolean allTimeSlotsHaveSchoolRooms,
                               boolean allTimeSlotsHaveTeachers, boolean timeSlotsNotEmpty, List<Subject> subjects, List<SchoolClassTimeSlot> timeSlots) {
        this.schoolClassName = schoolClassName;
        this.durationOk = durationOk;
        this.notOutside = notOutside;
        this.standardTimeslotsNotOverlapping = standardTimeslotsNotOverlapping;
        this.halfTimeslotsNotOverlapping = halfTimeslotsNotOverlapping;
        this.allTimeSlotsHaveSchoolRooms = allTimeSlotsHaveSchoolRooms;
        this.allTimeSlotsHaveTeachers = allTimeSlotsHaveTeachers;
        this.timeSlotsNotEmpty = timeSlotsNotEmpty;
        this.subjects = subjects;
        this.timeSlots = timeSlots;
    }

    public String getSchoolClassName() {
        return schoolClassName;
    }

    public boolean isDurationOk() {
        return durationOk;
    }

    public boolean isNotOutside() {
        return notOutside;
    }

    public boolean isStandardTimeslotsNotOverlapping() {
        return standardTimeslotsNotOverlapping;
    }

    public boolean isValid() {
        return isDurationOk() && isNotOutside()
                && isStandardTimeslotsNotOverlapping()
                && isHalfTimeslotsNotOverlapping()
                && isAllTimeSlotsHaveSchoolRooms()
                && isAllTimeSlotsHaveTeachers();
    }

    public boolean isHalfTimeslotsNotOverlapping() {
        return halfTimeslotsNotOverlapping;
    }

    public boolean isAllTimeSlotsHaveSchoolRooms() {
        return allTimeSlotsHaveSchoolRooms;
    }

    public boolean isAllTimeSlotsHaveTeachers() {
        return allTimeSlotsHaveTeachers;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<SchoolClassTimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public String validationToString() {
        return "TimeTableValidation{" +
                "schoolClassName='" + schoolClassName + '\'' +
                ", timeSlotsNotEmpty=" + timeSlotsNotEmpty +
                ", durationOk=" + durationOk +
                ", notOutside=" + notOutside +
                ", standardTimeslotsNotOverlapping=" + standardTimeslotsNotOverlapping +
                ", halfTimeslotsNotOverlapping=" + halfTimeslotsNotOverlapping +
                ", allTimeSlotsHaveTeachers=" + allTimeSlotsHaveTeachers +
                ", allTimeSlotsHaveSchoolRooms=" + allTimeSlotsHaveSchoolRooms +
                '}';
    }

    public void subjectValidationToString() {
        subjects.forEach(subject -> {
            final int addedDurationForSubject = TimeTableGenerationData.addedDurationForSubject(subject, timeSlots);
            if (addedDurationForSubject != subject.getMinutesPerWeek()) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! Subject : " + subject.getName() + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("subject MinutesPerWeek : " + subject.getMinutesPerWeek());
                System.out.println("timetable duration : " + addedDurationForSubject);
                System.out.println("--------------------------------------------------------------------------------");
            }
        });
    }
}
