import {Address} from './sub/address.model';
import {TimeTableOptions} from './sub/time-table-options.model';
import {AcademicYear} from '../academic-years/academic-years.model';
import {FormGroup} from '@angular/forms';
import {Pricing} from './pricing.model';
import {ClientContractRequest} from './client-contract-request.model';

class Email {
    constructor(
        public name: string,
        public link: string,
    ) {
    }
}

class Phone {
    constructor(
        public number: string,
        public name: string,
    ) {
    }
}

class Contract {

    constructor(
        public currentPeriodStart: Date,
        public currentPeriodEnd: Date,
        public globalPeriodStart: Date,
        public globalPeriodEnd: Date,
        ///////
        public currentPeriodOverdue: number,
        public globalOverdue: number,
        public globalPaid: number,
        public periodPaid: number,
        ///////
        public vatNumber: string,
        ///////
        public pricing: Pricing,
        public academicYear: AcademicYear,
        public signed: boolean,
        public contractRequest: ClientContractRequest) {
    }
}

class Website {

    constructor(
        public link: string,
        public name: string,
    ) {
    }
}

class ContactBy {

    constructor(
        public name: string,
        public code: string,
    ) {
    }
}

export class Client {
    constructor(
        public surfacesMeasurementUnit?: string,
        public distancesMeasurementUnit?: string,
        public majorityAge?: number,
        public id?: string,
        public name?: string,
        public address?: Address,
        public contactsBy?: Array<ContactBy>,
        public principalWebsite?: string,
        public phones?: Array<Phone>,
        public emails?: Array<Email>,
        public websites?: Array<Website>,
        // ///////////////
        public scholarYearStart?: Date,
        public scholarYearEnd?: Date,
        public nbMaxOfStudentsPerSchoolClass?: number,
        public contract?: Contract,
        public timeTableOptions?: TimeTableOptions,
    ) {
    }

    updateInfos(clientForm: FormGroup) {
        this.emails = clientForm.controls.emails.value;
        this.phones = clientForm.controls.phones.value;
        this.websites = clientForm.controls.websites.value;
        this.name = clientForm.controls.name.value;
        this.address.name = clientForm.controls.addressName.value;
        this.address.postalCode = clientForm.controls.addressPostalCode.value;
        this.address.city = clientForm.controls.addressCity.value;
        this.address.country = clientForm.controls.addressCountry.value;
        this.surfacesMeasurementUnit = clientForm.controls.surfacesMeasurementUnit.value;
        this.distancesMeasurementUnit = clientForm.controls.distancesMeasurementUnit.value;
        this.majorityAge = clientForm.controls.majorityAge.value;
        this.contactsBy = clientForm.controls.contactsBy.value;
        this.phones = clientForm.controls.phones.value;
        this.emails = clientForm.controls.emails.value;
        this.websites = clientForm.controls.websites.value;
        this.principalWebsite = clientForm.controls.principalWebsite.value;
    }

    updateContract(clientForm: FormGroup, ) {
        return new ClientContractRequest(
            this.id,
            clientForm.controls.vatNumber.value,
            clientForm.controls.startDateMin.value,
            clientForm.controls.startDateMax.value,
            clientForm.controls.endDate.value,
            clientForm.controls.wantedPricingId.value);
    }
}
