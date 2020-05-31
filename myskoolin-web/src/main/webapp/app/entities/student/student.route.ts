import {Routes} from '@angular/router';

import {AUTHORITIES_ADMIN_USERS, UserRouteAccessService} from '../../shared';
import {StudentEditComponent} from './student.edit.component';
import {StudentListComponent} from './student.list.component';
import {ClientResolver} from '../../shared/services/resolvers/client.resolver';
import {StudentViewComponent} from './student.view.component';
import {StudentComponent} from './student.component';
import {StudentsResolver} from '../../shared/services/resolvers/students.resolver';
import {StudentResolver} from '../../shared/services/resolvers/student.resolver';
import {SchoolClassesResolver} from '../../shared/services/resolvers/school-classes.resolver';

export const studentRoute: Routes = [
    {
        path: 'students',
        component: StudentComponent,
        data: {
            authorities: AUTHORITIES_ADMIN_USERS,
            pageTitle: 'student.home.title'
        },

        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: StudentListComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste élèves',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'student.home.title'
                },
                resolve: {
                    client: ClientResolver,
                    students: StudentsResolver,
                    schoolClasses: SchoolClassesResolver,
                },
                canActivate: [UserRouteAccessService],
            },
            {
                path: 'new', component: StudentEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste élèves',
                            url: 'students'
                        },
                        {
                            label: 'Nouvel(le) élève',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'student.new.title'
                },
                resolve: {
                    client: ClientResolver,
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'edit/:id', component: StudentEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste élèves',
                            url: 'students'
                        },
                        {
                            label: 'Modification élève',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'student.edit.title'
                },
                resolve: {
                    client: ClientResolver,
                    student: StudentResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'view/:id', component: StudentViewComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste élèves',
                            url: 'students'
                        },
                        {
                            label: 'Détails élève',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'student.view.title'
                },
                resolve: {
                    client: ClientResolver,
                    student: StudentResolver
                },
                canActivate: [UserRouteAccessService]
            }
        ]
    }
];
