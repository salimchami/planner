import {
    Component,
    OnInit,
    OnDestroy,
    ViewEncapsulation,
} from '@angular/core';
import {Client, Grade, Subject} from '../../shared/model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {NotificationService, Principal} from '../../shared';
import {SubjectService} from '../../shared/services/subject.service';
import {GradeSerie} from '../../shared/model/grade-serie.model';
import {ColorPickerService} from 'ngx-color-picker';
import {APP_CONSTANTS} from '../../app.constants';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-subject-edit',
    templateUrl: './subject.edit.component.html',
    styleUrls: [
        './subject.edit.component.scss',
        '../../../content/icons/template/icofont/css/icofont.scss'
    ],
})
export class SubjectEditComponent implements OnInit, OnDestroy {
    subjectForm: FormGroup;
    routeState: string;
    client: Client;
    subjectCreateConfirmation: boolean;
    subjectsFound: Array<Subject>;
    subjects: Array<Subject>;
    grades: Array<Grade>;
    schoolRoomsTypes: Array<any>;
    gradeSeries: Array<GradeSerie>;
    subjectsAutocomplete: Array<any>;
    creationError: boolean;
    creationErrorMsg: string;
    subject: Subject;
    rgbaTextColor: string;
    rgbaTextBgColor: string;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private notificationService: NotificationService,
        private subjectService: SubjectService,
        private principalService: Principal,
        private fb: FormBuilder,
        private location: Location,
        public cpService: ColorPickerService
    ) {
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.routeState = this.router.routerState.snapshot.url.endsWith('new') ? 'new' : 'edit';
        this.schoolRoomsTypes = Object.assign([], APP_CONSTANTS.SCHOOL_ROOMS_TYPES);
        this.subject = new Subject();
        if (this.routeState === 'edit') {
            const subject = this.route.snapshot.data['subject'];
            this.subject.update({...subject}, subject.clientId, subject.id);
        } else {
            this.subject.bgColor = '';
            this.subject.color = '';
            this.subject.foreignLanguage = false;
        }
        this.subjectForm = new FormGroup({
            name: new FormControl(this.subject.name, [Validators.required]),
            customName: new FormControl(this.subject.customName),
            grade: new FormControl(this.subject.grade, [Validators.required]),
            gradeSerie: new FormControl(this.subject.gradeSerie),
            coefficient: new FormControl(this.subject.coefficient, [Validators.min(1)]),
            color: new FormControl(this.subject.color),
            bgColor: new FormControl(this.subject.bgColor),
            foreignLanguage: new FormControl(this.subject.foreignLanguage),
            minMinutesPerDay: new FormControl(this.subject.minMinutesPerDay),
            maxMinutesPerDay: new FormControl(this.subject.maxMinutesPerDay),
            minutesPerWeek: new FormControl(this.subject.minutesPerWeek),
            coursesFrequencyPerWeek: new FormControl(this.subject.coursesFrequencyPerWeek),
            schoolRoomsTypes: new FormControl(this.subject.schoolRoomsTypes || [], [Validators.required]),
        });
        this.client = this.route.snapshot.data['client'];
        this.subjects = this.route.snapshot.data['subjects'];
        this.grades = this.route.snapshot.data['grades'];
        this.onChangeGrade();
        this.subjectsAutocomplete = [...this.subjects];
        this.subjectCreateConfirmation = false;
        this.creationError = false;
        this.subjectsFound = [];
        this.rgbaTextColor = this.subject.color;
        this.rgbaTextBgColor = this.subject.bgColor;
    }

    resetMessages() {
        this.subjectCreateConfirmation = false;
        this.creationError = false;
        this.creationErrorMsg = '';
    }

    createOrUpdateSubject(form) {
        const values = form.value;
        this.principalService.identity().then((user) => {
            switch (this.routeState) {
                case 'edit':
                    this.subject.update(values, this.subject.id, this.subject.clientId);
                    this.update();
                    break;
                case 'new':
                    if (!!this.subjects.filter((subject) => subject.name === form.value.name).length) {
                        this.notificationService.add('subject.new.form.error-toast-title',
                            'subject.new.form.form-name-error',
                            'error');
                    } else {
                        this.subject = new Subject(null, user.clientId, values.name, values.customName,
                            values.grade, values.gradeSerie, values.coefficient,
                            values.foreignLanguage, values.minMinutesPerDay, values.maxMinutesPerDay, values.minutesPerWeek,
                            values.coursesFrequencyPerWeek, this.subject.color, this.subject.bgColor);
                        this.create();
                    }
                    break;
            }
        });
    }

    private create() {
        this.subjectService.create(this.subject).subscribe(
            (res) => {
                this.notificationService.add('subject.new.form.toast-title',
                    'subject.new.form.creation-successfull',
                    'success');
                this.router.navigateByUrl('/subjects');
            }, (err) => console.log(err));
    }

    private update() {
        console.log(this.subject);
        this.subjectService.update(this.subject).subscribe(
            (res) => {
                this.notificationService.add('subject.edit.form.toast-title',
                    'subject.edit.form.update-successfull',
                    'success');
                this.router.navigateByUrl('/subjects');
            }, (err) => console.log(err));
    }

    goBack() {
        this.location.back();
    }

    compareGradesTypes(grade1, grade2) {
        if (grade1 && grade2) {
            return grade1.name === grade2.name;
        }
        return false;
    }

    compareGradesSeriesTypes(gradeSerie1, gradeSerie2) {
        return gradeSerie1.name === gradeSerie2.name;
    }

    compareSchoolRoomsTypes(roomType1, roomType2) {
        if (roomType1 && roomType2) {

            return roomType1.code === roomType2.code;
        }
        return false;
    }

    isFormValid() {
        return this.subjectForm.valid;
    }

    onChangeGrade() {
        const gradeSeries = this.grades
            .filter((grade) => {
                return grade.name === this.subjectForm.controls.grade.value;
            })
            .map((grade) => {
                return grade.series;
            });
        this.gradeSeries = gradeSeries[0];
        if (this.gradeSeries && !this.gradeSeries.length) {
            this.subjectForm.controls.gradeSerie.disable({onlySelf: true, emitEvent: false});
        } else {
            this.subjectForm.controls.gradeSerie.enable({onlySelf: true, emitEvent: false});
        }
    }

    onChangeColorHex8(color: string, colorType: string): string {
        switch (colorType) {
            case 'text':
                this.subject.color = color;
                break;
            case 'background':
                this.subject.bgColor = color;
                break;
        }
        return this.cpService.outputFormat(this.cpService.stringToHsva(color, true),
            'hex', null);
    }

    compareSchoolRoomsTypeFn(schoolRoomType1: any, schoolRoomType2: any): boolean {
        return schoolRoomType1 && schoolRoomType2 && schoolRoomType1.code === schoolRoomType2.code;
    }
}
