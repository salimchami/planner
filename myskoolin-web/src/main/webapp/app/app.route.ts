import {Routes} from '@angular/router';

import {NavbarComponent} from './layouts';

export const navbarRoute: Routes = [{
    path: '',
    component: NavbarComponent,
    outlet: 'navbar',
    data: {
        breadcrumb: 'home.title'
    },
}];
