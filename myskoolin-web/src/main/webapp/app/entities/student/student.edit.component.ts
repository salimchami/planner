import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {Client, Student} from '../../shared/model';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {NotificationService, Principal} from '../../shared';
import {StudentService} from '../../shared/services/student.service';
import {APP_CONSTANTS} from '../../app.constants';
import {Responsible} from '../../shared/model/responsible.model';
import {JhiLanguageService} from 'ng-jhipster';
import {WeekDay} from '../../shared/model/sub/week-day.model';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material';
import {MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {NotificationTypes} from '../../shared/notification/notification-types';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-student-edit',
    templateUrl: './student.edit.component.html',
    styleUrls: [
        './student.edit.component.scss',
        '../../../content/icons/template/icofont/css/icofont.scss'
    ],
    providers: [
        {provide: MAT_DATE_LOCALE, useValue: 'ja-JP'},
        {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
        {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
    ],
})
export class StudentEditComponent implements OnInit, OnDestroy {
    studentUserForm: FormGroup;
    studentScholarInfosForm: FormGroup;
    studentResponsiblesForm: FormGroup;
    studentResponsibleAccountForm: FormGroup;
    studentMedicalInfosForm: FormGroup;
    studentCanteenForm: FormGroup;
    studentResidentialSchoolForm: FormGroup;
    // ------------------------------------
    routeState: string;
    // ------------------------------------
    client: Client;
    students: Array<Student>;
    student: Student;
    familySituations: Array<any>;
    weekDays: Array<any>;
    address: any;
    sexes: Array<string>;
    // ------------------------------------
    currentStep: number;
    // ------------------------------------
    creationError: boolean;
    creationErrorMsg: string;
    studentCreateConfirmation: boolean;
    isHealthInsuranceEnabled: boolean;
    isCanteenRecordedEnabled: boolean;
    isCanteenBreakfastEnabled: boolean;
    isCanteenLunchEnabled: boolean;
    isCanteenDinnerEnabled: boolean;
    isComplementaryHealthInsuranceEnabled: boolean;
    canteenWeekdaysOption: Array<WeekDay>;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private notificationService: NotificationService,
        private studentService: StudentService,
        private principalService: Principal,
        private fb: FormBuilder,
        private location: Location,
        private languageService: JhiLanguageService,
        private dateHelper: DateTimeHelper,
        private adapter: DateAdapter<any>
    ) {
        this.sexes = APP_CONSTANTS.SEXES;
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.adapter.setLocale(this.languageService.currentLang);

        this.routeState = this.router.routerState.snapshot.url.endsWith('new') ? 'new' : 'edit';
        if (this.routeState === 'edit') {
            this.student = Object.assign(new Student(), {...this.route.snapshot.data['student']});
        } else {
            this.student = new Student();
        }
        this.familySituations = APP_CONSTANTS.FAMILY_SITUATIONS;
        this.canteenWeekdaysOption = Object.assign([], APP_CONSTANTS.DAYS);
        this.client = this.route.snapshot.data['client'];
        this.studentCreateConfirmation = false;
        this.creationError = false;
        this.currentStep = 0;

        this.studentUserForm = new FormGroup({
            login: new FormControl(this.student.login, [Validators.required]),
            firstName: new FormControl(this.student.firstName, [Validators.required]),
            lastName: new FormControl(this.student.lastName, [Validators.required]),
            nationality: new FormControl(this.student.nationality, [Validators.required]),
            cellPhone: new FormControl(this.student.cellPhone),
            homePhone: new FormControl(this.student.homePhone),
            gender: new FormControl(this.student.gender, [Validators.required]),
            email: new FormControl(this.student.email, [Validators.email]),
            imageUrl: new FormControl(this.student.imageUrl),
            comment: new FormControl(this.student.comment),
            birthDate: new FormControl(this.student.birthDate, [Validators.required]),
            // birthDate: new FormControl(this.dateHelper.dateFieldToNgbDate(this.student.birthDate), [Validators.required]),
            addressName: new FormControl(this.student.address ? this.student.address.name : '', [Validators.required]),
            postalCode: new FormControl(this.student.address ? this.student.address.postalCode : '', [Validators.required]),
            city: new FormControl(this.student.address ? this.student.address.city : '', [Validators.required]),
            country: new FormControl(this.student.address ? this.student.address.country : '', [Validators.required]),
            // respLogin: new FormControl(this.student.responsibleAccountLogin, [Validators.required]),
        });
        this.studentScholarInfosForm = new FormGroup({
            nationalNumber: new FormControl(this.student.schoolingInfos.nationalNumber),
            redoubling: new FormControl(this.student.schoolingInfos.redoubling, [Validators.required]),
            entryDate: new FormControl(this.student.schoolingInfos.entryDate, [Validators.required]),
            originEstablishment: new FormControl(this.student.schoolingInfos.originEstablishment),
            individualizedReceptionPlan: new FormControl(this.student.schoolingInfos.individualizedReceptionPlan),
            individualizedReceptionPlanContent: new FormControl(this.student.schoolingInfos.individualizedReceptionPlanContent),
            personalizedSchoolingPlan: new FormControl(this.student.schoolingInfos.personalizedSchoolingPlan),
            personalizedSchoolingPlanContent: new FormControl(this.student.schoolingInfos.personalizedSchoolingPlanContent),
            exitDate: new FormControl(this.student.schoolingInfos.exitDate),
            exitReason: new FormControl(this.student.schoolingInfos.exitReason)
        });
        this.onIndividualizedReceptionPlanChange(this.student.schoolingInfos.individualizedReceptionPlan);
        this.onPersonalizedSchoolingPlanChange(this.student.schoolingInfos.personalizedSchoolingPlan);
        this.studentResponsiblesForm = new FormGroup({});
        this.studentResponsibleAccountForm = new FormGroup({
            respLogin: new FormControl(this.student.responsibleAccountLogin, [Validators.required]),
        });
        if (this.student.responsibles && !!this.student.responsibles.length) {
            this.studentResponsiblesForm.setControl('responsibles', this.fb.array(this.initResponsiblesFormGroup(this.student.responsibles) || []));
        } else {
            this.studentResponsiblesForm.setControl('responsibles', this.fb.array(([this.initResponsibleFormGroup(new Responsible(true))]).map((x) => this.fb.group(x))));
        }
        this.isHealthInsuranceEnabled = !this.student.medicalInfos.healthInsurance;
        this.isComplementaryHealthInsuranceEnabled = !this.student.medicalInfos.complementaryHealthInsurance;
        this.studentMedicalInfosForm = new FormGroup({
            healthInsurance: new FormControl(this.student.medicalInfos.healthInsurance),
            healthInsuranceName: new FormControl({
                value: this.student.medicalInfos.healthInsuranceName,
                disabled: this.isHealthInsuranceEnabled
            }),
            healthInsuranceReference: new FormControl({
                value: this.student.medicalInfos.healthInsuranceReference,
                disabled: this.isHealthInsuranceEnabled
            }),
            healthInsuranceContact: new FormControl({
                value: this.student.medicalInfos.healthInsuranceContact,
                disabled: this.isHealthInsuranceEnabled
            }),
            complementaryHealthInsurance: new FormControl(this.student.medicalInfos.complementaryHealthInsurance),
            complementaryHealthInsuranceName: new FormControl({
                value: this.student.medicalInfos.complementaryHealthInsuranceName,
                disabled: this.isComplementaryHealthInsuranceEnabled
            }),
            complementaryHealthInsuranceReference: new FormControl({
                value: this.student.medicalInfos.complementaryHealthInsuranceReference,
                disabled: this.isComplementaryHealthInsuranceEnabled
            }),
            complementaryHealthInsuranceContact: new FormControl({
                value: this.student.medicalInfos.complementaryHealthInsuranceContact,
                disabled: this.isComplementaryHealthInsuranceEnabled
            }),
            knownDiseases: new FormControl(this.student.medicalInfos.knownDiseases),
            allergies: new FormControl(this.student.medicalInfos.allergies),
            healthComment: new FormControl(this.student.medicalInfos.healthComment),
        });
        this.studentCanteenForm = new FormGroup({
            recorded: new FormControl(this.student.canteenRegistration.recorded),
            breakfast: new FormControl({
                value: this.student.canteenRegistration.breakfast,
                disabled: this.isCanteenRecordedEnabled
            }),
            lunch: new FormControl({
                value: this.student.canteenRegistration.lunch,
                disabled: this.isCanteenRecordedEnabled
            }),
            dinner: new FormControl({
                value: this.student.canteenRegistration.dinner,
                disabled: this.isCanteenRecordedEnabled
            }),
            weekDays: this.fb.array(this.student.canteenRegistration.weekDays),
            subscriptionPeriodStart: new FormControl(this.student.canteenRegistration.subscriptionPeriodStart),
            subscriptionPeriodEnd: new FormControl(this.student.canteenRegistration.subscriptionPeriodEnd)
        });
        this.studentResidentialSchoolForm = new FormGroup({
            registered: new FormControl(this.student.residentialSchool.registered),
            name: new FormControl(this.student.residentialSchool.name),
            periodStart: new FormControl(this.student.residentialSchool.periodStart),
            periodEnd: new FormControl(this.student.residentialSchool.periodEnd),
        });
    }

    initResponsiblesFormGroup(responsibles: Array<Responsible>): Array<FormGroup> {
        return responsibles.map((responsible) => {
            return this.initResponsibleFormGroup(responsible);
        });
    }

    initResponsibleFormGroup(responsible: Responsible) {
        return new FormGroup({
            respAccount: new FormControl(responsible.respAccount, [Validators.required]),
            firstName: new FormControl(responsible.firstName, [Validators.required]),
            lastName: new FormControl(responsible.lastName, [Validators.required]),
            gender: new FormControl(responsible.gender, [Validators.required]),
            birthDate: new FormControl(responsible.birthdate, [Validators.required]),
            nationality: new FormControl(responsible.nationality, [Validators.required]),
            addressName: new FormControl(responsible.address ? responsible.address.name : '', [Validators.required]),
            postalCode: new FormControl(responsible.address ? responsible.address.postalCode : '', [Validators.required]),
            city: new FormControl(responsible.address ? responsible.address.city : '', [Validators.required]),
            country: new FormControl(responsible.address ? responsible.address.country : '', [Validators.required]),
            phone: new FormControl(responsible.phone),
            cellPhone: new FormControl(responsible.cellPhone),
            email: new FormControl(responsible.email, [Validators.email]),
            secondaryEmail: new FormControl(responsible.secondaryEmail),
            familySituation: new FormControl(responsible.familySituation, [Validators.required]),
            studentRelationship: new FormControl(responsible.studentRelationship, [Validators.required]),
            dependentChildren: new FormControl(responsible.dependentChildren, [Validators.required]),
            profession: new FormControl(responsible.profession, [Validators.required]),
            proEmail: new FormControl(responsible.proEmail),
            workPhone: new FormControl(responsible.workPhone),
            workCellPhone: new FormControl(responsible.workCellPhone),
            parentsAssociation: new FormControl(responsible.parentsAssociation),
            parentsAssociationPosition: new FormControl(responsible.parentsAssociationPosition),
            comment: new FormControl(responsible.comment),
        });
    }

    resetMessages() {
        this.studentCreateConfirmation = false;
        this.creationError = false;
        this.creationErrorMsg = '';
    }

    createOrUpdateStudent() {
        if (this.areFormsValid()) {
            this.student.update(this.studentUserForm,
                this.studentScholarInfosForm,
                this.studentResponsibleAccountForm,
                this.studentResponsiblesForm,
                this.studentMedicalInfosForm,
                this.studentCanteenForm,
                this.studentResidentialSchoolForm,
                this.languageService.currentLang
            );
            if (this.routeState === 'edit') {
                this.studentService.update(this.student).subscribe(
                    () => {
                        this.saveConfirmation();
                    },
                    () => {
                        this.saveError();
                    }
                );
            } else {
                this.studentService.create(this.student).subscribe(
                    () => {
                        this.saveConfirmation();
                    },
                    () => {
                        this.saveError();
                    }
                );
            }

        } else {
            this.saveError();
        }
    }

    private saveConfirmation() {
        this.notificationService.add('toast-save-title',
            'toast-save',
            NotificationTypes.SUCCESS);
    }

    private saveError() {
        this.notificationService.add('toast-save-error-title',
            'toast-save-error',
            NotificationTypes.ERROR);
    }

    goBack() {
        this.location.back();
    }

    isPersonalizedSchoolingPlanContentDisabled() {
        return this.studentScholarInfosForm.get('personalizedSchoolingPlan').value === false;
    }

    isIndividualizedReceptionPlanContentDisabled() {
        return this.studentScholarInfosForm.get('individualizedReceptionPlan').value === false;
    }

    onPersonalizedSchoolingPlanChange($event: boolean) {
        this.studentScholarInfosForm.get('personalizedSchoolingPlanContent').reset();
        if ($event) {
            this.studentScholarInfosForm.get('personalizedSchoolingPlanContent').enable();
        } else {
            this.studentScholarInfosForm.get('personalizedSchoolingPlanContent').disable();
        }
    }

    onIndividualizedReceptionPlanChange($event: boolean) {
        this.studentScholarInfosForm.get('individualizedReceptionPlanContent').reset();
        if ($event) {
            this.studentScholarInfosForm.get('individualizedReceptionPlanContent').enable();
        } else {
            this.studentScholarInfosForm.get('individualizedReceptionPlanContent').disable();
        }
    }

    onRespAccountChange(index: number, $event: boolean) {
        const respForms = (this.studentResponsiblesForm.controls.responsibles as FormArray).controls.filter((respForm, i) => {
            return index !== i;
        });
        respForms.forEach((respFormControl) => {
            respFormControl['controls'].respAccount.setValue(false);
        });
        if (!$event && !!respForms.length) {
            respForms[0]['controls'].respAccount.setValue(true);
        }
    }

    onHealthInsuranceChange($event: boolean) {
        this.isHealthInsuranceEnabled = $event;
        this.studentMedicalInfosForm.get('healthInsuranceName').reset();
        this.studentMedicalInfosForm.get('healthInsuranceReference').reset();
        this.studentMedicalInfosForm.get('healthInsuranceContact').reset();
        if ($event) {
            this.studentMedicalInfosForm.get('healthInsuranceName').enable();
            this.studentMedicalInfosForm.get('healthInsuranceReference').enable();
            this.studentMedicalInfosForm.get('healthInsuranceContact').enable();
        } else {
            this.studentMedicalInfosForm.get('healthInsuranceName').disable();
            this.studentMedicalInfosForm.get('healthInsuranceReference').disable();
            this.studentMedicalInfosForm.get('healthInsuranceContact').disable();
        }
    }

    onComplementaryHealthInsuranceChange($event: boolean) {
        this.isHealthInsuranceEnabled = $event;
        this.studentMedicalInfosForm.get('complementaryHealthInsuranceName').reset();
        this.studentMedicalInfosForm.get('complementaryHealthInsuranceReference').reset();
        this.studentMedicalInfosForm.get('complementaryHealthInsuranceContact').reset();
        if ($event) {
            this.studentMedicalInfosForm.get('complementaryHealthInsuranceName').enable();
            this.studentMedicalInfosForm.get('complementaryHealthInsuranceReference').enable();
            this.studentMedicalInfosForm.get('complementaryHealthInsuranceContact').enable();
        } else {
            this.studentMedicalInfosForm.get('complementaryHealthInsuranceName').disable();
            this.studentMedicalInfosForm.get('complementaryHealthInsuranceReference').disable();
            this.studentMedicalInfosForm.get('complementaryHealthInsuranceContact').disable();
        }
    }

    addResponsible() {
        (this.studentResponsiblesForm.controls.responsibles as FormArray).push(this.initResponsibleFormGroup(new Responsible(false)));
    }

    removeResponsible(itemIndex: number) {
        (this.studentResponsiblesForm.controls.responsibles as FormArray).removeAt(itemIndex);
    }

    compareFamilySituations(familySituation1, familySituation2) {
        return familySituation1 === familySituation2;
    }

    enterStep(index) {
        this.currentStep = index;
    }

    onCanteenRecordedChange($event: boolean) {
        this.isCanteenRecordedEnabled = $event;
        this.studentCanteenForm.get('breakfast').reset();
        this.studentCanteenForm.get('lunch').reset();
        this.studentCanteenForm.get('dinner').reset();
        if ($event) {
            this.studentCanteenForm.get('breakfast').enable();
            this.studentCanteenForm.get('lunch').enable();
            this.studentCanteenForm.get('dinner').enable();
        } else {
            this.studentCanteenForm.get('breakfast').disable();
            this.studentCanteenForm.get('lunch').disable();
            this.studentCanteenForm.get('dinner').disable();
        }
    }

    onCanteenBreakfastChange($event: boolean) {
        this.isCanteenBreakfastEnabled = $event;
    }

    onResidentialSchoolRegisteredChange($event: boolean) {
        // this.isCanteenBreakfastEnabled = $event;
    }

    onCanteenLunchChange($event: boolean) {
        this.isCanteenLunchEnabled = $event;
    }

    onCanteenDinnerChange($event: boolean) {
        this.isCanteenDinnerEnabled = $event;
    }

    private areFormsValid() {
        return this.studentUserForm.valid
            && this.studentScholarInfosForm.valid
            && this.studentResponsiblesForm.valid
            && this.studentMedicalInfosForm.valid
            && this.studentCanteenForm.valid
            && this.studentResponsibleAccountForm.valid
            && this.studentResidentialSchoolForm.valid;
    }

    onBirthDateSelect($event) {
    }

    changeCanteenWeekDay(day: WeekDay, checked: Event) {
        const birthFormArray = <FormArray>this.studentCanteenForm.controls.weekDays;
        if (checked.currentTarget['checked']) {
            birthFormArray.push(new FormControl(day));
        } else {
            const index = birthFormArray.controls.findIndex((x) => x.value.code === day.code);
            birthFormArray.removeAt(index);
        }
    }

    isCanteenWeekDayChecked(day: WeekDay) {
        const birthFormArray = <FormArray>this.studentCanteenForm.controls.weekDays;
        const result = birthFormArray.controls
            .map((control) => control.value.code === day.code)
            .filter((booleanValue) => booleanValue === true);
        if (result && !!result.length) {
            return result.reduce((a, b) => a || b);
        }
        return false;
    }
}
