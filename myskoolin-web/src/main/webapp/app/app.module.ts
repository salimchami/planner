import './vendor.ts';

import {NgModule, Injector} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpRequest} from '@angular/common/http';
import {LocalStorageService, NgxWebstorageModule, SessionStorageService} from 'ngx-webstorage';
import {JhiEventManager} from 'ng-jhipster';
import {ClientResolver} from './shared/services/resolvers/client.resolver';
import {ClientService} from './shared/services/client.service';
import {SchoolRoomService} from './shared/services/school-room.service';
import {SchoolRoomsResolver} from './shared/services/resolvers/school-rooms.resolver';
import {SchoolRoomBynameResolver} from './shared/services/resolvers/school-room-byname.resolver';
import {navbarRoute} from './app.route';
import {RouterModule} from '@angular/router';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {AuthInterceptor} from './blocks/interceptor/auth.interceptor';
import {AuthExpiredInterceptor} from './blocks/interceptor/auth-expired.interceptor';
import {ErrorHandlerInterceptor} from './blocks/interceptor/errorhandler.interceptor';
import {NotificationInterceptor} from './blocks/interceptor/notification.interceptor';
import {MenuItems, NotificationService, SchoolmeSharedModule, UserRouteAccessService} from './shared';
import {SchoolmeAppRoutingModule} from './app-routing.module';
import {SchoolmeHomeModule} from './home';
import {SchoolmeAdminModule} from './admin/admin.module';
import {SchoolmeAccountModule} from './account/account.module';
import {SchoolmeEntityModule} from './entities/entity.module';
import {PaginationConfig} from './blocks/config/uib-pagination.config';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {ScrollToModule} from '@nicky-lenaers/ngx-scroll-to';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {GradesResolver} from './shared/services/resolvers/grades.resolver';
import {GradeBynameResolver} from './shared/services/resolvers/grade-byname.resolver';
import {GradeService} from './shared/services/grade.service';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent,
    JhiLandingpageNavbarComponent,
    MenuComponent,
} from './layouts';
import {SubjectsResolver} from './shared/services/resolvers/subject.resolver';
import {StudentsResolver} from './shared/services/resolvers/students.resolver';
import {SubjectService} from './shared/services/subject.service';
import {StudentService} from './shared/services/student.service';
import {SubjectResolver} from './shared/services/resolvers/subject-resolver.service';
import {NgcCookieConsentModule} from 'ngx-cookieconsent';
import {COOKIE_CONSENT_CONFIG} from './app.constants';
import {CookieConsentService} from './shared/services/config/cookie.consent.service';
import {CookieModule} from 'ngx-cookie';
import {EncryptService} from './shared/services/security/encrypt.service';
import {StudentResolver} from './shared/services/resolvers/student.resolver';
import {DateTimeHelper} from './shared/services/utils/date-time-helper.service';
import {SchoolClassesResolver} from './shared/services/resolvers/school-classes.resolver';
import {SchoolClassService} from './shared/services/school-class.service';
import {TeachersResolver} from './shared/services/resolvers/teachers.resolver';
import {TeacherService} from './shared/services/teacher.service';
import {TeacherResolver} from './shared/services/resolvers/teacher.resolver';
import {SchoolClassResolver} from './shared/services/resolvers/school-class.resolver';
import {CalendarHelper} from './shared/services/utils/calendar-helper.service';
import {CalendarModule, DateAdapter} from 'angular-calendar';
import {Ng7MatBreadcrumbModule} from 'ng7-mat-breadcrumb';
import {PricingsResolver} from './shared/services/resolvers/pricings.resolver';
import {PricingService} from './shared/services/pricing.service';
import {TimetableService} from './shared/services/timetable.service';
import {DataHelper} from './shared/services/utils/data.helper';

const APP_STATES = [
    ...navbarRoute,
];

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        CookieModule.forRoot(),
        SchoolmeAppRoutingModule,
        SchoolmeSharedModule,
        ScrollToModule.forRoot(),
        NgxWebstorageModule.forRoot({prefix: 'jhi', separator: '-'}),
        RouterModule.forChild(APP_STATES),
        SchoolmeHomeModule,
        SchoolmeAdminModule,
        SchoolmeAccountModule,
        SchoolmeEntityModule,
        FormsModule,
        ReactiveFormsModule,
        Ng7MatBreadcrumbModule,
        NgxDatatableModule,
        CalendarModule,
        NgcCookieConsentModule.forRoot(COOKIE_CONSENT_CONFIG),
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent,
        JhiLandingpageNavbarComponent,
        MenuComponent,

    ],
    providers: [
        MenuItems,
        ProfileService,
        PaginationConfig,
        ClientResolver,
        PricingsResolver,
        PricingService,
        CookieConsentService,
        EncryptService,
        GradesResolver,
        SubjectsResolver,
        StudentsResolver,
        TeachersResolver,
        TeacherResolver,
        StudentResolver,
        SubjectResolver,
        GradeBynameResolver,
        SchoolRoomsResolver,
        SchoolClassesResolver,
        SchoolClassResolver,
        SchoolRoomBynameResolver,
        ClientService,
        NotificationService,
        DataHelper,
        SchoolRoomService,
        SchoolClassService,
        TimetableService,
        GradeService,
        DateTimeHelper,
        CalendarHelper,
        SubjectService,
        StudentService,
        TeacherService,
        NgbActiveModal,
        UserRouteAccessService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
            deps: [
                LocalStorageService,
                SessionStorageService
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [
                Injector
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [
                JhiEventManager
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [
                Injector
            ]
        }
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    bootstrap: [JhiMainComponent]
})
export class SchoolmeAppModule {

    constructor() {
    }
}
