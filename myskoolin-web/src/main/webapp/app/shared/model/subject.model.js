"use strict";
exports.__esModule = true;
var Subject = /** @class */ (function () {
    function Subject(id, clientId, name, customName, grade, gradeSerie, coefficient, foreignLanguage, maxMinutesPerDay, minutesPerWeek, coursesFrequencyPerWeek, color, bgColor) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.customName = customName;
        this.grade = grade;
        this.gradeSerie = gradeSerie;
        this.coefficient = coefficient;
        this.foreignLanguage = foreignLanguage;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.minutesPerWeek = minutesPerWeek;
        this.coursesFrequencyPerWeek = coursesFrequencyPerWeek;
        this.color = color;
        this.bgColor = bgColor;
        this.foreignLanguage = false;
    }
    return Subject;
}());
exports.Subject = Subject;
//# sourceMappingURL=subject.model.js.map