import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client} from '../../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Student} from '../../../shared/model';
import {StudentService} from '../../../shared/services/student.service';
import {DateTimeHelper} from '../../../shared/services/utils/date-time-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-student-view-responsibles',
    styleUrls: [
        'student.view.responsibles.component.scss'
    ],
    templateUrl: './student.view.responsibles.component.html'
})
export class StudentViewResponsiblesComponent implements OnInit, OnDestroy {
    student: Student;
    client: Client;

    constructor(
        private location: Location,
        private studentService: StudentService,
        private route: ActivatedRoute,
        private dateHelper: DateTimeHelper
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.student = this.route.snapshot.data['student'];
    }

    ngOnDestroy() {
    }
}
