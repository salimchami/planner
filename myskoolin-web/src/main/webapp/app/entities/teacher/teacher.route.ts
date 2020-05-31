import {Routes} from '@angular/router';

import {AUTHORITIES_ADMIN_USERS, UserRouteAccessService} from '../../shared';
import {ClientResolver} from '../../shared/services/resolvers/client.resolver';
import {TeacherComponent} from './teacher.component';
import {TeacherListComponent} from './teacher.list.component';
import {TeachersResolver} from '../../shared/services/resolvers/teachers.resolver';
import {TeacherResolver} from '../../shared/services/resolvers/teacher.resolver';
import {TeacherEditComponent} from './teacher.edit.component';
import {GradesResolver} from '../../shared/services/resolvers/grades.resolver';
import {StudentViewComponent} from '../student';
import {StudentResolver} from '../../shared/services/resolvers/student.resolver';
import {TeacherViewComponent} from './teacher.view.component';

export const teacherRoute: Routes = [
    {
        path: 'teachers',
        component: TeacherComponent,
        data: {
            authorities: AUTHORITIES_ADMIN_USERS,
            pageTitle: 'teacher.home.title'
        },

        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: TeacherListComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste professeurs',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'teacher.home.title'
                },
                resolve: {
                    teachers: TeachersResolver,
                },
                canActivate: [UserRouteAccessService],
            },
            {
                path: 'new', component: TeacherEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste professeurs',
                            url: 'teachers'
                        },
                        {
                            label: 'Nouve.au.lle professeur.e',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'teacher.new.title'
                },
                resolve: {
                    grades: GradesResolver,
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'view/:id', component: TeacherViewComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste professeurs',
                            url: 'teachers'
                        },
                        {
                            label: 'Détail professeur.e',
                            url: ''
                        }
                    ], authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'teacher.view.title'
                },
                resolve: {
                    teacher: TeacherResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'edit/:id', component: TeacherEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste professeurs',
                            url: 'teachers'
                        },
                        {
                            label: 'Détail professeur.e',
                            url: ''
                        }
                    ], authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'teacher.edit.title'
                },
                resolve: {
                    grades: GradesResolver,
                    teacher: TeacherResolver,
                },
                canActivate: [UserRouteAccessService]
            },
        ]
    }
];
