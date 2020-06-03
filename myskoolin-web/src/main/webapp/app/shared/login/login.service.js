"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var __1 = require("..");
var common_1 = require("@angular/common");
var en_1 = require("@angular/common/locales/en");
var fr_1 = require("@angular/common/locales/fr");
var LoginService = /** @class */ (function () {
    function LoginService(languageService, principal, trackerService, authServerProvider) {
        this.languageService = languageService;
        this.principal = principal;
        this.trackerService = trackerService;
        this.authServerProvider = authServerProvider;
    }
    LoginService.prototype.login = function (credentials, callback) {
        var _this = this;
        var cb = callback || function () {
        };
        return new Promise(function (resolve, reject) {
            _this.authServerProvider.login(credentials).subscribe(function (data) {
                _this.principal.identity(true).then(function (account) {
                    // After the login the language will be changed to
                    // the language selected by the user during his registration
                    if (account !== null) {
                        if (account.langKey) {
                            _this.languageService.changeLanguage(account.langKey);
                        }
                        else {
                            _this.languageService.changeLanguage(_this.languageService.currentLang);
                        }
                        if (_this.languageService.currentLang === __1.LANGUAGES.FR) {
                            common_1.registerLocaleData(fr_1["default"], __1.LANGUAGES.FR);
                        }
                        else {
                            common_1.registerLocaleData(en_1["default"], __1.LANGUAGES.EN);
                        }
                    }
                    _this.trackerService.sendActivity();
                    resolve(data);
                });
                return cb();
            }, function (err) {
                _this.logout();
                reject(err);
                return cb(err);
            });
        });
    };
    LoginService.prototype.loginWithToken = function (jwt, rememberMe) {
        return this.authServerProvider.loginWithToken(jwt, rememberMe);
    };
    LoginService.prototype.logout = function () {
        this.authServerProvider.logout().subscribe();
        this.principal.authenticate(null);
    };
    LoginService = __decorate([
        core_1.Injectable()
    ], LoginService);
    return LoginService;
}());
exports.LoginService = LoginService;
//# sourceMappingURL=login.service.js.map