import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild} from '@angular/core';
import {Client, Student} from '../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {SchoolClass} from '../../shared/model/schoolclass.model';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-student-list',
    templateUrl: './school-class.list.component.html',
    styleUrls: [
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class SchoolClassListComponent implements OnInit, OnDestroy {
    schoolClasses: SchoolClass[] = [];
    tempFilter: SchoolClass[] = [];
    rowsNumber = 10;
    rowsNumbers = [10, 25, 50, 100];
    loadingIndicator = true;
    @ViewChild(DatatableComponent) table: DatatableComponent;

    constructor(
        private route: ActivatedRoute,
    ) {
    }

    ngOnInit() {
        this.schoolClasses = this.route.snapshot.data['schoolClasses'];
        setTimeout(() => {
            this.loadingIndicator = false;
        }, 1000);
    }

    ngOnDestroy() {
    }

    updateFilter(event) {
        const val = event.target.value.toLowerCase();
        this.schoolClasses = this.tempFilter.filter((schoolClass) => {
            return schoolClass.name.toLowerCase().indexOf(val) !== -1 || !val
                || schoolClass.customName.toLowerCase().indexOf(val) !== -1 || !val;
        });
        this.table.offset = 0;
    }

}
