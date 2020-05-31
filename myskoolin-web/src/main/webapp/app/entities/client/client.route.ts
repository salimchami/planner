import {Routes} from '@angular/router';

import {AUTHORITIES_ADMIN_USERS, UserRouteAccessService} from '../../shared';
import {ClientComponent} from './client.component';
import {ClientViewComponent} from './client.view.component';
import {ClientInfosEditComponent} from './edit/client.infos.edit.component';
import {ClientResolver} from '../../shared/services/resolvers/client.resolver';
import {ClientContractEditComponent} from './edit/client.contract.edit.component';
import {PricingsResolver} from '../../shared/services/resolvers/pricings.resolver';

export const clientRoute: Routes = [
    {
        path: 'school-account',
        component: ClientComponent,
        data: {
            authorities: AUTHORITIES_ADMIN_USERS,
            pageTitle: 'client.home.title'
        },

        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '', component: ClientViewComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Paramètres compte',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'client.view.title'
                },
                resolve: {
                    client: ClientResolver,
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'infos/edit/:id', component: ClientInfosEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Paramètres compte',
                            url: '/school-account'
                        },
                        {
                            label: 'Modification Infos',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'client.edit.infos.title'
                },
                resolve: {
                    client: ClientResolver,
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'contract/edit/:id', component: ClientContractEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Paramètres compte',
                            url: '/school-account'
                        },
                        {
                            label: 'Demande modification contrat',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'client.edit.contract.title'
                },
                resolve: {
                    client: ClientResolver,
                    pricings: PricingsResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'timetable/edit/:id', component: ClientInfosEditComponent,
                data: {
                    breadcrumb: 'client.edit.timetable.title',
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'client.edit.timetable.title'
                },
                resolve: {
                    client: ClientResolver,
                },
                canActivate: [UserRouteAccessService]
            },
        ]
    }
];
