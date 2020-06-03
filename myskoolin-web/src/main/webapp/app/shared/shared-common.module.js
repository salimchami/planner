"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var common_1 = require("@angular/common");
var en_1 = require("@angular/common/locales/en");
var window_service_1 = require("./tracker/window.service");
var _1 = require("./");
var SchoolmeSharedCommonModule = /** @class */ (function () {
    function SchoolmeSharedCommonModule() {
        common_1.registerLocaleData(en_1["default"]);
    }
    SchoolmeSharedCommonModule = __decorate([
        core_1.NgModule({
            imports: [
                _1.SchoolmeSharedLibsModule
            ],
            declarations: [
                _1.FindLanguageFromKeyPipe,
                _1.JhiAlertComponent,
                _1.JhiAlertErrorComponent
            ],
            providers: [
                _1.JhiLanguageHelper,
                window_service_1.WindowRef,
                platform_browser_1.Title,
                {
                    provide: core_1.LOCALE_ID,
                    useValue: 'en'
                },
            ],
            exports: [
                _1.SchoolmeSharedLibsModule,
                _1.FindLanguageFromKeyPipe,
                _1.JhiAlertComponent,
                _1.JhiAlertErrorComponent
            ]
        })
    ], SchoolmeSharedCommonModule);
    return SchoolmeSharedCommonModule;
}());
exports.SchoolmeSharedCommonModule = SchoolmeSharedCommonModule;
//# sourceMappingURL=shared-common.module.js.map