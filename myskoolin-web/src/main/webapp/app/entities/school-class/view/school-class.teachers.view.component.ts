import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild, TemplateRef, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {CalendarView} from 'angular-calendar';
import {Client, SchoolClass} from '../../../shared/model';
import {DateTimeHelper} from '../../../shared/services/utils/date-time-helper.service';
import {CalendarHelper} from '../../../shared/services/utils/calendar-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-school-class-view-teachers',
    templateUrl: './school-class.teachers.view.component.html'
})
export class SchoolClassTeachersViewComponent implements OnInit {

    @Input()
    schoolClass: SchoolClass;
    loadingIndicator = true;
    rowsNumber = 10;

    ngOnInit(): void {
        setTimeout(() => {
            this.loadingIndicator = false;
        }, 1000);
    }
}
