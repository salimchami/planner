import {Component, OnDestroy, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {Client, SchoolClass, SchoolClassTimetable} from '../../shared/model';
import {TimetableService} from '../../shared/services/timetable.service';
import {NotificationService} from '../../shared';
import {TimetableSchoolClassesComponent} from '../timetable/schoolclasses/timetable-schoolclasses.component';
import {NotificationTypes} from '../../shared/notification/notification-types';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-teacher-list',
    templateUrl: './timetable.list.component.html',
    styleUrls: [
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class TimetableListComponent implements OnInit, OnDestroy {
    schoolClasses: Array<SchoolClass>;
    timetables: Array<SchoolClassTimetable>;
    generatedTimetablesPourcent: number;
    client: Client;
    generatedTimetablesCount: number;
    lastGenerationDate: Date;
    @ViewChild('timetableSchoolClassesComponent') child: TimetableSchoolClassesComponent;

    constructor(
        private route: ActivatedRoute,
        public dateHelper: DateTimeHelper,
        private timetableService: TimetableService,
        private router: Router,
        private notificationService: NotificationService,
    ) {
    }

    ngOnInit() {
        this.timetables = this.route.snapshot.data['timetables'];
        this.schoolClasses = this.route.snapshot.data['schoolClasses'];
        this.client = this.route.snapshot.data['client'];
        this.generatedTimetablesCount = this.route.snapshot.data['timetablesCount'];
        this.lastGenerationDate = this.route.snapshot.data['lastGenerationDate'];
        this.generatedTimetablesPourcent = this.calculateGeneratedTimetablesPourcent();
    }

    ngOnDestroy() {
    }

    generate() {
        this.timetableService.generateTimetables().subscribe((response) => {
            this.generateConfirmation();
            // add polling
        });
    }

    private generateConfirmation() {
        this.notificationService.add('client.new.form.toast-generation-title',
            'client.new.form.toast-generation',
            NotificationTypes.SUCCESS);
    }

    private saveError() {
        this.notificationService.add('client.new.form.toast-generation-error-title',
            'client.new.form.toast-generation-error',
            NotificationTypes.ERROR);
    }

    calculateGeneratedTimetablesPourcent() {
        return (this.generatedTimetablesCount / this.schoolClasses.length) * 100;
    }
}
