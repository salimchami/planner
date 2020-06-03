"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var ModalBasicComponent = /** @class */ (function () {
    function ModalBasicComponent() {
        this.hideHeader = false;
        this.hideFooter = false;
        this.visible = false;
        this.visibleAnimate = false;
    }
    ModalBasicComponent.prototype.ngOnInit = function () {
    };
    ModalBasicComponent.prototype.show = function () {
        var _this = this;
        this.visible = true;
        setTimeout(function () { return _this.visibleAnimate = true; }, 100);
    };
    ModalBasicComponent.prototype.hide = function () {
        var _this = this;
        this.visibleAnimate = false;
        setTimeout(function () { return _this.visible = false; }, 300);
    };
    ModalBasicComponent.prototype.onContainerClicked = function (event) {
        if (event.target.classList.contains('modal')) {
            this.hide();
        }
    };
    __decorate([
        core_1.Input()
    ], ModalBasicComponent.prototype, "dialogClass");
    __decorate([
        core_1.Input()
    ], ModalBasicComponent.prototype, "hideHeader");
    __decorate([
        core_1.Input()
    ], ModalBasicComponent.prototype, "hideFooter");
    ModalBasicComponent = __decorate([
        core_1.Component({
            selector: 'jhi-modal-basic',
            templateUrl: './modal-basic.component.html',
            styleUrls: ['./modal-basic.component.scss'],
            encapsulation: core_1.ViewEncapsulation.None
        })
    ], ModalBasicComponent);
    return ModalBasicComponent;
}());
exports.ModalBasicComponent = ModalBasicComponent;
//# sourceMappingURL=modal-basic.component.js.map