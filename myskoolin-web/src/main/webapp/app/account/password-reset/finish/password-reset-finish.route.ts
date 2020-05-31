import { Route } from '@angular/router';

import {AUTHORITIES_ALL, UserRouteAccessService} from '../../../shared';
import { PasswordResetFinishComponent } from './password-reset-finish.component';

export const passwordResetFinishRoute: Route = {
    path: 'reset/finish',
    component: PasswordResetFinishComponent,
    data: {
        authorities: AUTHORITIES_ALL,
        pageTitle: 'global.menu.account.password'
    },
    canActivate: [UserRouteAccessService]
};
