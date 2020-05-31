import {Routes} from '@angular/router';

import {AUTHORITIES_ADMIN_USERS, UserRouteAccessService} from '../../shared';
import {SubjectEditComponent} from './subject.edit.component';
import {SubjectListComponent} from './subject.list.component';
import {ClientResolver} from '../../shared/services/resolvers/client.resolver';
import {SubjectViewComponent} from './subject.view.component';
import {SubjectComponent} from './subject.component';
import {SubjectsResolver} from '../../shared/services/resolvers/subject.resolver';
import {SubjectResolver} from '../../shared/services/resolvers/subject-resolver.service';
import {GradesResolver} from '../../shared/services/resolvers/grades.resolver';

export const subjectRoute: Routes = [
    {
        path: 'subjects',
        component: SubjectComponent,
        data: {
            authorities: AUTHORITIES_ADMIN_USERS,
            pageTitle: 'subject.home.title'
        },

        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: SubjectListComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste matières',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'subject.home.title'
                },
                resolve: {
                    client: ClientResolver,
                    subjects: SubjectsResolver
                },
                canActivate: [UserRouteAccessService],
            },
            {
                path: 'new', component: SubjectEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste matières',
                            url: 'subjects'
                        },
                        {
                            label: 'Nouvelle matière',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'subject.new.title'
                },
                resolve: {
                    client: ClientResolver,
                    subjects: SubjectsResolver,
                    grades: GradesResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'edit/:id', component: SubjectEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste matières',
                            url: 'subjects'
                        },
                        {
                            label: 'Modification matière',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'subject.edit.title'
                },
                resolve: {
                    client: ClientResolver,
                    subjects: SubjectsResolver,
                    subject: SubjectResolver,
                    grades: GradesResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'view/:id', component: SubjectViewComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste matières',
                            url: 'subjects'
                        },
                        {
                            label: 'Détail matière',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'subject.view.title'
                },
                resolve: {
                    client: ClientResolver,
                    subjects: SubjectsResolver,
                    subject: SubjectResolver,
                },
                canActivate: [UserRouteAccessService]
            }
        ]
    }
];
