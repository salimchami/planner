"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var academic_years_model_1 = require("./academic-years.model");
var forms_1 = require("@angular/forms");
var JhiAcademicYearsComponent = /** @class */ (function () {
    function JhiAcademicYearsComponent(academicYearsService) {
        this.academicYearsService = academicYearsService;
    }
    JhiAcademicYearsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.displaySelect = false;
        this.academicYearsService.get().subscribe(function (res) {
            _this.acYears = new academic_years_model_1.AcademicYears(res.body.current, res.body.next);
            _this.selectedDate = _this.acYears.current;
        });
        this.academicYearForm = new forms_1.FormGroup({
            email: new forms_1.FormControl('', [forms_1.Validators.email, forms_1.Validators.required])
        });
    };
    JhiAcademicYearsComponent.prototype.ngOnDestroy = function () {
    };
    JhiAcademicYearsComponent.prototype.discardChangeDates = function () {
        this.displaySelect = false;
    };
    JhiAcademicYearsComponent.prototype.changeDates = function (value) {
        this.displaySelect = value;
    };
    __decorate([
        core_1.Input()
    ], JhiAcademicYearsComponent.prototype, "selectedDate");
    __decorate([
        core_1.Input()
    ], JhiAcademicYearsComponent.prototype, "label");
    JhiAcademicYearsComponent = __decorate([
        core_1.Component({
            selector: 'jhi-academic-years',
            templateUrl: 'academic-years.component.html'
        })
    ], JhiAcademicYearsComponent);
    return JhiAcademicYearsComponent;
}());
exports.JhiAcademicYearsComponent = JhiAcademicYearsComponent;
//# sourceMappingURL=academic-years.component.js.map