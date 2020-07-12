import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {APP_CONSTANTS} from '../../app.constants';
import {Client, Distance, SchoolRoom} from '../../shared/model';
import {ActivatedRoute, Router} from '@angular/router';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SchoolRoomService} from '../../shared/services/school-room.service';
import {NotificationService, Principal} from '../../shared';
import {SchoolRoomDistance} from '../../shared/model';
import {Location} from '@angular/common';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-school-edit-room',
    templateUrl: './school-room.edit.component.html',
    styleUrls: [
        './school-room.edit.component.scss',
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class SchoolRoomEditComponent implements OnInit, OnDestroy {
    schoolRoomForm: FormGroup;
    routeState: string;
    schoolRoomsTypes: any;
    client: Client;
    schoolRoomCreateConfirmation: boolean;
    // canAddDistances: boolean;
    // showDistancesAlert: boolean;
    schoolRoomsFound: Array<SchoolRoom>;
    schoolRooms: Array<SchoolRoom>;
    schoolRoomsAutocomplete: Array<any>;
    creationError: boolean;
    creationErrorMsg: string;
    schoolRoom: SchoolRoom;
    // distancesAC: Array<FormControl>;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private notificationService: NotificationService,
        private schoolRoomService: SchoolRoomService,
        private principalService: Principal,
        private fb: FormBuilder,
        private location: Location
    ) {
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.routeState = this.router.routerState.snapshot.url.endsWith('new') ? 'new' : 'edit';
        if (this.routeState === 'edit') {
            this.schoolRoom = new SchoolRoom().convert(this.route.snapshot.data['schoolRoom']);
            // this.distancesAC = [];
            // .map((distance) => {
            //     return {'schoolRoomDistance': distance.distance.value, 'schoolRoomName': distance.schoolRoomName};
            // });
            this.schoolRoomForm = new FormGroup({
                id: new FormControl(this.schoolRoom.id),
                name: new FormControl(this.schoolRoom.name, [Validators.required]),
                type: new FormControl(this.schoolRoom.type, [Validators.required]),
                surface: new FormControl(this.schoolRoom.surface, [Validators.min(2)]),
                seats: new FormControl(this.schoolRoom.seats, [Validators.min(2), Validators.pattern('[0-9]+')]),
                closed: new FormControl(this.schoolRoom.closed),
                // distances: new FormArray([]),
                timetable: new FormArray([]),
                comment: new FormControl(this.schoolRoom.comment),
                longitude: new FormControl(this.schoolRoom.longitude),
                latitude: new FormControl(this.schoolRoom.latitude),
            });
            // this.schoolRoom.distances.forEach((sr) => {
            //     this.addDistance(sr.schoolRoomName, sr.distance.value);
            // });
            // this.schoolRoomForm.setControl('distances', this.fb.array((this.schoolRoom.distances.map((sc) => {
            //     return this.schoolRooms.filter((baseSr) => baseSr.name === sc.schoolRoomName);
            // }) || []).map((x) => this.fb.group(x))));
        } else {
            this.schoolRoomForm = new FormGroup({
                id: new FormControl(),
                name: new FormControl('', [Validators.required]),
                type: new FormControl('', [Validators.required]),
                surface: new FormControl('', [Validators.min(2)]),
                seats: new FormControl('', [Validators.min(2), Validators.pattern('[0-9]+')]),
                closed: new FormControl(),
                // distances: new FormArray([]),
                timetable: new FormArray([]),
                comment: new FormControl(),
                longitude: new FormControl(),
                latitude: new FormControl(),
            });
        }
        this.schoolRoomsTypes = APP_CONSTANTS.SCHOOL_ROOMS_TYPES;
        this.client = this.route.snapshot.data['client'];
        this.schoolRooms = this.route.snapshot.data['schoolRooms'];
        this.schoolRoomsAutocomplete = [...this.schoolRooms.sort(this.sortRoomNames)];

        this.schoolRoomCreateConfirmation = false;
        this.schoolRoomsFound = [];
        // this.canAddDistances = true;
        // this.showDistancesAlert = true;
        this.creationError = false;
    }

    // initDistances(name, value) {
    //     return new FormGroup({
    //         schoolRoomName: new FormControl(name ? name : '', [Validators.required]),
    //         // distance: new FormControl(value ? value : '', [Validators.required]),
    //     });
    // }

    // removeDistance() {
    //     const distancesControl = <FormArray>this.schoolRoomForm.controls['distances'];
    //     distancesControl.removeAt(distancesControl.length - 1);
    //     // this.updateSchoolRoomsAutocomplete(null);
    // }

    // addDistance(name?, value?) {
    //     (this.schoolRoomForm.controls.distances as FormArray).push(this.initDistances(name, value));
    // }

    resetMessages() {
        this.schoolRoomCreateConfirmation = false;
        this.creationError = false;
        this.creationErrorMsg = '';
    }

    // enableAddingDistancesToSchoolRoom() {
    //     this.canAddDistances = true;
    // }

    createOrUpdateSchoolRoom() {
        const values = this.schoolRoomForm.value;
        const type = APP_CONSTANTS.SCHOOL_ROOMS_TYPES.find((value) => value.code === values.type.code);
        this.principalService.identity().then((user) => {
            const srFound = this.schoolRooms.find((schoolRoom) => schoolRoom.name === values.name);
            if (srFound && this.routeState === 'new') {
                this.creationError = true;
                this.notificationService.add('schoolRoom.new.form.toast-title',
                    'schoolRoom.new.form.name-already-exists',
                    'error');
                this.creationErrorMsg = '';
            } else {
                // const distances = values.distances.map((d) => {
                //     return new SchoolRoomDistance(d.schoolRoomName.name, new Distance(d.distance, this.client.distancesMeasurementUnit));
                // });
                switch (this.routeState) {
                    case 'edit':
                        this.schoolRoom = this.schoolRoom.update(values.name, type, values.surface, values.seats, values.closed,
                            values.timetable, values.comment, values.longitude, values.latitude);
                        if (this.schoolRoom.id == null) {
                            this.notificationService.add('Error',
                                'Id is null !!',
                                'error');

                        } else {
                            this.update();
                        }
                        break;
                    case 'new':
                        this.schoolRoom = new SchoolRoom(user.clientId, values.name, type, values.surface,
                            values.seats, values.closed, values.timetable, values.comment, values.longitude, values.latitude);
                        if (this.schoolRoom.id != null) {
                            this.notificationService.add('Error',
                                'id exists...',
                                'error');

                        } else {
                            this.create();
                        }
                        break;
                }

            }
        });
    }

    private create() {
        this.schoolRoomService.create(this.schoolRoom).subscribe(
            (res) => {
                this.notificationService.add('schoolRoom.new.form.toast-title',
                    'schoolRoom.new.form.creation-successfull',
                    'success');
                this.router.navigateByUrl('/school-rooms');
            }, (err) => console.log(err));
    }

    private update() {
        this.schoolRoomService.update(this.schoolRoom).subscribe(
            (res) => {
                this.notificationService.add('schoolRoom.edit.form.toast-title',
                    'schoolRoom.edit.form.update-successfull',
                    'success');
                this.router.navigateByUrl('/school-rooms');
            }, (err) => console.log(err));
    }

    // calculateDistanceBar(distance, distanceEvent, distancesValues) {
    //     let value = '100';
    //     if (distancesValues.length > 1) {
    //         const max = distancesValues.reduce((a, b) => {
    //             if (!a.schoolRoomDistance.length || !b.schoolRoomDistance.length
    //                 || distanceEvent === a.schoolRoomDistance || distanceEvent === b.schoolRoomDistance) {
    //                 return Math.max(a.schoolRoomDistance, b.schoolRoomDistance, distanceEvent);
    //             } else {
    //                 return Math.max(a.schoolRoomDistance, b.schoolRoomDistance);
    //             }
    //         });
    //         value = (distanceEvent * 100 / max).toFixed(2);
    //     }
    //     distance.controls.proportion.value = value;
    // }

    // updateSchoolRoomsAutocomplete(bschoolRoom: SchoolRoom) {
    //     this.schoolRoomsAutocomplete = [...this.schoolRooms
    //         .filter((schoolRoom) => {
    //             return schoolRoom.name !== bschoolRoom.name
    //                 && !this.schoolRoomForm.controls.distances.value
    //                     .find((value) => {
    //                         return schoolRoom.name === value.schoolRoomName;
    //                     });
    //         })].sort(this.sortRoomNames);
    // }

    compareSchoolRoomsTypes(type1, type2) {
        return type1.code === type2.code;
    }

    compareSchoolRoomsNames(schoolRoom1, schoolRoom2) {
        return schoolRoom1 === schoolRoom2;
    }

    goBack() {
        this.location.back();
    }

    sortRoomNames(schoolRoom1: SchoolRoom, schoolRoom2: SchoolRoom) {
        if (schoolRoom1.name > schoolRoom2.name) {
            return -1;
        }
        if (schoolRoom1.name < schoolRoom2.name) {
            return 1;
        }
        return 0;
    }

    // distancesControls() {
    //     return this.schoolRoomForm.get('distances') ? this.schoolRoomForm.get('distances')['controls'] : [];
    // }
}
