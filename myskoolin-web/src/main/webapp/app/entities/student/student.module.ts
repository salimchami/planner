import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NotificationService, SchoolmeSharedModule} from '../../shared';
import {ColorPickerModule} from 'ngx-color-picker';

import {
    StudentPopupService,
    StudentComponent,
    StudentListComponent,
    studentRoute, StudentEditComponent,
} from './';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {StudentViewComponent} from './student.view.component';
import {BrowserModule} from '@angular/platform-browser';
import {ArchwizardModule} from 'ng2-archwizard/dist/archwizard.module';
import {StudentViewTimetablesComponent} from './view/student.view.timetables.component';
import {StudentViewResponsiblesComponent} from './view/student.view.responsibles.component';
import {StudentViewContinualAssessmentComponent} from './view/student.view.continual-assessment.component';
import {StudentViewAbsencesComponent} from './view/student.view.absences.component';
import {StudentViewDailyBookComponent} from './view/student.view.daily-book.component';
import {StudentViewDelaysComponent} from './view/student.view.delays.component';
import {StudentViewSanctionsComponent} from './view/student.view.sanctions.component';
import {StudentViewReportsComponent} from './view/student.view.reports.component';
import {StudentViewOrientationComponent} from './view/student.view.orientation.component';
import {MatDatepickerModule, MatNativeDateModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

const ENTITY_STATES = [
    ...studentRoute,
];

@NgModule({
    imports: [
        BrowserAnimationsModule,
        SchoolmeSharedModule,
        NgxDatatableModule,
        BrowserModule,
        CommonModule,
        ReactiveFormsModule,
        ColorPickerModule,
        MatDatepickerModule,
        MatNativeDateModule,
        ArchwizardModule.forRoot(),
        RouterModule.forChild(ENTITY_STATES),
    ],
    declarations: [
        StudentComponent,
        StudentEditComponent,
        StudentViewComponent,
        StudentViewTimetablesComponent,
        StudentViewContinualAssessmentComponent,
        StudentViewDailyBookComponent,
        StudentViewAbsencesComponent,
        StudentViewDelaysComponent,
        StudentViewSanctionsComponent,
        StudentViewReportsComponent,
        StudentViewOrientationComponent,
        StudentListComponent,
        StudentViewResponsiblesComponent
    ],
    entryComponents: [
        StudentComponent,
        StudentEditComponent,
        StudentViewTimetablesComponent,
        StudentViewResponsiblesComponent,
        StudentViewContinualAssessmentComponent,
        StudentViewDailyBookComponent,
        StudentViewAbsencesComponent,
        StudentViewDelaysComponent,
        StudentViewSanctionsComponent,
        StudentViewReportsComponent,
        StudentViewOrientationComponent,
        StudentViewComponent,
        StudentListComponent,
    ],
    providers: [
        StudentPopupService,
        NotificationService,
    ],
    exports: [
        StudentComponent,
        StudentViewComponent,
        StudentViewTimetablesComponent,
        StudentViewResponsiblesComponent,
        StudentViewContinualAssessmentComponent,
        StudentViewDailyBookComponent,
        StudentViewAbsencesComponent,
        StudentViewDelaysComponent,
        StudentViewSanctionsComponent,
        StudentViewReportsComponent,
        StudentViewOrientationComponent,
        StudentEditComponent,
        StudentListComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeStudentModule {
}
