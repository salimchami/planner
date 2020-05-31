import { Route } from '@angular/router';

import { UserRouteAccessService, AUTHORITIES_ALL } from '../../shared';
import { PasswordComponent } from './password.component';

export const passwordRoute: Route = {
    path: 'password',
    component: PasswordComponent,
    data: {
        authorities: AUTHORITIES_ALL,
        pageTitle: 'global.menu.account.password'
    },
    canActivate: [UserRouteAccessService]
};
