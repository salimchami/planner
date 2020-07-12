import {Routes} from '@angular/router';

import {AUTHORITIES_ADMIN_USERS, UserRouteAccessService} from '../../shared';
import {TimetableListComponent} from './timetable.list.component';
import {TimetableComponent} from './timetable.component';
import {ClientResolver} from './../../shared/services/resolvers/client.resolver';
import {SchoolClassesResolver} from './../../shared/services/resolvers/school-classes.resolver';
import {GradesResolver} from './../../shared/services/resolvers/grades.resolver';
import {SubjectsResolver} from './../../shared/services/resolvers/subject.resolver';
import {SchoolRoomsResolver} from '../../shared/services/resolvers/school-rooms.resolver';
import {TeachersResolver} from '../../shared/services/resolvers/teachers.resolver';
import {TimeTablesResolver} from '../../shared/services/resolvers/timetables.resolver';

export const teacherRoute: Routes = [
    {
        path: 'timetables',
        component: TimetableComponent,
        data: {
            authorities: AUTHORITIES_ADMIN_USERS,
            pageTitle: 'timetable.home.title'
        },

        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: TimetableListComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Emplois du temps',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'timetable.home.title'
                },
                resolve: {
                    schoolClasses: SchoolClassesResolver,
                    client: ClientResolver,
                    grades: GradesResolver,
                    subjects: SubjectsResolver,
                    schoolRooms: SchoolRoomsResolver,
                    teachers: TeachersResolver,
                    timetables: TimeTablesResolver,
                },
                canActivate: [UserRouteAccessService],
            },
        ]
    }
];
