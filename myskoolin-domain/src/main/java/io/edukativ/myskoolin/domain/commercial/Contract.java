package io.edukativ.myskoolin.domain.commercial;

import io.edukativ.myskoolin.domain.schooling.AcademicYear;

import java.time.ZonedDateTime;

/**
 * A Contract.
 */
public class Contract {

    private ZonedDateTime currentPeriodStart;
    private ZonedDateTime currentPeriodEnd;
    private ZonedDateTime globalPeriodStart;
    private ZonedDateTime globalPeriodEnd;
    private AcademicYear academicYear;
    private boolean signed = false;

    public Contract() {
    }

    public Contract(ZonedDateTime currentPeriodStart, ZonedDateTime currentPeriodEnd, ZonedDateTime globalPeriodStart,
                    ZonedDateTime globalPeriodEnd, AcademicYear academicYear, boolean signed) {
        this.currentPeriodStart = currentPeriodStart;
        this.currentPeriodEnd = currentPeriodEnd;
        this.globalPeriodStart = globalPeriodStart;
        this.globalPeriodEnd = globalPeriodEnd;
        this.academicYear = academicYear;
        this.signed = signed;
    }


    public ZonedDateTime getCurrentPeriodStart() {
        return currentPeriodStart;
    }

    public void setCurrentPeriodStart(ZonedDateTime currentPeriodStart) {
        this.currentPeriodStart = currentPeriodStart;
    }

    public ZonedDateTime getCurrentPeriodEnd() {
        return currentPeriodEnd;
    }

    public void setCurrentPeriodEnd(ZonedDateTime currentPeriodEnd) {
        this.currentPeriodEnd = currentPeriodEnd;
    }

    public ZonedDateTime getGlobalPeriodStart() {
        return globalPeriodStart;
    }

    public void setGlobalPeriodStart(ZonedDateTime globalPeriodStart) {
        this.globalPeriodStart = globalPeriodStart;
    }

    public ZonedDateTime getGlobalPeriodEnd() {
        return globalPeriodEnd;
    }

    public void setGlobalPeriodEnd(ZonedDateTime globalPeriodEnd) {
        this.globalPeriodEnd = globalPeriodEnd;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }
}
