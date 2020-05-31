import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NotificationService, SchoolmeSharedModule} from '../../shared';
import {ColorPickerModule} from 'ngx-color-picker';

import {
    SubjectPopupService,
    SubjectComponent,
    SubjectListComponent,
    subjectRoute, SubjectEditComponent,
} from './';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {SubjectViewComponent} from './subject.view.component';
import {BrowserModule} from '@angular/platform-browser';
import {MatCheckboxModule, MatFormFieldModule, MatInputModule, MatSelectModule} from '@angular/material';

const ENTITY_STATES = [
    ...subjectRoute,
];

@NgModule({
    imports: [
        SchoolmeSharedModule,
        NgxDatatableModule,
        BrowserModule,
        CommonModule,
        ReactiveFormsModule,
        MatInputModule,
        MatFormFieldModule,
        MatCheckboxModule,
        MatSelectModule,
        RouterModule.forChild(ENTITY_STATES),
        ColorPickerModule,
    ],
    declarations: [
        SubjectComponent,
        SubjectEditComponent,
        SubjectViewComponent,
        SubjectListComponent
    ],
    entryComponents: [
        SubjectComponent,
        SubjectEditComponent,
        SubjectViewComponent,
        SubjectListComponent,
    ],
    providers: [
        SubjectPopupService,
        NotificationService,
    ],
    exports: [
        SubjectComponent,
        SubjectViewComponent,
        SubjectEditComponent,
        SubjectListComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeSubjectModule {
}
