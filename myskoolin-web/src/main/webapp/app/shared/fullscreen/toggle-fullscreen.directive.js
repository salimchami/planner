"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var screenfull = require("screenfull");
var ToggleFullScreenDirective = /** @class */ (function () {
    function ToggleFullScreenDirective() {
    }
    ToggleFullScreenDirective.prototype.onClick = function () {
        if (screenfull.enabled) {
            screenfull.toggle();
        }
    };
    __decorate([
        core_1.HostListener('click')
    ], ToggleFullScreenDirective.prototype, "onClick");
    ToggleFullScreenDirective = __decorate([
        core_1.Directive({
            selector: '[jhiToggleFullScreen]'
        })
    ], ToggleFullScreenDirective);
    return ToggleFullScreenDirective;
}());
exports.ToggleFullScreenDirective = ToggleFullScreenDirective;
//# sourceMappingURL=toggle-fullscreen.directive.js.map