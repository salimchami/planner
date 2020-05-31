import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client} from '../../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Teacher} from '../../../shared/model';
import {TeacherService} from '../../../shared/services/teacher.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-teacher-view-timetables',
    styleUrls: [
        'teacher.view.timetables.component.scss'
    ],
    templateUrl: './teacher.view.timetables.component.html'
})
export class TeacherViewTimetablesComponent implements OnInit, OnDestroy {
    teacher: Teacher;
    client: Client;

    constructor(
        private location: Location,
        private teacherService: TeacherService,
        private route: ActivatedRoute,
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.teacher = this.route.snapshot.data['teacher'];
    }

    ngOnDestroy() {
    }
}
