import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {DatePipe} from '@angular/common';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import {PERFECT_SCROLLBAR_CONFIG, PerfectScrollbarConfigInterface, PerfectScrollbarModule} from 'ngx-perfect-scrollbar';
import {ClickOutsideModule} from 'ng-click-outside';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {
    AcademicYearsService,
    AccordionAnchorDirective,
    AccordionDirective,
    AccordionLinkDirective,
    AccountService,
    AuthServerProvider,
    CardComponent,
    CardToggleDirective,
    CSRFService,
    HasAnyAuthorityDirective,
    JhiLoginModalComponent,
    JhiTrackerService,
    LoginModalService,
    LoginService,
    ModalAnimationComponent,
    ModalBasicComponent,
    Principal,
    SchoolmeSharedCommonModule,
    SchoolmeSharedLibsModule,
    SpinnerComponent,
    StateStorageService,
    ToggleFullScreenDirective,
    UserService,
    NotificationService,
} from './';
import {JhiAcademicYearsComponent} from './academic-years/academic-years.component';
import {ScrollToModule} from '@nicky-lenaers/ngx-scroll-to';
import {WizardModule} from 'ngx-archwizard';
import {WizardState} from 'ng2-archwizard';
import {ArraySortPipe} from './services/pipes/sort-pipe';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
    suppressScrollX: true
};

@NgModule({
    imports: [
        BrowserAnimationsModule,
        ClickOutsideModule,
        CommonModule,
        FormsModule,
        HttpClientModule,
        PerfectScrollbarModule,
        ReactiveFormsModule,
        RouterModule,
        SchoolmeSharedCommonModule,
        SchoolmeSharedLibsModule,
        WizardModule.forRoot(),
        ScrollToModule.forRoot(),
        NgbModule,
    ],
    declarations: [
        AccordionAnchorDirective,
        AccordionDirective,
        AccordionLinkDirective,
        CardComponent,
        CardToggleDirective,
        HasAnyAuthorityDirective,
        JhiAcademicYearsComponent,
        JhiLoginModalComponent,
        ModalAnimationComponent,
        ModalBasicComponent,
        SpinnerComponent,
        ToggleFullScreenDirective,
        ArraySortPipe,
    ],
    providers: [
        AcademicYearsService,
        AccountService,
        AuthServerProvider,
        CSRFService,
        DatePipe,
        JhiTrackerService,
        LoginModalService,
        LoginService,
        Principal,
        StateStorageService,
        UserService,
        NotificationService,
        WizardState,
        {
            provide: PERFECT_SCROLLBAR_CONFIG,
            useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
        },
    ],
    entryComponents: [JhiLoginModalComponent],
    exports: [
        AccordionAnchorDirective,
        AccordionDirective,
        AccordionLinkDirective,
        CardComponent,
        CardToggleDirective,
        ClickOutsideModule,
        DatePipe,
        HasAnyAuthorityDirective,
        HttpClientModule,
        JhiAcademicYearsComponent,
        JhiLoginModalComponent,
        ModalAnimationComponent,
        ModalBasicComponent,
        NgbModule,
        PerfectScrollbarModule,
        SchoolmeSharedCommonModule,
        SpinnerComponent,
        ToggleFullScreenDirective,
        ArraySortPipe,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class SchoolmeSharedModule {
}
