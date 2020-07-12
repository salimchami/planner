import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {Client, SchoolClass, SchoolClassTimetable} from '../../shared/model';
import {TimetableService} from '../../shared/services/timetable.service';
import {NotificationService} from '../../shared';
import {TimetableSchoolClassesComponent} from '../timetable/schoolclasses/timetable-schoolclasses.component';

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
    generatedTimetablesLength: number;
    @ViewChild('timetableSchoolClassesComponent') child: TimetableSchoolClassesComponent;

    constructor(
        private route: ActivatedRoute,
        private dateHelper: DateTimeHelper,
        private timetableService: TimetableService,
        private router: Router,
        private notificationService: NotificationService,
    ) {
    }

    ngOnInit() {
        this.timetables = this.route.snapshot.data['timetables'];
        this.schoolClasses = this.route.snapshot.data['schoolClasses'];
        this.client = this.route.snapshot.data['client'];
        this.generatedTimetablesLength = this.timetables.length;
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
            'success');
    }

    private saveError() {
        this.notificationService.add('client.new.form.toast-generation-error-title',
            'client.new.form.toast-generation-error',
            'error');
    }

    calculateGeneratedTimetablesPourcent() {
        return (this.generatedTimetablesLength / this.schoolClasses.length) * 100;
    }

    maxTimeTablesGenerationDate() {
        const sortedSchoolClassTimetables = this.timetables
            .filter((timetable) => typeof timetable.lastGenerationDate !== 'undefined')
            .sort((a, b) => (b.lastGenerationDate > a.lastGenerationDate) ? 1 : -1);
        return sortedSchoolClassTimetables[0].lastGenerationDate;
    }

    lastGenerationDateExists() {
        return !!this.timetables.map((timetable) => timetable.staticTimeTable).length;
    }
}
