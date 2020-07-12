import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild, TemplateRef} from '@angular/core';
import {Client, Timeslot} from '../../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {DateTimeHelper} from '../../../shared/services/utils/date-time-helper.service';
import {CalendarHelper} from '../../../shared/services/utils/calendar-helper.service';
import {CalendarView} from 'angular-calendar';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-client-view-timetables',
    styleUrls: [
        'client.view.timetables.component.scss'
    ],
    templateUrl: './client.view.timetables.component.html'
})
export class ClientViewTimetablesComponent implements OnInit, OnDestroy {

    @ViewChild('calendar', {}) modalContent: TemplateRef<any>;
    view: CalendarView = CalendarView.Month;
    viewDate: Date = new Date();
    client: Client;
    options: any;
    headerOptions: any;
    events: any[];
    extraActivities: any[];
    coursesTimeSlots: any[];

    constructor(
        private location: Location,
        private route: ActivatedRoute,
        private calendarHelper: CalendarHelper,
        private dateTimeHelper: DateTimeHelper,
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        // this.coursesTimeSlots = this.calendarHelper.convertTimeSlotsToFullCalendarEvents(
        //     this.client.timeTableOptions.firstWeekDay.name,
        //     this.client.timeTableOptions.coursesTimeSlots, true);
        // this.extraActivities = this.calendarHelper.convertTimeSlotsToFullCalendarEvents(
        //     this.client.timeTableOptions.firstWeekDay.name,
        //     this.client.timeTableOptions.extraActivities, true);
        this.events = [...this.coursesTimeSlots, ...this.extraActivities];
    }

    ngOnDestroy() {
    }
}
