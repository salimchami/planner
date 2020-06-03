"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var common_1 = require("@angular/common");
var common_2 = require("@angular/common");
var router_1 = require("@angular/router");
var forms_1 = require("@angular/forms");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var http_1 = require("@angular/common/http");
var ngx_perfect_scrollbar_1 = require("ngx-perfect-scrollbar");
var ng_click_outside_1 = require("ng-click-outside");
var animations_1 = require("@angular/platform-browser/animations");
var ng2_toasty_1 = require("ng2-toasty");
var ng2_ui_switch_1 = require("ng2-ui-switch");
var _1 = require("./");
var academic_years_component_1 = require("./academic-years/academic-years.component");
var ng_select_1 = require("@ng-select/ng-select");
var ngx_scroll_to_1 = require("@nicky-lenaers/ngx-scroll-to");
var ngx_archwizard_1 = require("ngx-archwizard");
var ng2_archwizard_1 = require("ng2-archwizard");
var DEFAULT_PERFECT_SCROLLBAR_CONFIG = {
    suppressScrollX: true
};
var SchoolmeSharedModule = /** @class */ (function () {
    function SchoolmeSharedModule() {
    }
    SchoolmeSharedModule = __decorate([
        core_1.NgModule({
            imports: [
                animations_1.BrowserAnimationsModule,
                ng_click_outside_1.ClickOutsideModule,
                common_2.CommonModule,
                forms_1.FormsModule,
                http_1.HttpClientModule,
                ngx_perfect_scrollbar_1.PerfectScrollbarModule,
                forms_1.ReactiveFormsModule,
                router_1.RouterModule,
                _1.SchoolmeSharedCommonModule,
                _1.SchoolmeSharedLibsModule,
                ng2_ui_switch_1.UiSwitchModule,
                ng_select_1.NgSelectModule,
                ng2_toasty_1.ToastyModule,
                ngx_archwizard_1.WizardModule,
                ngx_scroll_to_1.ScrollToModule.forRoot(),
                ng_bootstrap_1.NgbModule.forRoot(),
            ],
            declarations: [
                _1.AccordionAnchorDirective,
                _1.AccordionDirective,
                _1.AccordionLinkDirective,
                _1.CardComponent,
                _1.CardToggleDirective,
                _1.HasAnyAuthorityDirective,
                academic_years_component_1.JhiAcademicYearsComponent,
                _1.JhiLoginModalComponent,
                _1.ModalAnimationComponent,
                _1.ModalBasicComponent,
                _1.SpinnerComponent,
                _1.ToggleFullScreenDirective,
            ],
            providers: [
                _1.AcademicYearsService,
                _1.AccountService,
                _1.AuthServerProvider,
                _1.CSRFService,
                common_1.DatePipe,
                _1.JhiTrackerService,
                _1.LoginModalService,
                _1.LoginService,
                _1.Principal,
                _1.StateStorageService,
                _1.UserService,
                _1.NotificationService,
                ng2_archwizard_1.WizardState,
                ng2_toasty_1.ToastyService,
                {
                    provide: ngx_perfect_scrollbar_1.PERFECT_SCROLLBAR_CONFIG,
                    useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
                },
                {
                    provide: ng_select_1.NG_SELECT_DEFAULT_CONFIG,
                    useValue: {
                        notFoundText: 'Custom not found'
                    }
                }
            ],
            entryComponents: [_1.JhiLoginModalComponent],
            exports: [
                _1.AccordionAnchorDirective,
                _1.AccordionDirective,
                _1.AccordionLinkDirective,
                _1.CardComponent,
                _1.CardToggleDirective,
                ng_click_outside_1.ClickOutsideModule,
                common_1.DatePipe,
                _1.HasAnyAuthorityDirective,
                http_1.HttpClientModule,
                academic_years_component_1.JhiAcademicYearsComponent,
                _1.JhiLoginModalComponent,
                _1.ModalAnimationComponent,
                _1.ModalBasicComponent,
                ng_bootstrap_1.NgbModule,
                ngx_perfect_scrollbar_1.PerfectScrollbarModule,
                _1.SchoolmeSharedCommonModule,
                _1.SpinnerComponent,
                _1.ToggleFullScreenDirective,
                ng2_ui_switch_1.UiSwitchModule,
                ng2_toasty_1.ToastyModule,
            ],
            schemas: [core_1.CUSTOM_ELEMENTS_SCHEMA]
        })
    ], SchoolmeSharedModule);
    return SchoolmeSharedModule;
}());
exports.SchoolmeSharedModule = SchoolmeSharedModule;
//# sourceMappingURL=shared.module.js.map