import {Routes} from '@angular/router';

import {AUTHORITIES_ADMIN_USERS, UserRouteAccessService} from '../../shared';
import {SchoolRoomEditComponent} from './school-room.edit.component';
import {SchoolRoomListComponent} from './school-room.list.component';
import {ClientResolver} from '../../shared/services/resolvers/client.resolver';
import {SchoolRoomsResolver} from '../../shared/services/resolvers/school-rooms.resolver';
import {SchoolRoomViewComponent} from './school-room.view.component';
import {SchoolRoomBynameResolver} from '../../shared/services/resolvers/school-room-byname.resolver';
import {SchoolRoomComponent} from './school-room.component';

export const schoolRoomRoute: Routes = [
    {
        path: 'school-rooms',
        component: SchoolRoomComponent,
        data: {
            authorities: AUTHORITIES_ADMIN_USERS,
            pageTitle: 'schoolRoom.home.title'
        },

        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: SchoolRoomListComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Salles de cours',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'schoolRoom.home.title'
                },
                resolve: {
                    client: ClientResolver,
                    schoolRooms: SchoolRoomsResolver
                },
                canActivate: [UserRouteAccessService],
            },
            {
                path: 'new', component: SchoolRoomEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste salles',
                            url: 'school-rooms'
                        },
                        {
                            label: 'Nouvelle salle de cours',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'schoolRoom.new.title'
                },
                resolve: {
                    client: ClientResolver,
                    schoolRooms: SchoolRoomsResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'edit/:name', component: SchoolRoomEditComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste salles',
                            url: 'school-rooms'
                        },
                        {
                            label: 'Modification salle de cours',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'schoolRoom.edit.title'
                },
                resolve: {
                    client: ClientResolver,
                    schoolRoom: SchoolRoomBynameResolver,
                    schoolRooms: SchoolRoomsResolver
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'view/:name', component: SchoolRoomViewComponent,
                data: {
                    breadcrumb: [
                        {
                            label: 'Accueil',
                            url: '/'
                        },
                        {
                            label: 'Liste salles',
                            url: 'school-rooms'
                        },
                        {
                            label: 'Détail salle de cours',
                            url: ''
                        }
                    ],
                    authorities: AUTHORITIES_ADMIN_USERS,
                    pageTitle: 'schoolRoom.view.title'
                },
                resolve: {
                    client: ClientResolver,
                    schoolRoom: SchoolRoomBynameResolver
                },
                canActivate: [UserRouteAccessService]
            }
        ]
    }
];
