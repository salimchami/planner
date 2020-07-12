import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NotificationService, SchoolmeSharedModule} from '../../shared';
import {ColorPickerModule} from 'ngx-color-picker';

import {
    teacherRoute,
} from './timetable.route';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {BrowserModule} from '@angular/platform-browser';
import {ArchwizardModule} from 'ng2-archwizard/dist/archwizard.module';
import {MatDatepickerModule, MatFormFieldModule, MatInputModule, MatNativeDateModule, MatSelectModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TimetableListComponent} from './timetable.list.component';
import {TimetableComponent} from '../timetable/timetable.component';
import {TimetableSchoolClassesComponent} from './schoolclasses/timetable-schoolclasses.component';
import {CalendarModule, DateAdapter} from 'angular-calendar';
import {adapterFactory} from 'angular-calendar/date-adapters/date-fns';
import {MatExpansionModule} from '@angular/material/expansion';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {TimeTablesResolver} from '../../shared/services/resolvers/timetables.resolver';
import {TimeTableOptionsResolver} from '../../shared/services/resolvers/timetableoptions.resolver';

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
        MatExpansionModule,
        MatInputModule,
        MatFormFieldModule,
        MatSelectModule,
        NgbModule,
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        }),
        ArchwizardModule.forRoot(),
        RouterModule.forChild(ENTITY_STATES),
    ],
    declarations: [
        TimetableComponent,
        TimetableSchoolClassesComponent,
        TimetableListComponent,
    ],
    entryComponents: [
        TimetableComponent,
        TimetableSchoolClassesComponent,
        TimetableListComponent,
    ],
    providers: [
        NotificationService,
        TimeTablesResolver,
        TimeTableOptionsResolver,
    ],
    exports: [
        TimetableComponent,
        TimetableSchoolClassesComponent,
        TimetableListComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeTimetableModule {
}
