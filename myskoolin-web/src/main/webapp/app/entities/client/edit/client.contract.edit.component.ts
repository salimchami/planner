import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client} from '../../../shared/model';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {NotificationService, Principal} from '../../../shared';
import {ClientService} from '../../../shared/services/client.service';
import {Pricing} from 'app/shared/model/pricing.model';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-client-edit',
    templateUrl: './client.contract.edit.component.html',
    styleUrls: [
        './client.contract.edit.component.scss',
        '../../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class ClientContractEditComponent implements OnInit, OnDestroy {
    clientForm: FormGroup;
    routeState: string;
    clientCreateConfirmation: boolean;
    creationErrorMsg: string;
    creationError: boolean;
    client: Client;
    pricings: Array<Pricing>;

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
        this.pricings = Object.assign([], this.route.snapshot.data['pricings']);
        this.clientForm = new FormGroup({
            vatNumber: new FormControl(this.client.contract.vatNumber),
            startDateMin: new FormControl(this.client.contract.academicYear.startDateMin, Validators.required),
            startDateMax: new FormControl(this.client.contract.academicYear.startDateMax, Validators.required),
            endDate: new FormControl(this.client.contract.academicYear.endDate, Validators.required),
            wantedPricingId: new FormControl(''),
        });
    }

    resetMessages() {
        this.clientCreateConfirmation = false;
        this.creationError = false;
        this.creationErrorMsg = '';
    }

    updateClientContract() {
        let request;
        if (this.clientForm.valid) {
            request = this.client.updateContract(this.clientForm);
        }
        this.clientService.updateContract(request).subscribe(
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
        this.notificationService.addToast('client.new.form.toast-save-title',
            'client.new.form.toast-save',
            'success');
    }

    private saveError() {
        this.notificationService.addToast('client.new.form.toast-save-error-title',
            'client.new.form.toast-save-error',
            'error');
    }

    goBack() {
        this.location.back();
    }

    changePricing(id: string) {
        this.clientForm.patchValue({wantedPricingId: id});
    }

    isOtherPricingClicked(id: string) {
        const wantedPricingId: Pricing = this.clientForm.get('wantedPricingId').value;
        return wantedPricingId === id && typeof wantedPricingId !== 'undefined' && wantedPricingId !== null;
    }

    cancelWantedPricing() {
        this.clientForm.patchValue({wantedPricingId: null});
    }
}
