"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var ModalAnimationComponent = /** @class */ (function () {
    function ModalAnimationComponent() {
        this.backDrop = false;
    }
    ModalAnimationComponent.prototype.ngOnInit = function () {
    };
    ModalAnimationComponent.prototype.close = function (event) {
        document.querySelector('#' + event).classList.remove('md-show');
    };
    __decorate([
        core_1.Input()
    ], ModalAnimationComponent.prototype, "modalClass");
    __decorate([
        core_1.Input()
    ], ModalAnimationComponent.prototype, "contentClass");
    __decorate([
        core_1.Input()
    ], ModalAnimationComponent.prototype, "modalID");
    __decorate([
        core_1.Input()
    ], ModalAnimationComponent.prototype, "backDrop");
    ModalAnimationComponent = __decorate([
        core_1.Component({
            selector: 'jhi-modal-animation',
            templateUrl: './modal-animation.component.html',
            styleUrls: ['./modal-animation.component.scss'],
            encapsulation: core_1.ViewEncapsulation.None
        })
    ], ModalAnimationComponent);
    return ModalAnimationComponent;
}());
exports.ModalAnimationComponent = ModalAnimationComponent;
//# sourceMappingURL=modal-animation.component.js.map