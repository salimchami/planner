"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var NotificationService = /** @class */ (function () {
    function NotificationService(toastyService, translateService) {
        this.toastyService = toastyService;
        this.translateService = translateService;
        this.theme = 'default';
        this.type = 'default';
    }
    /**
     *
     * @param title toast title
     * @param msg toast message
     * @param type 'error', 'wait', 'success', 'default', 'info', 'warning'
     * </code>
     * @param closeOthers : close others toasts
     */
    NotificationService.prototype.addToast = function (title, msg, type, closeOthers) {
        var _this = this;
        if (closeOthers) {
            this.toastyService.clearAll();
        }
        this.translateService.get(msg).subscribe(function (toastMsg) {
            _this.translateService.get(title).subscribe(function (toastTitle) {
                var toastOptions = {
                    msg: toastMsg,
                    title: toastTitle,
                    showClose: true,
                    timeout: 8000,
                    theme: 'default',
                    onAdd: function (toast) {
                        /* added */
                    },
                    onRemove: function (toast) {
                        /* removed */
                    }
                };
                switch (type) {
                    case 'default':
                        _this.toastyService["default"](toastOptions);
                        break;
                    case 'info':
                        _this.toastyService.info(toastOptions);
                        break;
                    case 'success':
                        _this.toastyService.success(toastOptions);
                        break;
                    case 'wait':
                        _this.toastyService.wait(toastOptions);
                        break;
                    case 'error':
                        _this.toastyService.error(toastOptions);
                        break;
                    case 'warning':
                        _this.toastyService.warning(toastOptions);
                        break;
                }
            });
        });
    };
    NotificationService = __decorate([
        core_1.Injectable()
    ], NotificationService);
    return NotificationService;
}());
exports.NotificationService = NotificationService;
//# sourceMappingURL=notification.service.js.map