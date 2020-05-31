import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild} from '@angular/core';
import {Client, SchoolClass, Student} from '../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-student-list',
    templateUrl: './student.list.component.html',
    styleUrls: [
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class StudentListComponent implements OnInit, OnDestroy {
    client: Client;
    students: Student[] = [];
    schoolClasses: Array<SchoolClass>;
    tempFilter: Student[] = [];
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
        this.client = this.route.snapshot.data['client'];
        this.students = this.route.snapshot.data['students'];
        this.schoolClasses = this.route.snapshot.data['schoolClasses'];
        this.tempFilter = [...this.route.snapshot.data['students']];
        setTimeout(() => {
            this.loadingIndicator = false;
        }, 1000);

    }

    ngOnDestroy() {
    }

    updateFilter(event) {
        const val = event.target.value.toLowerCase();
        this.students = this.tempFilter.filter((student) => {
            return student.lastName.toLowerCase().indexOf(val) !== -1 || !val
                || student.firstName.toLowerCase().indexOf(val) !== -1 || !val;
        });
        this.table.offset = 0;
    }

    log(item: any) {
        console.log(item);
    }

    schoolClassName(schoolClassId: any) {
        return this.schoolClasses.filter((value) => value.id)[0].name;
    }
}
