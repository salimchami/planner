"use strict";
exports.__esModule = true;
var TimeTableOptions = /** @class */ (function () {
    function TimeTableOptions(schoolRoomsDistances, firstWeekDay, surfaceMinPerStudent, coursesStartTime, coursesEndTime, dayTimeBreaks, coursesTimeSlots, calendarTimelineDuration) {
        this.schoolRoomsDistances = schoolRoomsDistances;
        this.firstWeekDay = firstWeekDay;
        this.surfaceMinPerStudent = surfaceMinPerStudent;
        this.coursesStartTime = coursesStartTime;
        this.coursesEndTime = coursesEndTime;
        this.dayTimeBreaks = dayTimeBreaks;
        this.coursesTimeSlots = coursesTimeSlots;
        this.calendarTimelineDuration = calendarTimelineDuration;
    }
    TimeTableOptions.prototype.convert = function (json) {
        this.schoolRoomsDistances = json.schoolRoomsDistances;
        this.firstWeekDay = json.firstWeekDay;
        this.surfaceMinPerStudent = json.firstWeekDay;
        this.coursesStartTime = json.firstWeekDay;
        this.coursesEndTime = json.coursesEndTime;
        this.dayTimeBreaks = json.dayTimeBreaks;
        this.coursesTimeSlots = json.coursesTimeSlots;
        this.calendarTimelineDuration = json.calendarTimelineDuration;
        return this;
    };
    return TimeTableOptions;
}());
exports.TimeTableOptions = TimeTableOptions;
//# sourceMappingURL=time-table-options.model.js.map