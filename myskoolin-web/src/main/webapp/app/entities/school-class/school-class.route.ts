import {Routes} from '@angular/router';

import {AUTHORITIES_ADMIN_USERS, UserRouteAccessService} from '../../shared';
import {ClientResolver} from '../../shared/services/resolvers/client.resolver';
import {SchoolClassComponent} from './school-class.component';
import {SchoolClassListComponent} from './school-class.list.component';
import {SchoolClassesResolver} from '../../shared/services/resolvers/school-classes.resolver';
import {GradesResolver} from '../../shared/services/resolvers/grades.resolver';
import {SchoolClassEditComponent} from './school-class.edit.component';
import {SchoolClassViewComponent} from './school-class.view.component';
import {SchoolClassResolver} from '../../shared/services/resolvers/school-class.resolver';

export const schoolClassRoute: Routes = [
    {
        path: 'school-classes',
        component: SchoolClassComponent,
        data: {
            authorities: AUTHORITIES_ADMIN_USERS,
            pageTitle: 'student.home.title'
        },

        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: SchoolClassListComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste classes',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'schoolClass.home.title'
                },
                resolve: {
                    schoolClasses: SchoolClassesResolver,
                },
                canActivate: [UserRouteAccessService],
            },
            {
                path: 'new', component: SchoolClassEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste classes',
                            url: 'school-classes'
                        },
                        {
                            label: 'Nouvelle classe',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'schoolClass.new.title'
                },
                resolve: {
                    grades: GradesResolver,
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'edit/:id', component: SchoolClassEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste classes',
                            url: 'school-classes'
                        },
                        {
                            label: 'Modification classe',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'schoolClass.new.title'
                },
                resolve: {
                    grades: GradesResolver,
                    schoolClass: SchoolClassResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'view/:id', component: SchoolClassViewComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste classes',
                            url: 'school-classes'
                        },
                        {
                            label: 'Détail classe',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'schoolClass.view.title'
                },
                resolve: {
                    schoolClass: SchoolClassResolver,
                    client: ClientResolver,
                },
                canActivate: [UserRouteAccessService]
            }
        ]
    }
];
