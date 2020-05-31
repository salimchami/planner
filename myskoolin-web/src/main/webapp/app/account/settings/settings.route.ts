import {Route} from '@angular/router';

import {UserRouteAccessService, AUTHORITIES_ALL} from '../../shared';
import {SettingsComponent} from './settings.component';

export const settingsRoute: Route = {
    path: 'settings',
    component: SettingsComponent,
    data: {
        authorities: AUTHORITIES_ALL,
        pageTitle: 'global.menu.account.settings'
    },
    canActivate: [UserRouteAccessService]
};
