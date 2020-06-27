package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsDbVO;
import org.bson.types.ObjectId;

import java.time.ZonedDateTime;

public class ClientSchoolingDTO {

    private ObjectId id;
    private TimeTableOptionsDbVO timeTableOptions;
    private Integer majorityAge;
    private Integer nbMaxOfStudentsPerSchoolClass;
    private ZonedDateTime scholarYearStart;
    private ZonedDateTime scholarYearEnd;
    private String surfacesMeasurementUnit;
    private String distancesMeasurementUnit;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public TimeTableOptionsDbVO getTimeTableOptions() {
        return timeTableOptions;
    }

    public void setTimeTableOptions(TimeTableOptionsDbVO timeTableOptions) {
        this.timeTableOptions = timeTableOptions;
    }

    public Integer getMajorityAge() {
        return majorityAge;
    }

    public void setMajorityAge(Integer majorityAge) {
        this.majorityAge = majorityAge;
    }

    public Integer getNbMaxOfStudentsPerSchoolClass() {
        return nbMaxOfStudentsPerSchoolClass;
    }

    public void setNbMaxOfStudentsPerSchoolClass(Integer nbMaxOfStudentsPerSchoolClass) {
        this.nbMaxOfStudentsPerSchoolClass = nbMaxOfStudentsPerSchoolClass;
    }

    public ZonedDateTime getScholarYearStart() {
        return scholarYearStart;
    }

    public void setScholarYearStart(ZonedDateTime scholarYearStart) {
        this.scholarYearStart = scholarYearStart;
    }

    public ZonedDateTime getScholarYearEnd() {
        return scholarYearEnd;
    }

    public void setScholarYearEnd(ZonedDateTime scholarYearEnd) {
        this.scholarYearEnd = scholarYearEnd;
    }

    public String getSurfacesMeasurementUnit() {
        return surfacesMeasurementUnit;
    }

    public void setSurfacesMeasurementUnit(String surfacesMeasurementUnit) {
        this.surfacesMeasurementUnit = surfacesMeasurementUnit;
    }

    public String getDistancesMeasurementUnit() {
        return distancesMeasurementUnit;
    }

    public void setDistancesMeasurementUnit(String distancesMeasurementUnit) {
        this.distancesMeasurementUnit = distancesMeasurementUnit;
    }
}
