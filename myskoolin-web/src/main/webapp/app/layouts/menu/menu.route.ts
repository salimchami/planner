import { Route } from '@angular/router';

import { MenuComponent } from './menu.component';

export const menuRoute: Route = {
    path: '',
    component: MenuComponent,
    outlet: 'menu'
};
