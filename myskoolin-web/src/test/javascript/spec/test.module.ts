import {DatePipe} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {NgModule, ElementRef, Renderer2} from '@angular/core';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {
    JhiLanguageService,
    JhiDataUtils,
    JhiDateUtils,
    JhiEventManager,
    JhiAlertService,
    JhiParseLinks
} from 'ng-jhipster';
import {TranslateService} from '@ngx-translate/core';
import {MockLanguageService, MockLanguageHelper} from './helpers/mock-language.service';
import {MockTranslateService} from './helpers/mock-translateService';
import {
    JhiLanguageHelper,
    Principal,
    AccountService,
    LoginModalService,
    JhiTrackerService
} from '../../../main/webapp/app/shared';
import {MockPrincipal} from './helpers/mock-principal.service';
import {MockAccountService} from './helpers/mock-account.service';
import {MockActivatedRoute, MockRouter} from './helpers/mock-route.service';
import {MockActiveModal} from './helpers/mock-active-modal.service';
import {MockEventManager} from './helpers/mock-event-manager.service';

@NgModule({
    providers: [
        DatePipe,
        JhiDataUtils,
        JhiDateUtils,
        JhiParseLinks,
        {
            provide: TranslateService,
            useClass: MockTranslateService
        },
        {
            provide: JhiLanguageService,
            useClass: MockLanguageService
        },
        {
            provide: JhiLanguageHelper,
            useClass: MockLanguageHelper
        },
        {
            provide: JhiTrackerService,
            useValue: null
        },
        {
            provide: JhiEventManager,
            useClass: MockEventManager
        },
        {
            provide: NgbActiveModal,
            useClass: MockActiveModal
        },
        {
            provide: ActivatedRoute,
            useValue: new MockActivatedRoute({id: '123'})
        },
        {
            provide: Router,
            useClass: MockRouter
        },
        {
            provide: Principal,
            useClass: MockPrincipal
        },
        {
            provide: AccountService,
            useClass: MockAccountService
        },
        {
            provide: LoginModalService,
            useValue: null
        },
        {
            provide: ElementRef,
            useValue: null
        },
        {
            provide: Renderer2,
            useValue: null
        },
        {
            provide: JhiAlertService,
            useValue: null
        },
        {
            provide: NgbModal,
            useValue: null
        },
    ],
    imports: [HttpClientTestingModule]
})
export class SchoolmeTestModule {
}
