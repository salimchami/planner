import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {SchoolmeSharedModule} from '../../shared';
import {
    QuotationService,
    QuotationComponent,
    quotationRoute
} from './';

const ENTITY_STATES = [
    ...quotationRoute
];

@NgModule({
    imports: [
        SchoolmeSharedModule,
        ReactiveFormsModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        QuotationComponent,
    ],
    entryComponents: [
        QuotationComponent,
    ],
    providers: [
        QuotationService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeQuotationModule {
}
