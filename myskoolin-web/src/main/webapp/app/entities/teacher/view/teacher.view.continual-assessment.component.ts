import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client} from '../../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Teacher} from '../../../shared/model';
import {TeacherService} from '../../../shared/services/teacher.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-teacher-view-continual-assessment',
    styleUrls: [
        'teacher.view.continual-assessment.component.scss'
    ],
    templateUrl: './teacher.view.continual-assessment.component.html'
})
export class TeacherViewContinualAssessmentComponent implements OnInit, OnDestroy {
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
