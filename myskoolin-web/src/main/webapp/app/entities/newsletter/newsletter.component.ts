import {Component, OnInit, OnDestroy} from '@angular/core';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiAlertService} from 'ng-jhipster';
import {NewsletterService} from './newsletter.service';
import {ActivatedRoute} from '@angular/router';

@Component({
    selector: 'jhi-newsletter',
    templateUrl: './newsletter.component.html'
})
export class NewsletterComponent implements OnInit, OnDestroy {
    eventSubscriber: Subscription;
    key: string;

    constructor(
        private newsletterService: NewsletterService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.route.queryParams.subscribe((params) => {
            this.key = params['key'];
            this.newsletterService.confirm(this.key);
        });
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

}
