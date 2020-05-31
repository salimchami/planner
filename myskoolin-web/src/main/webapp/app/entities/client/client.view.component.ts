import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NotificationService} from '../../shared';
import {Location} from '@angular/common';
import {Client} from '../../shared/model';
import {ClientService} from '../../shared/services/client.service';
import {animate, style, transition, trigger} from '@angular/animations';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-client',
    styleUrls: [
        'client.view.component.scss',
        '../../../content/icons/template/icofont/css/icofont.scss'
    ],
    templateUrl: './client.view.component.html',
    animations: [
        trigger('fadeInOutTranslate', [
            transition(':enter', [
                style({opacity: 0}),
                animate('400ms ease-in-out', style({opacity: 1}))
            ]),
            transition(':leave', [
                style({transform: 'translate(0)'}),
                animate('400ms ease-in-out', style({opacity: 0}))
            ])
        ])
    ]

})
export class ClientViewComponent implements OnInit, OnDestroy {
    client: Client;

    constructor(
        private location: Location,
        private clientService: ClientService,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
        private dateHelper: DateTimeHelper
    ) {
    }

    ngOnInit() {
        this.client = this.route.snapshot.data['client'];
    }

    ngOnDestroy() {
    }

    goBack() {
        this.location.back();
    }
}
