import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SchoolmeSharedModule } from '../../shared';
import {
    NewsletterService,
    NewsletterComponent,
    newsletterRoute
} from './';

const ENTITY_STATES = [
    ...newsletterRoute
];

@NgModule({
    imports: [
        SchoolmeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        NewsletterComponent,
    ],
    entryComponents: [
        NewsletterComponent,
    ],
    providers: [
        NewsletterService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeNewsletterModule {}
