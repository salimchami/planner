import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client} from '../../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Student} from '../../../shared/model';
import {StudentService} from '../../../shared/services/student.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-client-view-basic-infos',
    styleUrls: [
        'client.view.basic-infos.component.scss'
    ],
    templateUrl: './client.view.basic-infos.component.html'
})
export class ClientViewBasicInfosComponent implements OnInit, OnDestroy {
    client: Client;

    constructor(
        private location: Location,
        private route: ActivatedRoute,
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
    }

    ngOnDestroy() {

    }
}
