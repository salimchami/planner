import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {SchoolRoomService} from '../../shared/services/school-room.service';
import {Client, SchoolRoom} from '../../shared/model';
import {ActivatedRoute, Router} from '@angular/router';
import {NotificationService} from '../../shared';
import {Location} from '@angular/common';
import {NotificationTypes} from '../../shared/notification/notification-types';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-school-room',
    templateUrl: './school-room.view.component.html'
})
export class SchoolRoomViewComponent implements OnInit, OnDestroy {
    schoolRoom: SchoolRoom;
    client: Client;

    constructor(
        private location: Location,
        private schoolRoomService: SchoolRoomService,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.schoolRoom = this.route.snapshot.data['schoolRoom'];
    }

    ngOnDestroy() {
    }

    deleteSchoolRoom() {
        this.schoolRoomService.delete(this.schoolRoom.id).subscribe(
            (res) => {
                this.notificationService.add('schoolRoom.delete.toast-title',
                    'schoolRoom.delete.delete-successfull',
                    NotificationTypes.SUCCESS);
                this.router.navigateByUrl('/school-rooms');
            }, (err) => console.log(err));
    }

    goBack() {
        this.location.back();
    }
}
