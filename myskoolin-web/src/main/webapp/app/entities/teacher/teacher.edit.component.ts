import {
    Component,
    OnInit,
    OnDestroy,
    ViewEncapsulation,
} from '@angular/core';
import {Grade, Subject, Teacher} from '../../shared/model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {NotificationService, Principal} from '../../shared';
import {TeacherService} from '../../shared/services/teacher.service';
import {APP_CONSTANTS} from '../../app.constants';
import {JhiLanguageService} from 'ng-jhipster';
import {WeekDay} from '../../shared/model/sub/week-day.model';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material';
import {MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {SubjectService} from '../../shared/services/subject.service';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-teacher-edit',
    templateUrl: './teacher.edit.component.html',
    styleUrls: [
        './teacher.edit.component.scss',
        '../../../content/icons/template/icofont/css/icofont.scss'
    ],
    providers: [
        {provide: MAT_DATE_LOCALE, useValue: 'fr-FR'},
        {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
        {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
    ],
})
export class TeacherEditComponent implements OnInit, OnDestroy {
    teacherUserForm: FormGroup;
    // ------------------------------------
    routeState: string;
    // ------------------------------------
    teacher: Teacher;
    grades: Array<Grade>;
    taughtSubjects: Subject[];
    substitutedTeachers: Teacher[];
    familySituations: Array<any>;
    weekDays: Array<any>;
    address: any;
    sexes: Array<string>;
    // ------------------------------------
    currentStep: number;
    // ------------------------------------
    creationError: boolean;
    creationErrorMsg: string;
    teacherCreateConfirmation: boolean;
    canteenWeekdaysOption: Array<WeekDay>;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private notificationService: NotificationService,
        private teacherService: TeacherService,
        private principalService: Principal,
        private fb: FormBuilder,
        private location: Location,
        private languageService: JhiLanguageService,
        private dateHelper: DateTimeHelper,
        private adapter: DateAdapter<any>,
        private subjectService: SubjectService
    ) {
        this.sexes = APP_CONSTANTS.SEXES;
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.adapter.setLocale(this.languageService.currentLang);
        this.routeState = this.router.routerState.snapshot.url.endsWith('new') ? 'new' : 'edit';
        this.grades = Object.assign([], [...this.route.snapshot.data['grades']]);
        if (this.routeState === 'edit') {
            this.teacher = Object.assign(new Teacher(), {...this.route.snapshot.data['teacher']});
        } else {
            this.teacher = new Teacher();
        }
        this.familySituations = APP_CONSTANTS.FAMILY_SITUATIONS;
        this.canteenWeekdaysOption = Object.assign([], APP_CONSTANTS.DAYS);
        this.teacherCreateConfirmation = false;
        this.creationError = false;
        this.currentStep = 0;

        this.teacherUserForm = new FormGroup({
            login: new FormControl(this.teacher.login, [Validators.required]),
            imageUrl: new FormControl(this.teacher.imageUrl),
            firstName: new FormControl(this.teacher.firstName, [Validators.required]),
            lastName: new FormControl(this.teacher.lastName, [Validators.required]),
            sex: new FormControl(this.teacher.sex, [Validators.required]),
            nationality: new FormControl(this.teacher.nationality, [Validators.required]),
            birthDate: new FormControl(this.teacher.birthDate, [Validators.required]),
            homePhone: new FormControl(this.teacher.homePhone),
            cellPhone: new FormControl(this.teacher.cellPhone, [Validators.required]),
            proPhone: new FormControl(this.teacher.proPhone),
            proCellPhone: new FormControl(this.teacher.proCellPhone),
            familySituation: new FormControl(this.teacher.familySituation, [Validators.required]),
            email: new FormControl(this.teacher.email, [Validators.email, Validators.required]),
            proEmail: new FormControl(this.teacher.proEmail),
            addressName: new FormControl(this.teacher.address ? this.teacher.address.name : '', [Validators.required]),
            postalCode: new FormControl(this.teacher.address ? this.teacher.address.postalCode : '', [Validators.required]),
            city: new FormControl(this.teacher.address ? this.teacher.address.city : '', [Validators.required]),
            country: new FormControl(this.teacher.address ? this.teacher.address.country : '', [Validators.required]),
            substitute: new FormControl(this.teacher.substitute, [Validators.required]),
            grades: new FormControl(this.teacher.grades && !!this.teacher.grades.length
                ? Grade.sort(this.teacher.grades)
                : [],
                [Validators.required]),
            taughtSubjects: new FormControl({
                    value: this.teacher.taughtSubjects && !!this.teacher.taughtSubjects.length
                        ? Subject.sort(this.teacher.taughtSubjects)
                        : [],
                    disabled: !this.teacher.taughtSubjects || !this.teacher.taughtSubjects.length
                },
                [Validators.required]),
            employedDate: new FormControl(this.teacher.employedDate, [Validators.required]),
            exitDate: new FormControl(this.teacher.exitDate),
            exitReason: new FormControl(this.teacher.exitReason),
            substitutedTeachers: new FormControl(
                {
                    value: this.teacher.substitutedTeachers && !!this.teacher.substitutedTeachers.length
                        ? this.teacher.substitutedTeachers
                        : [], disabled: !this.teacher.substitute
                }),
            comment: new FormControl(this.teacher.comment),
        });
    }

    resetMessages() {
        this.teacherCreateConfirmation = false;
        this.creationError = false;
        this.creationErrorMsg = '';
    }

    createOrUpdateTeacher() {
        if (this.teacherUserForm.valid) {
            this.teacher.update(this.teacherUserForm, this.languageService.currentLang);
            if (this.routeState === 'edit') {
                this.teacherService.update(this.teacher).subscribe(
                    () => {
                        this.saveConfirmation();
                    },
                    () => {
                        this.saveError();
                    }
                );
            } else {
                this.teacherService.create(this.teacher).subscribe(
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
        this.notificationService.addToast('teacher.new.form.toast-save-title',
            'teacher.new.form.toast-save',
            'success');
    }

    private saveError() {
        this.notificationService.addToast('teacher.new.form.toast-save-error-title',
            'teacher.new.form.toast-save-error',
            'error');
    }

    goBack() {
        this.location.back();
    }

    isTaughtSubjectsDisabled() {
        return !this.teacherUserForm.controls.grades.value.length;
    }

    onGradesChange($event) {
        this.subjectService.findByGradesIds($event.value).subscribe((res) => {
            this.taughtSubjects = res.body && !!res.body.length ? Subject.sort(res.body) : [];
        });
    }

    onSubstitutedChange($event) {
        console.log('event : ' + $event);
        console.log('taughtSubjects : ' + this.teacherUserForm.controls.taughtSubjects.value);
        console.log('grades : ' + this.teacherUserForm.controls.grades.value);
        if ($event && !!this.teacherUserForm.controls.taughtSubjects.value.length && !!this.teacherUserForm.controls.grades.value.length) {
            this.teacherService.findByGradesIdsAndSubjectsIds(
                this.teacherUserForm.controls.taughtSubjects.value.map((subject) => subject.id),
                this.teacherUserForm.controls.grades.value.map((grade) => grade.id)).subscribe((res) => {
                this.substitutedTeachers = res.body;
            });
        }
    }

    compareGradesFn(grade1: Grade, grade2: Grade) {
        return grade1.name === grade2.name;
    }

    compareSubjectsFn(subject1: Subject, subject2: Subject) {
        return subject1.name === subject2.name;
    }

    compareFamilySituationsFn(fs1: string, fs2: string) {
        return fs1 === fs2;
    }
}
