"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var language_constants_1 = require("./language.constants");
var app_constants_1 = require("../../app.constants");
var JhiLanguageHelper = /** @class */ (function () {
    function JhiLanguageHelper(translateService, 
        // tslint:disable-next-line: no-unused-variable
        rootRenderer, titleService, router) {
        this.translateService = translateService;
        this.rootRenderer = rootRenderer;
        this.titleService = titleService;
        this.router = router;
        this.renderer = null;
        this.renderer = rootRenderer.createRenderer(document.querySelector('html'), null);
        this.init();
    }
    JhiLanguageHelper.prototype.getAll = function () {
        return Promise.resolve(language_constants_1.LANGUAGES_ARRAY);
    };
    /**
     * Update the window title using params in the following
     * order:
     * 1. titleKey parameter
     * 2. $state.$current.data.pageTitle (current state page title)
     * 3. 'global.title'
     */
    JhiLanguageHelper.prototype.updateTitle = function (titleKey) {
        var _this = this;
        if (!titleKey) {
            titleKey = this.getPageTitle(this.router.routerState.snapshot.root);
        }
        this.translateService.get(titleKey).subscribe(function (title) {
            var finalTitle;
            if (title.includes(app_constants_1.APP_CONSTANTS.APP_TITLE)) {
                finalTitle = title;
            }
            else if (!!title.trim().length) {
                finalTitle = app_constants_1.APP_CONSTANTS.APP_TITLE + ' - ' + title;
            }
            else {
                finalTitle = app_constants_1.APP_CONSTANTS.APP_TITLE;
            }
            _this.titleService.setTitle(finalTitle);
        });
    };
    JhiLanguageHelper.prototype.init = function () {
        var _this = this;
        this.translateService.onLangChange.subscribe(function (event) {
            _this.renderer.setAttribute(document.querySelector('html'), 'lang', _this.translateService.currentLang);
            _this.updateTitle();
        });
    };
    JhiLanguageHelper.prototype.getPageTitle = function (routeSnapshot) {
        var title = (routeSnapshot.data && routeSnapshot.data['pageTitle']) ? routeSnapshot.data['pageTitle'] : 'schoolmeApp';
        if (routeSnapshot.firstChild) {
            title = this.getPageTitle(routeSnapshot.firstChild) || title;
        }
        return title;
    };
    JhiLanguageHelper = __decorate([
        core_1.Injectable()
    ], JhiLanguageHelper);
    return JhiLanguageHelper;
}());
exports.JhiLanguageHelper = JhiLanguageHelper;
//# sourceMappingURL=language.helper.js.map