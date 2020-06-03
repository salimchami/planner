"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
exports.__esModule = true;
var timeslot_model_1 = require("./timeslot.model");
var DailyBookTimeSlot = /** @class */ (function (_super) {
    __extends(DailyBookTimeSlot, _super);
    function DailyBookTimeSlot(subject, 
        // FIXME: change to Teacher type
        teachers, targetDate) {
        var _this = _super.call(this) || this;
        _this.subject = subject;
        _this.teachers = teachers;
        _this.targetDate = targetDate;
        return _this;
    }
    return DailyBookTimeSlot;
}(timeslot_model_1.Timeslot));
exports.DailyBookTimeSlot = DailyBookTimeSlot;
//# sourceMappingURL=daily-book-timeslot.model.js.map