import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NotificationService, SchoolmeSharedModule} from '../../shared';

import {
    clientRoute,
} from './';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {ClientComponent} from './client.component';
import {ClientInfosEditComponent} from './edit/client.infos.edit.component';
import {ClientViewComponent} from './client.view.component';
import {ClientViewBasicInfosComponent} from './views/client.view.basic-infos.component';
import {ClientViewContractComponent} from './views/client.view.contract.component';
import {ClientViewTimetablesComponent} from './views/client.view.timetables.component';
import {CalendarModule, DateAdapter} from 'angular-calendar';
import {adapterFactory} from 'angular-calendar/date-adapters/date-fns';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ClientContractEditComponent} from './edit/client.contract.edit.component';
import {MatNativeDateModule} from '@angular/material/core';
import {MatIconModule} from '@angular/material/icon';
import {MatDatepickerModule} from '@angular/material/datepicker';

const ENTITY_STATES = [
    ...clientRoute,
];

@NgModule({
    imports: [
        SchoolmeSharedModule,
        NgxDatatableModule,
        CommonModule,
        ReactiveFormsModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,
        MatNativeDateModule,
        MatSelectModule,
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        }),
        RouterModule.forChild(ENTITY_STATES),
    ],
    declarations: [
        ClientComponent,
        ClientInfosEditComponent,
        ClientContractEditComponent,
        ClientViewComponent,
        ClientViewBasicInfosComponent,
        ClientViewContractComponent,
        ClientViewTimetablesComponent,
    ],
    entryComponents: [
        ClientComponent,
        ClientInfosEditComponent,
        ClientContractEditComponent,
        ClientViewComponent,
        ClientViewBasicInfosComponent,
        ClientViewContractComponent,
        ClientViewTimetablesComponent,
    ],
    providers: [
        NotificationService,
    ],
    exports: [
        ClientComponent,
        ClientViewComponent,
        ClientInfosEditComponent,
        ClientContractEditComponent,
        ClientViewBasicInfosComponent,
        ClientViewContractComponent,
        ClientViewTimetablesComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolmeClientModule {
}
