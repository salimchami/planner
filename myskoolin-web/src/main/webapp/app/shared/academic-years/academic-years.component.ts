import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {AcademicYear, AcademicYears} from './academic-years.model';
import {AcademicYearsService} from './academic-years.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
    selector: 'jhi-academic-years',
    templateUrl: 'academic-years.component.html'
})
export class JhiAcademicYearsComponent implements OnInit, OnDestroy {
    acYears: AcademicYears;
    displaySelect: boolean;
    @Input() selectedDate: AcademicYear;
    @Input() label: string;
    academicYearForm: FormGroup;

    constructor(private academicYearsService: AcademicYearsService) { }

    ngOnInit() {
        this.displaySelect = false;
        this.academicYearsService.get().subscribe(
            (res) => {
                this.acYears = new AcademicYears(res.body.current, res.body.next);
                this.selectedDate = this.acYears.current;
            });
        this.academicYearForm = new FormGroup({
            email: new FormControl('', [Validators.email, Validators.required])
        });

    }

    ngOnDestroy() {
    }

    discardChangeDates() {
        this.displaySelect = false;
    }

    changeDates(value) {
        this.displaySelect = value;
    }
}
