import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client} from '../../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Student} from '../../../shared/model';
import {StudentService} from '../../../shared/services/student.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-student-view-reports',
    styleUrls: [
        'student.view.reports.component.scss'
    ],
    templateUrl: './student.view.reports.component.html'
})
export class StudentViewReportsComponent implements OnInit, OnDestroy {
    student: Student;
    client: Client;

    constructor(
        private location: Location,
        private studentService: StudentService,
        private route: ActivatedRoute,
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.student = this.route.snapshot.data['student'];
    }

    ngOnDestroy() {
    }
}
