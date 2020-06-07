import {Component, OnInit, OnDestroy, ViewEncapsulation} from '@angular/core';
import {Client, Grade} from '../../shared/model';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {NotificationService, Principal} from '../../shared';
import {GradeService} from '../../shared/services/grade.service';
import {APP_CONSTANTS} from '../../app.constants';
import {GradeSerie} from '../../shared/model/grade-serie.model';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {ArraysHelper} from '../../shared/services/utils/table.helper';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-grade-edit',
    templateUrl: './grade.edit.component.html',
    styleUrls: [
        './grade.edit.component.scss',
        '../../../content/icons/template/icofont/css/icofont.scss'
    ]
})
export class GradeEditComponent implements OnInit, OnDestroy {
    gradeForm: FormGroup;
    routeState: string;
    client: Client;
    gradeCreateConfirmation: boolean;
    gradesFound: Array<Grade>;
    grades: Array<Grade>;
    gradesAutocomplete: Array<any>;
    creationError: boolean;
    creationErrorMsg: string;
    grade: Grade;
    gradesNotations: any;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private notificationService: NotificationService,
        private gradeService: GradeService,
        private principalService: Principal,
        private fb: FormBuilder,
        private location: Location,
        private dateHelper: DateTimeHelper
    ) {
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.routeState = this.router.routerState.snapshot.url.endsWith('new') ? 'new' : 'edit';
        this.client = this.route.snapshot.data['client'];
        this.grades = this.route.snapshot.data['grades'];
        if (this.routeState === 'edit') {
            this.grade = new Grade().convert(this.route.snapshot.data['grade']);
            this.gradeForm = new FormGroup({
                name: new FormControl(this.grade.name, [Validators.required]),
                order: new FormControl(this.grade.order, [Validators.required]),
                notation: new FormControl(this.grade.notation, [Validators.minLength(2)]),
                diminutive: new FormControl(this.grade.diminutive, [Validators.minLength(2)]),
                maxMinutesPerDay: new FormControl(this.grade.maxMinutesPerDay, [Validators.min(15), Validators.max(480)]),
                // timeTableOptions: new FormGroup({
                //     schoolRoomsDistances: new FormControl(this.grade.timeTableOptions.schoolRoomsDistances, [Validators.required]),
                //     dayTimeBreaks: new FormControl(this.grade.timeTableOptions.dayTimeBreaks, [Validators.required]),
                //     coursesTimeSlots: new FormControl(this.grade.timeTableOptions.coursesTimeSlots, [Validators.required]),
                //     coursesStartTime: new FormControl(this.grade.timeTableOptions.coursesStartTime, [Validators.required]),
                //     coursesEndTime: new FormControl(this.grade.timeTableOptions.coursesEndTime, [Validators.required]),
                //     calendarTimelineDuration: new FormControl(this.grade.timeTableOptions.calendarTimelineDuration, [Validators.required]),
                //     firstWeekDay: new FormControl(this.grade.timeTableOptions.firstWeekDay, [Validators.required]),
                //     surfaceMinPerStudent: new FormControl(this.grade.timeTableOptions.surfaceMinPerStudent, [Validators.required]),
                // })
            });
            this.gradeForm.setControl('series', this.fb.array((this.grade.series || []).map((x) => this.fb.group(x))));
        } else {
            this.grade = new Grade();
            this.grade.order = this.grades.length + 1;
            this.gradeForm = new FormGroup({
                name: new FormControl('', [Validators.required]),
                order: new FormControl(1, [Validators.required]),
                notation: new FormControl('', [Validators.required, Validators.minLength(2)]),
                diminutive: new FormControl('', [Validators.minLength(2)]),
                maxMinutesPerDay: new FormControl('', [Validators.required, Validators.min(15), Validators.max(480)]),
                series: new FormArray([]),
                // timeTableOptions: new FormGroup({
                //     gradesDistances: new FormControl('', [Validators.required]),
                //     dayTimeBreaks: new FormControl('', [Validators.required]),
                //     coursesTimeSlots: new FormControl('', [Validators.required]),
                //     coursesStartTime: new FormControl('', [Validators.required]),
                //     coursesEndTime: new FormControl('', [Validators.required]),
                //     calendarTimelineDuration: new FormControl('', [Validators.required]),
                //     firstWeekDay: new FormControl('', [Validators.required]),
                //     surfaceMinPerStudent: new FormControl('', [Validators.required]),
                // })
            });
        }
        this.gradesAutocomplete = [...this.grades];
        this.gradeCreateConfirmation = false;
        this.gradesFound = [];
        this.creationError = false;
        this.gradesNotations = APP_CONSTANTS.SCHOOL_CLASSES_NOTATIONS;
    }

    initSerieFormGroup() {
        return new FormGroup({
            name: new FormControl('', [Validators.required]),
            diminutive: new FormControl(''),
            option: new FormControl(''),
        });
    }

    removeSerie() {
        const seriesControl = <FormArray>this.gradeForm.controls['series'];
        seriesControl.removeAt(seriesControl.length - 1);
    }

    addSerie() {
        (this.gradeForm.controls.series as FormArray).push(this.initSerieFormGroup());
    }

    resetMessages() {
        this.gradeCreateConfirmation = false;
        this.creationError = false;
        this.creationErrorMsg = '';
    }

    basicGradeFormValid() {
        const form = this.gradeForm.controls;
        return form.name.valid && form.notation.valid && form.maxMinutesPerDay.valid && form.diminutive.valid;
    }

    createOrUpdateGrade(form) {
        const values = form.value;
        this.principalService.identity().then((user) => {
            const series = values.series.map((serie) => {
                return new GradeSerie(serie.name, serie.diminutive, serie.option);
            });
            switch (this.routeState) {
                case 'edit':
                    if (form.dirty && form.valid) {
                        this.grade = this.grade.update(this.grade.id, values.name, values.order, values.notation, values.diminutive, values.maxMinutesPerDay, series, null);
                        this.update();
                    } else {
                        this.notificationService.addToast('grade.new.form.error-toast-title',
                            'grade.new.form.form-error',
                            'error');
                    }
                    break;
                case 'new':
                    if (form.dirty && form.valid) {
                        this.grade = new Grade(user.clientId, values.name, values.order, values.notation, values.diminutive, values.maxMinutesPerDay, series, null);
                        this.create();
                    } else {
                        this.notificationService.addToast('grade.new.form.error-toast-title',
                            'grade.new.form.form-error',
                            'error');
                    }
                    break;
            }
        });
    }

    private create() {
        this.gradeService.create(this.grades, this.grade).subscribe(
            (res) => {
                this.notificationService.addToast('grade.new.form.toast-title',
                    'grade.new.form.creation-successfull',
                    'success');
                this.router.navigateByUrl('/grades');
            }, (err) => console.log(err));
    }

    private update() {
        this.gradeService.update(this.grades, this.grade).subscribe(
            (res) => {
                this.notificationService.addToast('grade.edit.form.toast-title',
                    'grade.edit.form.update-successfull',
                    'success');
                this.router.navigateByUrl('/grades');
            }, (err) => console.log(err));
    }

    updateGradesAutocomplete(name) {
        this.gradesAutocomplete = [...this.grades
            .filter((grade) => {
                return grade.name !== name
                    && !this.gradeForm.controls.series.value
                        .find((value) => {
                            return grade.name === value.name;
                        });
            })];
    }

    goBack() {
        this.location.back();
    }

    compareGradesNotations(notation1, notation2) {
        return notation1.code === notation2.code;
    }

    isFormValid() {
        return this.gradeForm.valid;
    }

    displayHoursAndMinutes(minutes: number): string {
        return this.dateHelper.displayHoursAndMinutes(minutes);
    }

    increaseOrder(gradeForOrders: any) {
        const gradeOrderToIncrease = this.grades.find((grade) => grade.order === gradeForOrders);
        const gradeOrderToDecrease = this.grades.find((grade) => grade.order === gradeForOrders + 1);
        this.reorderGrades(gradeOrderToIncrease, gradeOrderToDecrease);
        this.grades = ArraysHelper.sortArray(this.grades, 'order', 'desc');
    }

    decreaseOrder(orderToDecrease: any) {
        const gradeOrderToDecrease = this.grades.find((grade) => grade.order === orderToDecrease);
        const gradeOrderToIncrease = this.grades.find((grade) => grade.order === orderToDecrease - 1);
        this.reorderGrades(gradeOrderToIncrease, gradeOrderToDecrease);
        this.grades = ArraysHelper.sortArray(this.grades, 'order', 'desc');
    }

    reorderGrades(gradeOrderToIncrease: Grade, gradeOrderToDecrease: Grade) {
        this.grades.forEach((grade) => {
            if (grade.order > 1 && grade.name === gradeOrderToDecrease.name) {
                grade.order--;
            }
            if (grade.order < this.grades.length && grade.name === gradeOrderToIncrease.name) {
                grade.order++;
            }
        });
    }

    getGradeCellBackground(gradeForRanking: Grade) {
        let className = '';
        if (gradeForRanking.name === this.gradeForm.controls.name.value) {
            className = 'background-cell-color-red';
        }
        return className;
    }
}
