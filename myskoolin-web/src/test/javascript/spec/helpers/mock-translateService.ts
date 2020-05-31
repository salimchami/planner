import { SpyObject } from './spyobject';
import {TranslateService} from '@ngx-translate/core';
import Spy = jasmine.Spy;

export class MockTranslateService extends SpyObject {

    getSpy: Spy;
    fakeResponse: any;

    constructor() {
        super(TranslateService);

        this.fakeResponse = '';

        this.getSpy = this.spy('get').andReturn(Promise.resolve(this.fakeResponse));
    }

    init() {}

    reload() {}
}
