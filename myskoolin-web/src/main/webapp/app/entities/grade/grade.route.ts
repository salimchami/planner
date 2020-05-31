import {Routes} from '@angular/router';

import {AUTHORITIES_ADMIN_USERS, UserRouteAccessService} from '../../shared';
import {GradeEditComponent} from './grade.edit.component';
import {GradeListComponent} from './grade.list.component';
import {ClientResolver} from '../../shared/services/resolvers/client.resolver';
import {GradeViewComponent} from './grade.view.component';
import {GradeComponent} from './grade.component';
import {GradeBynameResolver} from '../../shared/services/resolvers/grade-byname.resolver';
import {GradesResolver} from '../../shared/services/resolvers/grades.resolver';

export const gradeRoute: Routes = [
    {
        path: 'grades',
        component: GradeComponent,
        data: {
            authorities: AUTHORITIES_ADMIN_USERS,
            pageTitle: 'grade.home.title'
        },

        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: GradeListComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste niveaux scolaires',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'grade.home.title'
                },
                resolve: {
                    client: ClientResolver,
                    grades: GradesResolver
                },
                canActivate: [UserRouteAccessService],
            },
            {
                path: 'new', component: GradeEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste niveaux scolaires',
                            url: 'grades'
                        },
                        {
                            label: 'Création niveau scolaire',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'grade.new.title'
                },
                resolve: {
                    client: ClientResolver,
                    grades: GradesResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'edit/:name', component: GradeEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste niveaux scolaires',
                            url: 'grades'
                        },
                        {
                            label: 'Modification niveau scolaire',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'grade.edit.title'
                },
                resolve: {
                    client: ClientResolver,
                    grade: GradeBynameResolver,
                    grades: GradesResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'view/:name', component: GradeViewComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste niveaux scolaires',
                            url: 'grades'
                        },
                        {
                            label: 'Détail niveau scolaire',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'grade.view.title'
                },
                resolve: {
                    client: ClientResolver,
                    grades: GradesResolver,
                    grade: GradeBynameResolver,
                },
                canActivate: [UserRouteAccessService]
            }
        ]
    }
];
