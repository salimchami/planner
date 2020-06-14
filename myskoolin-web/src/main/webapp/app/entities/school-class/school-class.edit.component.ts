import {
    Component,
    OnInit,
    OnDestroy,
    ViewEncapsulation,
} from '@angular/core';
import {Grade, SchoolClass, Student, Teacher} from '../../shared/model';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {NotificationService, Principal} from '../../shared';
import {SchoolClassService} from '../../shared/services/school-class.service';
import {APP_CONSTANTS} from '../../app.constants';
import {JhiDateUtils, JhiLanguageService} from 'ng-jhipster';
import {DateTimeHelper} from '../../shared/services/utils/date-time-helper.service';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material';
import {MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {GradeSerie} from '../../shared/model/grade-serie.model';
import {TeacherService} from '../../shared/services/teacher.service';
import {debounceTime, finalize, switchMap, tap} from 'rxjs/operators';
import {StudentService} from '../../shared/services/student.service';
import {Observable, of} from 'rxjs';
import {empty} from 'rxjs/internal/Observer';
import {HttpResponse} from '@angular/common/http';

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'jhi-schoolclass-edit',
    templateUrl: './school-class.edit.component.html',
    styleUrls: [
        './school-class.edit.component.scss',
        '../../../content/icons/template/icofont/css/icofont.scss'
    ],
    providers: [
        {provide: MAT_DATE_LOCALE, useValue: 'fr-FR'},
        {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
        {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
    ],
})
export class SchoolClassEditComponent implements OnInit, OnDestroy {
    schoolClassForm: FormGroup;
    schoolClassStudentsForm: FormGroup;
    // ------------------------------------
    routeState: string;
    // ------------------------------------
    schoolClass: SchoolClass;
    grades: Array<Grade>;
    weekDays: Array<any>;
    address: any;
    sexes: Array<string>;
    // ------------------------------------
    currentStep: number;
    // ------------------------------------
    creationError: boolean;
    creationErrorMsg: string;
    schoolClassCreateConfirmation: boolean;
    gradesSeries: Array<GradeSerie>;
    notations: Array<any>;
    headTeachers: Array<Teacher>;
    filteredStudents: Array<Student> = [];
    searchedStudents: Array<Student> = [];
    studentsRowsNumber: number;
    isLoading = false;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private notificationService: NotificationService,
        private schoolClassService: SchoolClassService,
        private teacherService: TeacherService,
        private studentService: StudentService,
        private principalService: Principal,
        private location: Location,
        private languageService: JhiLanguageService,
        private dateHelper: DateTimeHelper,
        private adapter: DateAdapter<any>,
        private fb: FormBuilder,
    ) {
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.sexes = APP_CONSTANTS.SEXES;
        this.studentsRowsNumber = 15;
        this.adapter.setLocale(this.languageService.currentLang);
        this.routeState = this.router.routerState.snapshot.url.endsWith('new') ? 'new' : 'edit';
        this.grades = Object.assign([], {...this.route.snapshot.data['grades']});
        if (this.routeState === 'edit') {
            const dbStudent = {...this.route.snapshot.data['schoolClass']};
            this.schoolClass = Object.assign(new SchoolClass(), dbStudent);
            this.searchedStudents = [...this.schoolClass.students];
        } else {
            this.schoolClass = new SchoolClass();
        }
        this.schoolClassCreateConfirmation = false;
        this.creationError = false;
        this.currentStep = 0;
        this.gradesSeries = Object.assign([], this.schoolClass.grade.series);
        this.headTeachers = Object.assign([], this.schoolClass.headTeachers);
        this.notations = Object.assign([], APP_CONSTANTS.SCHOOL_CLASSES_NOTATIONS);
        this.schoolClassForm = this.fb.group({
            name: new FormControl(this.schoolClass.name, [Validators.required]),
            customName: new FormControl(this.schoolClass.customName),
            grade: new FormControl(this.schoolClass.grade, [Validators.required]),
            gradeSerie: new FormControl(this.schoolClass.gradeSerie),
            notation: new FormControl(this.schoolClass.notation, [Validators.required]),
            coursesStartDate: new FormControl(this.schoolClass.coursesStartDate, [Validators.required]),
            coursesEndDate: new FormControl(this.schoolClass.coursesEndDate),
            headTeachers: new FormControl(this.schoolClass.headTeachers, [Validators.required]),
            // councilsDates: new FormControl(this.schoolClass.councilsDates)
            councilsDates: this.fb.array([]),
            myInputs: this.fb.array([])
        });
        this.schoolClassStudentsForm = this.fb.group({
            search: null
        });
        const searchedStudentCondition = (value) => {
            return value && typeof value === 'string' && value.trim().length > 2;
        };
        this.schoolClassStudentsForm
            .get('search')
            .valueChanges
            .pipe(
                debounceTime(300),
                tap((value) => {
                    if (searchedStudentCondition(value)) {
                        return this.isLoading = true;
                    } else {
                        return null;
                    }
                }),
                switchMap((value) => {
                        let students;
                        if (searchedStudentCondition(value)) {
                            students = this.studentService.findByNamesLoginEmail(value.toString());
                        } else {
                            students = of(new HttpResponse<Student[]>());
                        }
                        return students
                            .pipe(
                                finalize(() => {
                                    return this.isLoading = false;
                                }),
                            );
                    }
                )
            )
            .subscribe((response: any) => {
                return this.filteredStudents = response.body;
            });
        if (!this.schoolClass.councilsDates.length) {
            this.addCouncilDate();
        } else {
            this.schoolClass.councilsDates.forEach((councilDate) => this.addCouncilDate(councilDate));
        }
    }

    addCouncilDate(date?): void {
        let councilDate = new Date();
        let councilTime = '00:00';
        if (date) {
            councilDate = new Date(date.substring(0, 10));
            councilTime = date.substring(11, 16);
        }
        const councilsDatesArray =
            <FormArray>this.schoolClassForm.controls['councilsDates'];
        councilsDatesArray.push(this.fb.group({
            date: [councilDate],
            time: [councilTime]
        }));
    }

    removeCouncilDate(i) {
        const councilsDatesArray =
            <FormArray>this.schoolClassForm.controls['councilsDates'];
        councilsDatesArray.removeAt(i);
    }

    resetMessages() {
        this.schoolClassCreateConfirmation = false;
        this.creationError = false;
        this.creationErrorMsg = '';
        if (this.schoolClassStudentsForm.controls['search'].value) {
            this.filteredStudents = [];
            this.schoolClassStudentsForm.controls['search'].setValue('');
        }
    }

    createOrUpdateSchoolClass() {
        if (this.schoolClassForm.valid && this.schoolClassStudentsForm.valid) {
            this.schoolClass.update(this.schoolClassForm, [...this.searchedStudents]);
            if (this.routeState === 'edit') {
                this.schoolClassService.update(this.schoolClass).subscribe(
                    () => {
                        this.saveConfirmation();
                    },
                    () => {
                        this.saveError();
                    }
                );
            } else {
                this.schoolClassService.create(this.schoolClass).subscribe(
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
        this.notificationService.addToast('schoolClass.new.form.toast-save-title',
            'schoolClass.new.form.toast-save',
            'success');
    }

    private saveError() {
        this.notificationService.addToast('schoolClass.new.form.toast-save-error-title',
            'schoolClass.new.form.toast-save-error',
            'error');
    }

    goBack() {
        this.location.back();
    }

    compareGradesFn(grade1: Grade, grade2: Grade): boolean {
        return grade1 && grade2 && grade1.id === grade2.id;
    }

    compareGradeSeriesFn(gradeSerie1: GradeSerie, gradeSerie2: GradeSerie): boolean {
        return gradeSerie1.name === gradeSerie2.name;
    }

    onGradesChange($event) {
        this.gradesSeries = $event.value.series;
        this.teacherService.findByGrade($event.value.id)
            .subscribe((res) => {
                this.headTeachers = res.body;
            });
    }

    isGradeSeriesSelectDisabled() {
        const grade = this.schoolClassForm.controls.grade.value;
        const disabled = !grade || !grade.series || !grade.series.length;
        if (disabled) {
            this.schoolClassForm.controls.gradeSerie.setValidators(null);
        } else {
            this.schoolClassForm.controls.gradeSerie.setValidators([Validators.required]);
        }
        return disabled;
    }

    compareHeaderTeachersFn(headerTeacher1: Teacher, headerTeacher2: Teacher) {
        return headerTeacher1.id === headerTeacher2.id;
    }

    enterStep(index) {
        this.currentStep = index;
    }

    displaySearchStudentsFn(student: Student) {
        if (student) {
            return student.firstName + ' ' + student.lastName;
        }
    }

    onSearchStudentSelect(student) {
        if (!this.searchedStudents.filter((fstudent) => {
            return fstudent.id === student.id;
        }).length) {
            this.searchedStudents.push(student);
            this.filteredStudents = [];
        }
    }

    removeStudentFromSchoolClass(id: string) {
        this.searchedStudents.splice(this.searchedStudents.findIndex((student) => student.id === id), 1);
    }
}
