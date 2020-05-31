import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NotificationService, SchoolmeSharedModule} from '../../shared';
import {ColorPickerModule} from 'ngx-color-picker';

import {
    teacherRoute,
} from './teacher.route';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {BrowserModule} from '@angular/platform-browser';
import {ArchwizardModule} from 'ng2-archwizard/dist/archwizard.module';
import {MatDatepickerModule, MatFormFieldModule, MatInputModule, MatNativeDateModule, MatSelectModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TeacherComponent} from './teacher.component';
import {TeacherListComponent} from './teacher.list.component';
import {TeacherEditComponent} from './teacher.edit.component';
import {TeacherViewComponent} from './teacher.view.component';
import {TeacherViewAbsencesComponent} from './view/teacher.view.absences.component';
import {TeacherViewContinualAssessmentComponent} from './view/teacher.view.continual-assessment.component';
import {TeacherViewDailyBookComponent} from './view/teacher.view.daily-book.component';
import {TeacherViewTimetablesComponent} from './view/teacher.view.timetables.component';

const ENTITY_STATES = [
    ...teacherRoute,
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
        MatInputModule,
        MatFormFieldModule,
        MatSelectModule,
        ArchwizardModule.forRoot(),
        RouterModule.forChild(ENTITY_STATES),
    ],
    declarations: [
        TeacherComponent,
        TeacherListComponent,
        TeacherEditComponent,
        TeacherViewComponent,
        TeacherViewAbsencesComponent,
        TeacherViewContinualAssessmentComponent,
        TeacherViewDailyBookComponent,
        TeacherViewTimetablesComponent,
    ],
    entryComponents: [
        TeacherComponent,
        TeacherListComponent,
        TeacherEditComponent,
        TeacherViewComponent,
        TeacherViewAbsencesComponent,
        TeacherViewContinualAssessmentComponent,
        TeacherViewDailyBookComponent,
        TeacherViewTimetablesComponent,
    ],
    providers: [
        NotificationService,
    ],
    exports: [
        TeacherComponent,
        TeacherListComponent,
        TeacherEditComponent,
        TeacherViewComponent,
        TeacherViewAbsencesComponent,
        TeacherViewContinualAssessmentComponent,
        TeacherViewDailyBookComponent,
        TeacherViewTimetablesComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeTeacherModule {
}
