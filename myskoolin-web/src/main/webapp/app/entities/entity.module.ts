import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {SchoolmeContactModule} from './contact/contact.module';
import {SchoolmeNewsletterModule} from './newsletter/newsletter.module';
import {SchoolmeQuotationModule} from './quotation/quotation.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SchoolmeSchoolRoomModule} from './school-room/school-room.module';
import {SchoolmeGradeModule} from './grade/grade.module';
import {SchoolmeSubjectModule} from './subject/subject.module';
import {SchoolmeStudentModule} from './student/student.module';
import {SchoolmeSchoolClassModule} from './school-class';
import {SchoolmeSharedModule} from '../shared';
import {SchoolmeTeacherModule} from './teacher';
import {SchoolmeTimetableModule} from './timetable/timetable.module';
import {SchoolmeClientModule} from './client/client.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        SchoolmeSharedModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        BrowserAnimationsModule,
        CommonModule,
        SchoolmeContactModule,
        SchoolmeSubjectModule,
        SchoolmeStudentModule,
        SchoolmeSchoolClassModule,
        SchoolmeNewsletterModule,
        SchoolmeQuotationModule,
        SchoolmeSchoolRoomModule,
        SchoolmeGradeModule,
        SchoolmeClientModule,
        SchoolmeTeacherModule,
        SchoolmeTimetableModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeEntityModule {
}
