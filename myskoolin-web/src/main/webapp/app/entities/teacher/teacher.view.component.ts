import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Teacher} from '../../shared/model';
import {ActivatedRoute, Router} from '@angular/router';
import {NotificationService} from '../../shared';
import {Location} from '@angular/common';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {TeacherService} from '../../shared/services/teacher.service';
import {Absence} from '../../shared/model/sub/absence.model';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-teacher',
    styleUrls: [
        'teacher.view.component.scss'
    ],
    templateUrl: './teacher.view.component.html'
})
export class TeacherViewComponent implements OnInit, OnDestroy {
    teacher: Teacher;

    constructor(
        private location: Location,
        private teacherService: TeacherService,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
        private dateHelper: DateTimeHelper
    ) {
    }

    ngOnInit() {
        this.teacher = this.route.snapshot.data['teacher'];
    }

    ngOnDestroy() {
    }

    deleteTeacher() {
        this.teacherService.delete(this.teacher.id).subscribe(
            (res) => {
                this.notificationService.add('teacher.delete.toast-title',
                    'teacher.delete.delete-successful',
                    'success');
                this.router.navigateByUrl('/teachers');
            }, (err) => console.log(err));
    }

    goBack() {
        this.location.back();
    }

    isAbsentNow() {
        return false;
    }

}
