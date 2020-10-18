import {AfterViewInit, Component, ElementRef, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {StateStorageService, LoginService} from '../../shared';
import {JhiEventManager} from 'ng-jhipster';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {APP_CONSTANTS} from '../../app.constants';
import {ContactService} from '../../entities/contact';
import {NewsletterService} from '../../entities/newsletter';
import {Contact, Newsletter} from '../../shared/model';
import {TranslateService} from '@ngx-translate/core';
import {CookieConsentService} from '../../shared/services/config/cookie.consent.service';
import {CookieService} from 'ngx-cookie';
import {NgcCookieConsentService} from 'ngx-cookieconsent';
import {CookieConsent} from '../../shared/model/cookie.consent.model';
import {Pricing} from '../../shared/model/pricing.model';
import {PricingService} from '../../shared/services/pricing.service';

@Component({
    selector: 'jhi-landingpage',
    templateUrl: './landingpage.component.html',
    styleUrls: ['./landingpage.component.scss']
})
export class JhiLandingpageComponent implements AfterViewInit, OnInit, OnDestroy {
    authenticationError: boolean;
    newsletterConfirmation: boolean;
    contactConfirmation: boolean;
    newsletterError: boolean;
    password: string;
    rememberMe: boolean;
    username: string;
    credentials: any;
    contactForm: FormGroup;
    newsletterForm: FormGroup;
    contactReasons: Array<string>;
    sexes: Array<string>;
    contact: Contact;
    newsletter: Newsletter;
    subscriptions: CookieConsent;
    pricings: Array<Pricing>;

    constructor(
        private cookieService: CookieService,
        private ccService: NgcCookieConsentService,
        private eventManager: JhiEventManager,
        private loginService: LoginService,
        private stateStorageService: StateStorageService,
        private elementRef: ElementRef,
        private renderer: Renderer2,
        private router: Router,
        private translateService: TranslateService,
        private contactService: ContactService,
        private newsletterService: NewsletterService,
        private cookieConsentService: CookieConsentService,
        private pricingService: PricingService,
    ) {
        this.credentials = {};
        this.contactReasons = APP_CONSTANTS.CONTACT_TYPES;
        this.sexes = APP_CONSTANTS.SEXES;
        this.newsletterConfirmation = false;
        this.contactConfirmation = false;
    }

    ngOnInit(): void {
        this.pricings = [];
        this.pricingService.query().subscribe((response) => {
            this.pricings = Object.assign([], response.body);
        });
        this.contactForm = new FormGroup({
            name: new FormControl('', [Validators.minLength(2), Validators.required]),
            contactReason: new FormControl('', [Validators.required]),
            email: new FormControl('', [Validators.email, Validators.required]),
            schoolName: new FormControl('', [Validators.minLength(2), Validators.required]),
            message: new FormControl('', [Validators.required]),
            gender: new FormControl('', Validators.required),
            phone: new FormControl()
        });

        this.newsletterForm = new FormGroup({
            email: new FormControl('', [Validators.email, Validators.required])
        });
        this.subscriptions = new CookieConsent();
        this.cookieConsentService.initCookieConsent(this.ccService, this.cookieService, this.subscriptions);
        this.cookieConsentService.translate(this.translateService, this.ccService);
    }

    ngOnDestroy(): void {
        this.cookieConsentService.unsubscribes(this.subscriptions);
    }

    ngAfterViewInit() {
        this.elementRef.nativeElement.querySelector('#username').focus();
    }

    login() {
        this.authenticationError = false;
        this.loginService.login({
            username: this.username,
            password: this.password,
            rememberMe: this.rememberMe
        }).then(() => {
            this.authenticationError = false;

            if (this.router.url === '/register' || (/^\/activate\//.test(this.router.url)) ||
                (/^\/reset\//.test(this.router.url))) {
                this.router.navigate(['']);
            }

            this.eventManager.broadcast({
                name: 'authenticationSuccess',
                content: 'Sending Authentication Success'
            });

            // // previousState was set in the authExpiredInterceptor before being redirected to login modal.
            // // since login is succesful, go to stored previousState and clear previousState
            const redirect = this.stateStorageService.getUrl();
            if (redirect) {
                this.stateStorageService.storeUrl(null);
                this.router.navigate([redirect]);
            }
        }).catch(() => {
            this.authenticationError = true;
        });
    }

    requestResetPassword() {
        this.router.navigate(['/reset', 'request']);
    }

    cancel() {
        this.credentials = {
            username: null,
            password: null,
            rememberMe: true
        };
        this.authenticationError = false;
    }

    sendContact(form) {
        const values = form.value;

        this.contact = new Contact(values.name, values.email, values.message, values.contactReason,
            this.translateService.currentLang, values.phone, values.gender);
        this.contactService.create(this.contact).subscribe(
            (res) => {
                this.contactConfirmation = true;
                this.contactForm.reset();
            });

    }

    sendNewsletter(newsletterForm) {
        this.newsletter = new Newsletter(newsletterForm.value.email, this.translateService.currentLang);
        this.newsletterService.create(this.newsletter).subscribe(
            (res) => {
                this.newsletterConfirmation = !res.body;
                // this.newsletterError = res.body;
                this.newsletterForm.reset();
            }, (err) => {
                this.newsletterConfirmation = false;
                this.newsletterError = err;
            });
    }

    resetMessages() {
        this.newsletterConfirmation = false;
        this.authenticationError = false;
        this.newsletterError = undefined;
        this.contactConfirmation = false;
    }
}
