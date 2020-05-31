import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SchoolmeSharedModule } from '../../shared';
import {
    ContactService,
    ContactComponent,
    contactRoute,
    contactPopupRoute,
} from './';

const ENTITY_STATES = [
    ...contactRoute,
    ...contactPopupRoute,
];

@NgModule({
    imports: [
        SchoolmeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ContactComponent,
    ],
    entryComponents: [
        ContactComponent,
    ],
    providers: [
        ContactService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeContactModule {}
