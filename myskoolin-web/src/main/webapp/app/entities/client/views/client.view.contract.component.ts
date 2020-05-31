import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client} from '../../../shared/model';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {DateTimeHelper} from '../../../shared/services/utils/date-time-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-client-view-contract',
    styleUrls: [
        'client.view.contract.component.scss'
    ],
    templateUrl: './client.view.contract.component.html'
})
export class ClientViewContractComponent implements OnInit, OnDestroy {
    client: Client;
    requestPending: boolean;

    constructor(
        private location: Location,
        private route: ActivatedRoute,
        private dateHelper: DateTimeHelper,
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
        this.requestPending = typeof this.client.contract.contractRequest !== 'undefined' && this.client.contract.contractRequest !== null;
    }

    ngOnDestroy() {
    }
}
