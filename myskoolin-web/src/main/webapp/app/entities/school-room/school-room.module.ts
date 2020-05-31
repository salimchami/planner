import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import { CommonModule } from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NotificationService, SchoolmeSharedModule} from '../../shared';

import {
    SchoolRoomPopupService,
    SchoolRoomComponent,
    schoolRoomRoute,
    SchoolRoomEditComponent,
    SchoolRoomListComponent,
} from './';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {SchoolRoomViewComponent} from './school-room.view.component';
import {MatAutocompleteModule, MatDatepickerModule, MatFormFieldModule, MatInputModule, MatNativeDateModule, MatProgressSpinnerModule, MatSelectModule} from '@angular/material';

const ENTITY_STATES = [
    ...schoolRoomRoute,
];

@NgModule({
    imports: [
        SchoolmeSharedModule,
        NgxDatatableModule,
        CommonModule,
        ReactiveFormsModule,
        MatInputModule,
        MatFormFieldModule,
        MatSelectModule,
        MatAutocompleteModule,
        RouterModule.forChild(ENTITY_STATES),
    ],
    declarations: [
        SchoolRoomComponent,
        SchoolRoomEditComponent,
        SchoolRoomViewComponent,
        SchoolRoomListComponent
    ],
    entryComponents: [
        SchoolRoomComponent,
        SchoolRoomEditComponent,
        SchoolRoomViewComponent,
        SchoolRoomListComponent,
    ],
    providers: [
        SchoolRoomPopupService,
        NotificationService
    ],
    exports: [
        SchoolRoomComponent,
        SchoolRoomViewComponent,
        SchoolRoomEditComponent,
        SchoolRoomListComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeSchoolRoomModule {
}
