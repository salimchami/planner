import { Route } from '@angular/router';

import { RegisterComponent } from './register.component';
import {Authorities} from '../../shared';

export const registerRoute: Route = {
    path: 'register',
    component: RegisterComponent,
    data: {
        authorities: [Authorities.ROLE_ANONYMOUS],
        pageTitle: 'register.title'
    }
};
