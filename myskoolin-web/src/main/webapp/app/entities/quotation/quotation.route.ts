import { Routes } from '@angular/router';

import { AUTHORITIES_ALL } from '../../shared';
import { APP_CONSTANTS } from '../../app.constants';
import { QuotationComponent } from './quotation.component';

export const quotationRoute: Routes = [
    {
        path: 'quotation',
        component: QuotationComponent,
        data: {
            authorities: AUTHORITIES_ALL,
            pageTitle: 'quotation.title'
        }

    }
];
