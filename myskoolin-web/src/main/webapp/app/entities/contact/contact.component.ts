import {Component, OnInit, OnDestroy} from '@angular/core';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiAlertService} from 'ng-jhipster';

import {Contact} from '../../shared/model/contact.model';
import {ContactService} from './contact.service';
import {Principal} from '../../shared';

@Component({
    selector: 'jhi-contact',
    templateUrl: './contact.component.html'
})
export class ContactComponent implements OnInit, OnDestroy {
    contacts: Contact[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private contactService: ContactService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

}
