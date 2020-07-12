import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {Client} from '../../../shared/model';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {NotificationService, Principal} from '../../../shared';
import {ClientService} from '../../../shared/services/client.service';
import {APP_CONSTANTS} from '../../../app.constants';
import {NotificationTypes} from '../../../shared/notification/notification-types';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-client-edit',
    templateUrl: './client.infos.edit.component.html',
    styleUrls: [
        './client.infos.edit.component.scss',
        '../../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class ClientInfosEditComponent implements OnInit, OnDestroy {
    clientForm: FormGroup;
    routeState: string;
    clientCreateConfirmation: boolean;
    creationErrorMsg: string;
    creationError: boolean;
    client: Client;
    surfaces: Array<any>;
    distances: Array<any>;
    contactsBy: Array<any>;
    phones: FormArray;
    emails: FormArray;
    websites: FormArray;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private notificationService: NotificationService,
        private clientService: ClientService,
        private principalService: Principal,
        private fb: FormBuilder,
        private location: Location,
    ) {
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.client = Object.assign(new Client(), this.route.snapshot.data['client']);
        this.clientForm = new FormGroup({
            name: new FormControl(this.client.name, Validators.required),
            addressName: new FormControl(this.client.address.name, Validators.required),
            addressPostalCode: new FormControl(this.client.address.postalCode, Validators.required),
            addressCity: new FormControl(this.client.address.city, Validators.required),
            addressCountry: new FormControl(this.client.address.country, Validators.required),
            surfacesMeasurementUnit: new FormControl(this.client.surfacesMeasurementUnit),
            distancesMeasurementUnit: new FormControl(this.client.distancesMeasurementUnit),
            majorityAge: new FormControl(this.client.majorityAge),
            contactsBy: new FormControl(this.client.contactsBy),
            phones: this.fb.array(this.createPhonesItemsFromClientPhones()),
            emails: this.fb.array(this.createEmailsItemsFromClientEmails()),
            websites: this.fb.array(this.createWebsitesItemsFromClientWebsites()),
            principalWebsite: new FormControl(this.client.principalWebsite, []),
        });
        this.distances = Object.assign([], APP_CONSTANTS.DISTANCES);
        this.surfaces = Object.assign([], APP_CONSTANTS.SURFACES);
        this.contactsBy = Object.assign([], APP_CONSTANTS.CONTACTS_BY);
    }

    resetMessages() {
        this.clientCreateConfirmation = false;
        this.creationError = false;
        this.creationErrorMsg = '';
    }

    updateClientInfos() {
        if (this.clientForm.valid) {
            this.client.updateInfos(this.clientForm);
        }
        this.clientService.update(this.client).subscribe(
            () => {
                this.router.navigate(['/school-account']).then((r) => {
                    this.saveConfirmation();
                });
            },
            () => {
                this.saveError();
            }
        );
    }

    private saveConfirmation() {
        this.notificationService.add('client.new.form.toast-save-title',
            'client.new.form.toast-save',
            NotificationTypes.SUCCESS);
    }

    private saveError() {
        this.notificationService.add('client.new.form.toast-save-error-title',
            'client.new.form.toast-save-error',
            NotificationTypes.ERROR);
    }

    goBack() {
        this.location.back();
    }

    compareSurfacesMeasurementUnitFn(surface1: any, surface2: any) {
        return surface1 === surface2;
    }

    compareDistancesMeasurementUnitFn(distance1: any, distance2: any) {
        return distance1 === distance2;
    }

    compareContactsByMeasurementUnitFn(contactBy1: any, contactBy2: any) {
        return contactBy1.code === contactBy2.code;
    }

    addPhone() {
        this.phones = this.clientForm.get('phones') as FormArray;
        this.phones.push(this.fb.group({
            name: '',
            number: ''
        }));
    }

    removePhone(index: number) {
        this.phones.removeAt(index);
    }

    addWebsite() {
        this.websites = this.clientForm.get('websites') as FormArray;
        this.websites.push(this.fb.group({
            name: '',
            link: ''
        }));
    }

    removeWebsite(index: number) {
        this.websites.removeAt(index);
    }

    addEmail() {
        this.emails = this.clientForm.get('emails') as FormArray;
        this.emails.push(this.fb.group({
            name: '',
            link: ''
        }));
    }

    removeEmail(index: number) {
        this.emails.removeAt(index);
    }

    private createPhonesItemsFromClientPhones(): Array<FormGroup> {
        const phonesArrayForm = [];
        if (this.client.phones.length > 0) {
            this.client.phones.forEach((phone) => phonesArrayForm.push(
                this.fb.group(phone)
            ));
        } else {
            phonesArrayForm.push(
                this.fb.group({
                    name: '',
                    number: ''
                })
            );
        }
        return phonesArrayForm;
    }

    private createEmailsItemsFromClientEmails(): Array<FormGroup> {
        const emailsArrayForm = [];
        if (this.client.emails.length > 0) {
            this.client.emails.forEach((email) => emailsArrayForm.push(
                this.fb.group(email)
            ));
        } else {
            emailsArrayForm.push(
                this.fb.group({
                    name: '',
                    link: ''
                })
            );
        }
        return emailsArrayForm;
    }

    private createWebsitesItemsFromClientWebsites() {
        const websitesArrayForm = [];
        if (this.client.websites.length > 0) {
            this.client.websites.forEach((website) => websitesArrayForm.push(
                this.fb.group(website)
            ));
        } else {
            websitesArrayForm.push(
                this.fb.group({
                    name: '',
                    link: ''
                })
            );
        }
        return websitesArrayForm;
    }
}
