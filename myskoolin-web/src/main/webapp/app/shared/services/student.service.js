"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var app_constants_1 = require("../../app.constants");
var request_util_1 = require("./utils/request-util");
var StudentService = /** @class */ (function () {
    function StudentService(http, languageService) {
        this.http = http;
        this.languageService = languageService;
        this.resourceUrl = app_constants_1.SERVER_API_URL + 'api/students';
    }
    StudentService.prototype.create = function (student) {
        var studentCopy = this.convert(student);
        this.setLangKey(studentCopy);
        return this.http.post(this.resourceUrl, studentCopy, { observe: 'response' });
    };
    StudentService.prototype.update = function (student) {
        var studentCopy = this.convert(student);
        this.setLangKey(studentCopy);
        return this.http.put(this.resourceUrl, studentCopy, { observe: 'response' });
    };
    StudentService.prototype.find = function (name) {
        var _this = this;
        return this.http.get(this.resourceUrl + "/" + name, { observe: 'response' })
            .map(function (res) { return _this.convertResponse(res); });
    };
    StudentService.prototype.query = function (req) {
        var _this = this;
        var options = request_util_1.createRequestOption(req);
        return this.http.get(this.resourceUrl, { params: options, observe: 'response' })
            .map(function (res) { return _this.convertArrayResponse(res); });
    };
    StudentService.prototype["delete"] = function (id) {
        return this.http["delete"](this.resourceUrl + "/" + id, { observe: 'response' });
    };
    StudentService.prototype.convertResponse = function (res) {
        var body = this.convertItemFromServer(res.body);
        return res.clone({ body: body });
    };
    StudentService.prototype.convertArrayResponse = function (res) {
        var jsonResponse = res.body;
        var body = [];
        for (var i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({ body: body });
    };
    StudentService.prototype.setLangKey = function (studentCopy) {
        if (!studentCopy.langKey) {
            this.languageService.getCurrent().then(function (key) {
                studentCopy.langKey = key;
            });
        }
    };
    /**
     * Convert a returned JSON object to Student.
     */
    StudentService.prototype.convertItemFromServer = function (student) {
        return Object.assign({}, student);
    };
    /**
     * Convert a Student to a JSON which can be sent to the server.
     */
    StudentService.prototype.convert = function (student) {
        return Object.assign({}, student);
    };
    StudentService.prototype.convertArray = function (students) {
        return Object.assign([], students);
    };
    StudentService = __decorate([
        core_1.Injectable()
    ], StudentService);
    return StudentService;
}());
exports.StudentService = StudentService;
//# sourceMappingURL=student.service.js.map