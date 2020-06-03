"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var card_animation_1 = require("./card-animation");
var CardComponent = /** @class */ (function () {
    function CardComponent() {
        this.classHeader = false;
        this.cardOptionBlock = false;
        this.cardToggle = 'expanded';
        this.cardClose = 'open';
        this.loadCard = false;
        this.isCardToggled = false;
        this.fullCardIcon = 'fa-expand';
        this.cardIconToggle = 'an-off';
    }
    CardComponent.prototype.ngOnInit = function () {
        if (this.cardOptionBlock) {
            this.cardToggle = 'false';
        }
    };
    CardComponent.prototype.toggleCard = function (event) {
        this.cardToggle = this.cardToggle === 'collapsed' ? 'expanded' : 'collapsed';
    };
    CardComponent.prototype.toggleCardOption = function () {
        this.isCardToggled = !this.isCardToggled;
        this.cardIconToggle = this.cardIconToggle === 'an-off' ? 'an-animate' : 'an-off';
    };
    CardComponent.prototype.closeCard = function (event) {
        this.cardClose = this.cardClose === 'closed' ? 'open' : 'closed';
    };
    CardComponent.prototype.fullScreen = function (event) {
        this.fullCard = this.fullCard === 'full-card' ? '' : 'full-card';
        this.fullCardIcon = this.fullCardIcon === 'fa-expand' ? 'fa-compress' : 'fa-expand';
    };
    CardComponent.prototype.cardRefresh = function () {
        var _this = this;
        this.loadCard = true;
        this.cardLoad = 'card-load';
        setTimeout(function () {
            _this.cardLoad = '';
            _this.loadCard = false;
        }, 3000);
    };
    __decorate([
        core_1.Input()
    ], CardComponent.prototype, "headerContent");
    __decorate([
        core_1.Input()
    ], CardComponent.prototype, "title");
    __decorate([
        core_1.Input()
    ], CardComponent.prototype, "blockClass");
    __decorate([
        core_1.Input()
    ], CardComponent.prototype, "cardClass");
    __decorate([
        core_1.Input()
    ], CardComponent.prototype, "classHeader");
    __decorate([
        core_1.Input()
    ], CardComponent.prototype, "cardOptionBlock");
    CardComponent = __decorate([
        core_1.Component({
            selector: 'jhi-card',
            templateUrl: './card.component.html',
            styleUrls: ['./card.component.scss'],
            animations: [card_animation_1.cardToggle, card_animation_1.cardClose, card_animation_1.cardIconToggle],
            encapsulation: core_1.ViewEncapsulation.None
        })
    ], CardComponent);
    return CardComponent;
}());
exports.CardComponent = CardComponent;
//# sourceMappingURL=card.component.js.map