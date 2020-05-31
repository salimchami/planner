import {Component, OnInit, OnDestroy} from '@angular/core';
import {Client, Teacher} from '../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {Absence} from '../../shared/model/sub/absence.model';

@Component({
    template: '<router-outlet></router-outlet>'
})
export class TimetableComponent implements OnInit, OnDestroy {

    constructor() {
    }

    ngOnInit() {
    }

    ngOnDestroy() {
    }
}
