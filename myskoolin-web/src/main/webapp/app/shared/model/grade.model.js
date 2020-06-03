"use strict";
exports.__esModule = true;
var Grade = /** @class */ (function () {
    function Grade(clientId, name, order, notation, diminutive, maxMinutesPerDay, series, timeTableOptions, subjects, id) {
        this.clientId = clientId;
        this.name = name;
        this.order = order;
        this.notation = notation;
        this.diminutive = diminutive;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.series = series;
        this.timeTableOptions = timeTableOptions;
        this.subjects = subjects;
        this.id = id;
    }
    Grade.prototype.update = function (id, name, order, notation, diminutive, maxMinutesPerDay, series, timeTableOptions) {
        this.name = name;
        this.order = order;
        this.notation = notation;
        this.diminutive = diminutive;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.series = series;
        this.timeTableOptions = timeTableOptions;
        this.id = id;
        return this;
    };
    Grade.prototype.convert = function (json) {
        this.clientId = json.clientId;
        this.id = json.id;
        this.name = json.name;
        this.order = json.order;
        this.notation = json.notation;
        this.diminutive = json.diminutive;
        this.maxMinutesPerDay = json.maxMinutesPerDay;
        this.series = json.series;
        this.timeTableOptions = json.timeTableOptions;
        return this;
    };
    return Grade;
}());
exports.Grade = Grade;
//# sourceMappingURL=grade.model.js.map