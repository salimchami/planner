import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NotificationService, SchoolmeSharedModule} from '../../shared';
import {ColorPickerModule} from 'ngx-color-picker';

import {
    schoolClassRoute
} from './';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {BrowserModule} from '@angular/platform-browser';
import {SchoolClassComponent} from './school-class.component';
import {SchoolClassListComponent} from './school-class.list.component';
import {SchoolClassEditComponent} from './school-class.edit.component';
import {
    MatAutocompleteModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatNativeDateModule,
    MatProgressSpinnerModule,
    MatSelectModule
} from '@angular/material';
import {SchoolClassViewComponent} from './school-class.view.component';
import {ArchwizardModule} from 'ng2-archwizard/dist/archwizard.module';
import {SchoolClassTimetableViewComponent} from './view/school-class.timetable.view.component';
import {CalendarModule, DateAdapter} from 'angular-calendar';
import {adapterFactory} from 'angular-calendar/date-adapters/date-fns';
import {SchoolClassTeachersViewComponent} from './view/school-class.teachers.view.component';

const ENTITY_STATES = [
    ...schoolClassRoute,
];

@NgModule({
    imports: [
        SchoolmeSharedModule,
        NgxDatatableModule,
        BrowserModule,
        CommonModule,
        ReactiveFormsModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatInputModule,
        MatFormFieldModule,
        MatSelectModule,
        MatAutocompleteModule,
        MatProgressSpinnerModule,
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        }),
        ArchwizardModule.forRoot(),
        RouterModule.forChild(ENTITY_STATES),
        ColorPickerModule,
        MatIconModule,
    ],
    declarations: [
        SchoolClassComponent,
        SchoolClassTeachersViewComponent,
        SchoolClassEditComponent,
        SchoolClassListComponent,
        SchoolClassViewComponent,
        SchoolClassTimetableViewComponent,
    ],
    entryComponents: [
        SchoolClassComponent,
        SchoolClassEditComponent,
        SchoolClassListComponent,
        SchoolClassViewComponent,
        SchoolClassTimetableViewComponent,
    ],
    providers: [
        // StudentPopupService,
        NotificationService,
    ],
    exports: [
        SchoolClassComponent,
        SchoolClassEditComponent,
        SchoolClassListComponent,
        SchoolClassViewComponent,
        SchoolClassTimetableViewComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeSchoolClassModule {
}
