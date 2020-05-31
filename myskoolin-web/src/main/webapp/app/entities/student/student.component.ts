import {Component, OnInit, OnDestroy} from '@angular/core';

@Component({
    template: '<router-outlet></router-outlet>'
})
export class StudentComponent implements OnInit, OnDestroy {

    constructor() {
    }

    ngOnInit() {
    }

    ngOnDestroy() {
        // this.eventManager.destroy(this.eventSubscriber);
    }
}
