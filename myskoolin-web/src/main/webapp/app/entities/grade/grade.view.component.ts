import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild, TemplateRef} from '@angular/core';
import {Client} from '../../shared/model';
import {ActivatedRoute, Router} from '@angular/router';
import {NotificationService} from '../../shared';
import {Location} from '@angular/common';
import {Grade} from '../../shared/model';
import {GradeService} from '../../shared/services/grade.service';
import {animate, style, transition, trigger} from '@angular/animations';
import {CalendarView} from 'angular-calendar';
import {CalendarHelper} from '../../shared/services/utils/calendar-helper.service';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-grade',
    styleUrls: [
        'grade.view.component.scss'
    ],
    templateUrl: './grade.view.component.html',
    animations: [
        trigger('fadeInOutTranslate', [
            transition(':enter', [
                style({opacity: 0}),
                animate('400ms ease-in-out', style({opacity: 1}))
            ]),
            transition(':leave', [
                style({transform: 'translate(0)'}),
                animate('400ms ease-in-out', style({opacity: 0}))
            ])
        ])
    ]

})
export class GradeViewComponent implements OnInit, OnDestroy {
    @ViewChild('calendar', {}) modalContent: TemplateRef<any>;
    view: CalendarView = CalendarView.Month;
    viewDate: Date = new Date();
    grades: Array<Grade>;
    grade: Grade;
    client: Client;
    events: any[];
    extraActivities: any[];
    coursesTimeSlots: any[];

    constructor(
        private location: Location,
        private gradeService: GradeService,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
        private calendarHelper: CalendarHelper,
        private dateTimeHelper: DateTimeHelper
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.grade = this.route.snapshot.data['grade'];
        this.grades = this.route.snapshot.data['grades'];
        this.coursesTimeSlots = this.calendarHelper.convertTimeSlotsToFullCalendarEvents(
            this.grade.timeTableOptions.firstWeekDay.name,
            this.grade.timeTableOptions.coursesTimeSlots, true);
        this.extraActivities = this.calendarHelper.convertTimeSlotsToFullCalendarEvents(
            this.grade.timeTableOptions.firstWeekDay.name,
            this.grade.timeTableOptions.extraActivities, true);
        this.events = [...this.coursesTimeSlots, ...this.extraActivities];
    }

    ngOnDestroy() {
    }

    deleteSchoolRoom() {
        this.gradeService.delete(this.grade.id).subscribe(
            (res) => {
                this.notificationService.addToast('grade.delete.toast-title',
                    'grade.delete.delete-successfull',
                    'success');
                this.router.navigateByUrl('/grades');
            }, (err) => console.log(err));
    }

    goBack() {
        this.location.back();
    }

    getGradeCellBackground(gradeForRanking: Grade) {
        if (gradeForRanking.name === this.grade.name) {
            return 'background-cell-color-red';
        } else {
            return 'background-cell-color-blue';
        }
    }
}
