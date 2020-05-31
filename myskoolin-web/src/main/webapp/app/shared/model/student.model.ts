import {BaseEntity} from '../../shared';
import {InfirmaryStatistics} from './sub/infirmary.statistics.model';
import {Responsible} from './responsible.model';
import {CanteenRegistration} from './sub/canteen.registration.model';
import {MedicalInfos} from './sub/medical.infos.model';
import {SchoolingInfos} from './sub/schooling.infos.model';
import {Address} from './sub/address.model';
import {ResidentialSchool} from './sub/residential.school.model';
import {FormGroup} from '@angular/forms';
import {SchoolClassTimetable} from './sub/school-class-timetable.model';
import {DailyBookTimeSlot} from './sub/daily-book-timeslot.model';
import {ContinuousAssessmentItem} from './sub/continuous.assessment.model';
import {Sanction} from './sub/sanction.model';
import {Delay} from './delay.model';
import {Report} from './report.model';
import {SchoolClass} from './schoolclass.model';

export class Student implements BaseEntity {

    constructor(
        public id?: string,
        public login?: string,
        public clientId?: string,
        public firstName?: string,
        public lastName?: string,
        public sex?: string,
        public birthDate?: Date,
        public nationality?: string,
        public homePhone?: string,
        public cellPhone?: string,
        public email?: string,
        public imageUrl?: string,
        public address?: Address,
        public langKey?: string,
        public infirmaryStatistics?: InfirmaryStatistics,
        public responsibles?: Array<Responsible>,
        public responsibleAccountLogin?: string,
        public comment?: string,
        public canteenRegistration?: CanteenRegistration,
        public medicalInfos?: MedicalInfos,
        public schoolingInfos?: SchoolingInfos,
        public schoolClass?: SchoolClass,
        public residentialSchool?: ResidentialSchool,
        public timetable?: SchoolClassTimetable,
        public dailyBook?: Array<DailyBookTimeSlot>,
        public continuousAssessment?: Array<ContinuousAssessmentItem>,
        public sanctions?: Array<Sanction>,
        public delays?: Array<Delay>,
        public reports?: Array<Report>,
    ) {
        this.address = new Address();
        this.residentialSchool = new ResidentialSchool();
        this.residentialSchool.registered = false;
        this.schoolingInfos = new SchoolingInfos();
        this.schoolingInfos.redoubling = false;
        this.schoolingInfos.personalizedSchoolingPlan = false;
        this.schoolingInfos.individualizedReceptionPlan = false;
        this.responsibles = [new Responsible(true)];
        this.medicalInfos = new MedicalInfos();
        this.medicalInfos.healthInsurance = false;
        this.medicalInfos.complementaryHealthInsurance = false;
        this.medicalInfos.allergies = '';
        this.medicalInfos.healthComment = '';
        this.medicalInfos.knownDiseases = '';
        this.canteenRegistration = new CanteenRegistration(false, false, false, false, []);
    }

