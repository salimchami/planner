import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import { CommonModule } from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NotificationService, SchoolmeSharedModule} from '../../shared';

import {
    GradePopupService,
    GradeComponent,
    GradeEditComponent,
    GradeListComponent,
    gradeRoute,
} from './';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {GradeViewComponent} from './grade.view.component';
import {CalendarModule, DateAdapter} from 'angular-calendar';
import {adapterFactory} from 'angular-calendar/date-adapters/date-fns';

const ENTITY_STATES = [
    ...gradeRoute,
];

@NgModule({
    imports: [
        SchoolmeSharedModule,
        NgxDatatableModule,
        CommonModule,
        ReactiveFormsModule,
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        }),
        RouterModule.forChild(ENTITY_STATES),
    ],
    declarations: [
        GradeComponent,
        GradeEditComponent,
        GradeViewComponent,
        GradeListComponent
    ],
    entryComponents: [
        GradeComponent,
        GradeEditComponent,
        GradeViewComponent,
        GradeListComponent,
    ],
    providers: [
        GradePopupService,
        NotificationService,
    ],
    exports: [
        GradeComponent,
        GradeViewComponent,
        GradeEditComponent,
        GradeListComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeGradeModule {
}
