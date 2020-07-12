import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild, TemplateRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {CalendarView} from 'angular-calendar';
import {Client, SchoolClass, Timeslot} from '../../../shared/model';
import {DateTimeHelper} from '../../../shared/services/utils/date-time-helper.service';
import {CalendarHelper} from '../../../shared/services/utils/calendar-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-school-class-view-timetables',
    templateUrl: './school-class.timetable.view.component.html'
})
export class SchoolClassTimetableViewComponent implements OnInit, OnDestroy {
    @ViewChild('calendar', {}) modalContent: TemplateRef<any>;
    view: CalendarView = CalendarView.Month;
    viewDate: Date = new Date();
    studentsRowsNumber: number;
    schoolClassesStaticTimeSlots: Array<any>;
    client: Client;
    schoolClass: SchoolClass;

    constructor(
        private location: Location,
        private route: ActivatedRoute,
        private router: Router,
        private dateTimeHelper: DateTimeHelper,
        private calendarHelper: CalendarHelper,
    ) {
    }

    ngOnInit() {
        this.studentsRowsNumber = 20;
        this.schoolClassesStaticTimeSlots = [];
        this.client = this.route.snapshot.data['client'];
        this.schoolClass = this.route.snapshot.data['schoolClass'];
        const timeSlots: Array<Timeslot> = this.schoolClass.timetable.staticTimeTable.map((lesson) => lesson.timeSlot);
        if (this.schoolClass.timetable != null && typeof this.schoolClass.timetable !== 'undefined') {
            this.schoolClassesStaticTimeSlots = this.calendarHelper.convertTimeSlotsToFullCalendarEvents(
                this.client.timeTableOptions.firstWeekDay.name,
                timeSlots, false);
        }
    }

    ngOnDestroy() {
    }
}