    update(studentUserForm: FormGroup, studentScholarInfosForm: FormGroup, studentResponsibleAccountForm: FormGroup, studentResponsiblesForm: FormGroup,
           studentMedicalInfosForm: FormGroup, studentCanteenForm: FormGroup, studentResidentialSchoolForm: FormGroup, currentLang: string) {
        if (!this.langKey) {
            this.langKey = currentLang;
        }
        this.login = studentUserForm.controls.login.value;
        this.firstName = studentUserForm.controls.firstName.value;
        this.lastName = studentUserForm.controls.lastName.value;
        this.sex = studentUserForm.controls.sex.value;
        this.nationality = studentUserForm.controls.nationality.value;
        this.email = studentUserForm.controls.email.value;
        this.birthDate = studentUserForm.controls.birthDate.value.toDate();
        this.cellPhone = studentUserForm.controls.cellPhone.value;
        this.homePhone = studentUserForm.controls.homePhone.value;
        this.address.name = studentUserForm.controls.addressName.value;
        this.address.postalCode = studentUserForm.controls.postalCode.value;
        this.address.city = studentUserForm.controls.city.value;
        this.address.country = studentUserForm.controls.country.value;
        this.comment = studentUserForm.controls.comment.value;
        this.schoolingInfos.nationalNumber = studentScholarInfosForm.controls.nationalNumber.value;
        this.schoolingInfos.redoubling = studentScholarInfosForm.controls.redoubling.value;
        this.schoolingInfos.entryDate = studentScholarInfosForm.controls.entryDate.value.toDate();
        this.schoolingInfos.originEstablishment = studentScholarInfosForm.controls.originEstablishment.value;
        this.schoolingInfos.individualizedReceptionPlan = studentScholarInfosForm.controls.individualizedReceptionPlan.value;
        this.schoolingInfos.individualizedReceptionPlanContent = studentScholarInfosForm.controls.individualizedReceptionPlanContent.value;
        this.schoolingInfos.personalizedSchoolingPlan = studentScholarInfosForm.controls.personalizedSchoolingPlan.value;
        this.schoolingInfos.personalizedSchoolingPlanContent = studentScholarInfosForm.controls.personalizedSchoolingPlanContent.value;
        if (studentScholarInfosForm.controls.exitDate.value) {
            this.schoolingInfos.exitDate = studentScholarInfosForm.controls.exitDate.value.toDate();
            this.schoolingInfos.exitReason = studentScholarInfosForm.controls.exitReason.value;
        }

        this.medicalInfos.allergies = studentMedicalInfosForm.controls.allergies.value;
        this.medicalInfos.knownDiseases = studentMedicalInfosForm.controls.knownDiseases.value;
        this.medicalInfos.healthInsurance = studentMedicalInfosForm.controls.healthInsurance.value;
        this.medicalInfos.healthInsuranceName = studentMedicalInfosForm.controls.healthInsuranceName.value;
        this.medicalInfos.healthInsuranceReference = studentMedicalInfosForm.controls.healthInsuranceReference.value;
        this.medicalInfos.healthInsuranceContact = studentMedicalInfosForm.controls.healthInsuranceContact.value;
        this.medicalInfos.complementaryHealthInsurance = studentMedicalInfosForm.controls.complementaryHealthInsurance.value;
        this.medicalInfos.complementaryHealthInsuranceName = studentMedicalInfosForm.controls.complementaryHealthInsuranceName.value;
        this.medicalInfos.complementaryHealthInsuranceReference = studentMedicalInfosForm.controls.complementaryHealthInsuranceReference.value;
        this.medicalInfos.complementaryHealthInsuranceContact = studentMedicalInfosForm.controls.complementaryHealthInsuranceContact.value;
        this.medicalInfos.healthComment = studentMedicalInfosForm.controls.healthComment.value;
        this.responsibleAccountLogin = studentResponsibleAccountForm.controls.respLogin.value;

        studentResponsiblesForm.controls.responsibles.value.forEach((responsibleForm, index) => {
            this.responsibles[index] = new Responsible(responsibleForm.respAccount);
            this.responsibles[index].firstName = responsibleForm.firstName;
            this.responsibles[index].lastName = responsibleForm.lastName;
            this.responsibles[index].sex = responsibleForm.sex;
            this.responsibles[index].birthdate = responsibleForm.birthDate.toDate();
            this.responsibles[index].address.name = responsibleForm.addressName;
            this.responsibles[index].nationality = responsibleForm.nationality;
            this.responsibles[index].address.postalCode = responsibleForm.postalCode;
            this.responsibles[index].address.city = responsibleForm.city;
            this.responsibles[index].address.country = responsibleForm.country;
            this.responsibles[index].phone = responsibleForm.phone;
            this.responsibles[index].cellPhone = responsibleForm.cellPhone;
            this.responsibles[index].workPhone = responsibleForm.workPhone;
            this.responsibles[index].email = responsibleForm.email;
            this.responsibles[index].secondaryEmail = responsibleForm.secondaryEmail;
            this.responsibles[index].familySituation = responsibleForm.familySituation;
            this.responsibles[index].studentRelationship = responsibleForm.studentRelationship;
            this.responsibles[index].dependentChildren = responsibleForm.dependentChildren;
            this.responsibles[index].profession = responsibleForm.profession;
            this.responsibles[index].proEmail = responsibleForm.proEmail;
            this.responsibles[index].workCellPhone = responsibleForm.workCellPhone;
            this.responsibles[index].parentsAssociation = responsibleForm.parentsAssociation;
            this.responsibles[index].parentsAssociationPosition = responsibleForm.parentsAssociationPosition;
            this.responsibles[index].comment = responsibleForm.comment;
        });
        this.canteenRegistration.recorded = studentCanteenForm.controls.recorded.value;
        this.canteenRegistration.breakfast = studentCanteenForm.controls.breakfast.value;
        this.canteenRegistration.lunch = studentCanteenForm.controls.lunch.value;
        this.canteenRegistration.dinner = studentCanteenForm.controls.dinner.value;
        this.canteenRegistration.weekDays = studentCanteenForm.controls.weekDays.value;
        this.canteenRegistration.subscriptionPeriodStart = studentCanteenForm.controls.subscriptionPeriodStart.value.toDate();
        this.canteenRegistration.subscriptionPeriodEnd = studentCanteenForm.controls.subscriptionPeriodEnd.value.toDate();
        this.residentialSchool.registered = studentResidentialSchoolForm.controls.registered.value;
        this.residentialSchool.name = studentResidentialSchoolForm.controls.name.value;

        this.residentialSchool.periodStart = studentResidentialSchoolForm.controls.periodStart.value.toDate();
        this.residentialSchool.periodEnd = studentResidentialSchoolForm.controls.periodEnd.value.toDate();
    }
}
