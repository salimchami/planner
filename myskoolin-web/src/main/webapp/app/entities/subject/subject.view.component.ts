import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client} from '../../shared/model';
import {ActivatedRoute, Router} from '@angular/router';
import {NotificationService} from '../../shared';
import {Location} from '@angular/common';
import {Subject} from '../../shared/model';
import {SubjectService} from '../../shared/services/subject.service';
import {DataHelper} from '../../shared/services/utils/data.helper';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-subject',
    styleUrls: [
        'subject.view.component.scss'
    ],
    templateUrl: './subject.view.component.html'
})
export class SubjectViewComponent implements OnInit, OnDestroy {
    subjects: Array<Subject>;
    subject: Subject;
    client: Client;

    constructor(
        private location: Location,
        private subjectService: SubjectService,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
        private dataHelper: DataHelper
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.subject = this.route.snapshot.data['subject'];
        this.subjects = this.route.snapshot.data['subjects'];
    }

    ngOnDestroy() {
    }

    deleteSchoolRoom() {
        this.subjectService.delete(this.subject.id).subscribe(
            (res) => {
                this.notificationService.add('subject.delete.toast-title',
                    'subject.delete.delete-successfull',
                    'success');
                this.router.navigateByUrl('/subjects');
            }, (err) => console.log(err));
    }

    goBack() {
        this.location.back();
    }
}
