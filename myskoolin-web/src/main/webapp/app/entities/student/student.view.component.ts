import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client, Student} from '../../shared/model';
import {ActivatedRoute, Router} from '@angular/router';
import {NotificationService} from '../../shared';
import {Location} from '@angular/common';
import {StudentService} from '../../shared/services/student.service';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-student',
    styleUrls: [
        'student.view.component.scss'
    ],
    templateUrl: './student.view.component.html'
})
export class StudentViewComponent implements OnInit, OnDestroy {
    students: Array<Student>;
    student: Student;
    client: Client;

    constructor(
        private location: Location,
        private studentService: StudentService,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
        private dateHelper: DateTimeHelper
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.student = this.route.snapshot.data['student'];
    }

    ngOnDestroy() {
    }

    deleteStudent() {
        this.studentService.delete(this.student.id).subscribe(
            (res) => {
                this.notificationService.addToast('student.delete.toast-title',
                    'student.delete.delete-successful',
                    'success');
                this.router.navigateByUrl('/students');
            }, (err) => console.log(err));
    }

    goBack() {
        this.location.back();
    }
}
