import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild} from '@angular/core';
import {Client, Grade} from '../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '@swimlane/ngx-datatable';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-grade-list',
    templateUrl: './grade.list.component.html',
    styleUrls: [
        'grade.list.component.scss',
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class GradeListComponent implements OnInit, OnDestroy {
    client: Client;
    grades: Grade[] = [];
    tempFilter: Grade[] = [];
    rowsNumber = 10;
    rowsNumbers = [10, 25, 50, 100];
    loadingIndicator = true;
    @ViewChild(DatatableComponent) table: DatatableComponent;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.grades = this.route.snapshot.data['grades'];
        this.tempFilter = [...this.route.snapshot.data['grades']];
        setTimeout(() => {
            this.loadingIndicator = false;
        }, 1000);

    }

    ngOnDestroy() {
    }

    updateFilter(event) {
        const val = event.target.value.toLowerCase();
        const temp = this.tempFilter.filter((grade) => {
            return grade.name.toLowerCase().indexOf(val) !== -1 || !val;
        });
        this.grades = temp;
        this.table.offset = 0;
    }

}
