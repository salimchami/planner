import { Route } from '@angular/router';

import { JhiConfigurationComponent } from './configuration.component';
import {Authorities} from '../../shared';

export const configurationRoute: Route = {
    path: 'jhi-configuration',
    component: JhiConfigurationComponent,
    data: {
        authorities: [Authorities.ROLE_SCHOOLME_ADMIN],
        pageTitle: 'configuration.title'
    }
};
