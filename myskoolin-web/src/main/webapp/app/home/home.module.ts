import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SchoolmeSharedModule} from '../shared';
import {HOME_ROUTE, HomeComponent} from './';
import {JhiLandingpageComponent} from '../layouts';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
    imports: [
        BrowserAnimationsModule,
        SchoolmeSharedModule,
        RouterModule.forChild([HOME_ROUTE]),
        FormsModule,
        ReactiveFormsModule,
    ],
    declarations: [
        HomeComponent,
        JhiLandingpageComponent
    ],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeHomeModule {
}
