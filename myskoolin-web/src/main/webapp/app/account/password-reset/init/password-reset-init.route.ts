import { Route } from '@angular/router';

import {AUTHORITIES_ALL, UserRouteAccessService} from '../../../shared';
import { PasswordResetInitComponent } from './password-reset-init.component';

export const passwordResetInitRoute: Route = {
    path: 'reset/request',
    component: PasswordResetInitComponent,
    data: {
        authorities: AUTHORITIES_ALL,
        pageTitle: 'global.menu.account.password'
    },
    canActivate: [UserRouteAccessService]
};
