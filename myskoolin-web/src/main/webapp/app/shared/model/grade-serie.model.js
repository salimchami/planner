"use strict";
exports.__esModule = true;
var GradeSerie = /** @class */ (function () {
    function GradeSerie(name, diminutive, option) {
        this.name = name;
        this.diminutive = diminutive;
        this.option = option;
    }
    GradeSerie.prototype.update = function (name) {
        this.name = name;
        return this;
    };
    GradeSerie.prototype.convert = function (json) {
        this.name = json.name;
        this.diminutive = json.diminutive;
        this.option = json.option;
        return this;
    };
    return GradeSerie;
}());
exports.GradeSerie = GradeSerie;
//# sourceMappingURL=grade-serie.model.js.map