import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild} from '@angular/core';
import {Teacher} from '../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {Absence} from '../../shared/model/sub/absence.model';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-teacher-list',
    templateUrl: './teacher.list.component.html',
    styleUrls: [
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class TeacherListComponent implements OnInit, OnDestroy {
    teachers: Teacher[] = [];
    tempFilter: Teacher[] = [];
    rowsNumber = 10;
    rowsNumbers = [10, 25, 50, 100];
    loadingIndicator = true;
    @ViewChild(DatatableComponent) table: DatatableComponent;

    constructor(
        private route: ActivatedRoute,
        private dateHelper: DateTimeHelper,
    ) {
    }

    ngOnInit() {
        this.teachers = this.route.snapshot.data['teachers'];
        this.tempFilter = [...this.route.snapshot.data['teachers']];
        setTimeout(() => {
            this.loadingIndicator = false;
        }, 1000);

    }

    ngOnDestroy() {
    }

    updateFilter(event) {
        const val = event.target.value.toLowerCase();
        this.teachers = this.tempFilter.filter((teacher) => {
            return teacher.lastName.toLowerCase().indexOf(val) !== -1 || !val
                || teacher.firstName.toLowerCase().indexOf(val) !== -1 || !val;
        });
        this.table.offset = 0;
    }

    isAbsentNow(absences: Array<Absence>) {
        return false;
    }

}
