import {Component, OnDestroy, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {Client, Subject} from '../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-subject-list',
    templateUrl: './subject.list.component.html',
    styleUrls: [
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class SubjectListComponent implements OnInit, OnDestroy {
    client: Client;
    subjects: Subject[] = [];
    tempFilter: Subject[] = [];
    rowsNumber = 10;
    rowsNumbers = [10, 25, 50, 100];
    loadingIndicator = true;
    @ViewChild(DatatableComponent) table: DatatableComponent;

    constructor(private route: ActivatedRoute,
                private dateTimeHelper: DateTimeHelper) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.subjects = this.route.snapshot.data['subjects'];
        this.tempFilter = [...this.route.snapshot.data['subjects']];
        setTimeout(() => {
            this.loadingIndicator = false;
        }, 1000);

    }

    ngOnDestroy() {
    }

    updateFilter(event) {
        const val = event.target.value.toLowerCase();
        this.subjects = this.tempFilter.filter((subject) => {
            return subject.name.toLowerCase().indexOf(val) !== -1 || !val;
        });
        this.table.offset = 0;
    }
}
