import {AfterViewInit, Component, ElementRef, OnInit, OnDestroy} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiAlertService} from 'ng-jhipster';
import {QuotationService} from './quotation.service';
import {ActivatedRoute} from '@angular/router';
import {Interlocutor, SchoolmeSubscription} from '../../shared/model';
import {APP_CONSTANTS} from '../../app.constants';
import {AcademicYear} from '../../shared/academic-years/academic-years.model';
import {TranslateService} from '@ngx-translate/core';

@Component({
    selector: 'jhi-quotation',
    templateUrl: './quotation.component.html'
})
export class QuotationComponent implements AfterViewInit, OnInit, OnDestroy {
    eventSubscriber: Subscription;
    key: string;
    quotationForm: FormGroup;
    sexes: Array<string>;
    quotationConfirmation: boolean;
    errorSubscription: boolean;
    selectedDate: AcademicYear;
    subscription: SchoolmeSubscription;

    constructor(
        private router: Router,
        private quotationService: QuotationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private elementRef: ElementRef,
        private route: ActivatedRoute,
        private translateService: TranslateService
    ) {
        this.sexes = APP_CONSTANTS.SEXES;
        this.quotationConfirmation = false;
        this.errorSubscription = false;
    }

    ngOnInit(): void {
        this.initForm();
    }

    ngAfterViewInit() {
        // this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#username'), 'focus', []);
    }

    ngOnDestroy() {
        // this.eventManager.destroy(this.eventSubscriber);
    }

    sendQuotationRequest(form) {
        if (form.valid) {
            const values = form.value;
            const interlocutor = new Interlocutor(values.lastname, values.firstname, values.gender, values.position, values.phone, values.mobile, values.email);
            this.subscription = new SchoolmeSubscription(this.selectedDate, values.schoolName, values.duns, values.address, values.website, values.phone, values.email
                , this.translateService.currentLang, interlocutor);
            this.quotationService.create(this.subscription).subscribe(
                (res) => {
                    this.quotationConfirmation = true;
                    this.errorSubscription = false;
                    this.quotationForm.reset();
                },
                (err) => {
                    this.quotationConfirmation = false;
                    this.errorSubscription = true;
                });
        }
    }

    resetMessages() {
        this.errorSubscription = false;
        this.quotationConfirmation = false;
    }

    resetSubscription() {
        this.subscription = undefined;
    }

    initForm() {
        this.quotationForm = new FormGroup({
            schoolName: new FormControl('', [Validators.minLength(2), Validators.required]),
            duns: new FormControl('', [Validators.required]),
            address: new FormControl('', [Validators.required]),
            website: new FormControl(),
            schoolPhone: new FormControl(),
            schoolEmail: new FormControl('', [Validators.email, Validators.required]),
            lastname: new FormControl('', [Validators.minLength(2), Validators.required]),
            firstname: new FormControl('', [Validators.required, Validators.minLength(2)]),
            gender: new FormControl('', [Validators.required]),
            position: new FormControl('', [Validators.required]),
            phone: new FormControl(),
            mobile: new FormControl(),
            email: new FormControl('', [Validators.required, Validators.email])
        });
    }
}
