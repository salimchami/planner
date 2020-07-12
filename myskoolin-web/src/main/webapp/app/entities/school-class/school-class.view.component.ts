import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SchoolClass} from '../../shared/model';
import {Location} from '@angular/common';
import {NotificationService} from '../../shared';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {SchoolClassService} from '../../shared/services/school-class.service';
import {NotificationTypes} from '../../shared/notification/notification-types';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-student-list',
    templateUrl: './school-class.view.component.html',
    styleUrls: [
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class SchoolClassViewComponent implements OnInit, OnDestroy {
    schoolClass: SchoolClass;
    studentsRowsNumber: number;

    constructor(
        private location: Location,
        private schoolClassService: SchoolClassService,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
        private dateHelper: DateTimeHelper
    ) {
    }

    ngOnInit() {
        this.schoolClass = this.route.snapshot.data['schoolClass'];
        this.studentsRowsNumber = 20;
    }

    ngOnDestroy() {
    }

    deleteSchoolClass() {
        this.schoolClassService.delete(this.schoolClass.id).subscribe(
            (res) => {
                this.notificationService.add('schoolClass.delete.toast-title',
                    'schoolClass.delete.delete-successful',
                    NotificationTypes.SUCCESS);
                this.router.navigateByUrl('/school-classes');
            }, (err) => console.log(err));
    }

    goBack() {
        this.location.back();
    }
}
