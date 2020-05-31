import {Component, OnInit, OnDestroy, ViewChild, ViewEncapsulation} from '@angular/core';
import {Client, SchoolRoom} from '../../shared/model';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {ActivatedRoute} from '@angular/router';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-school-room',
    templateUrl: './school-room.list.component.html',
    styleUrls: [
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class SchoolRoomListComponent implements OnInit, OnDestroy {
    loadingIndicator = true;
    client: Client;
    reorderable = true;
    schoolRooms: SchoolRoom[] = [];
    tempFilter: SchoolRoom[] = [];
    expanded = {};
    timeout: any;
    rowsNumber = 10;
    rowsNumbers = [10, 25, 50, 100];
    @ViewChild(DatatableComponent) table: DatatableComponent;

    constructor(
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.schoolRooms = this.route.snapshot.data['schoolRooms'];
        this.tempFilter = [...this.route.snapshot.data['schoolRooms']];
        setTimeout(() => {
            this.loadingIndicator = false;
        }, 1000);

    }

    ngOnDestroy() {
        // this.eventManager.destroy(this.eventSubscriber);
    }

    updateFilter(event) {
        const val = event.target.value.toLowerCase();
        const temp = this.tempFilter.filter((schoolRoom) => {
            return schoolRoom.name.toLowerCase().indexOf(val) !== -1 || !val;
        });
        this.schoolRooms = temp;
        this.table.offset = 0;
    }
}
