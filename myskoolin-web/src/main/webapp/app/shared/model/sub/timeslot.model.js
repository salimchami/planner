"use strict";
exports.__esModule = true;
var Timeslot = /** @class */ (function () {
    function Timeslot(title, comment, canceled, startTime, endTime, bgColor, fontColor, day, date, autoAlterable) {
        this.title = title;
        this.comment = comment;
        this.canceled = canceled;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bgColor = bgColor;
        this.fontColor = fontColor;
        this.day = day;
        this.date = date;
        this.autoAlterable = autoAlterable;
    }
    return Timeslot;
}());
exports.Timeslot = Timeslot;
//# sourceMappingURL=timeslot.model.js.map