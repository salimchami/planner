import {Route} from '@angular/router';

import {HomeComponent} from './';
import {AUTHORITIES_AUTHENTICATED_USERS} from '../shared';

export const HOME_ROUTE: Route = {
    path: '',
    component: HomeComponent,
    data: {
        authorities: AUTHORITIES_AUTHENTICATED_USERS,
        pageTitle: 'home.title'
    }
};
