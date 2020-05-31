import { Routes } from '@angular/router';

import {UserRouteAccessService, AUTHORITIES_ALL} from '../../shared';
import { ContactComponent } from './contact.component';

export const contactRoute: Routes = [
    {
        path: 'contact',
        component: ContactComponent,
        data: {
            authorities: AUTHORITIES_ALL
        },
        canActivate: [UserRouteAccessService]
    }
];

export const contactPopupRoute: Routes = [
    {
        path: 'contact-new',
        component: ContactComponent,
        data: {
            authorities: AUTHORITIES_ALL
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
