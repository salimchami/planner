export class AcademicYears {
    current: AcademicYear;
    next: AcademicYear;

    constructor(
        current: AcademicYear,
        next: AcademicYear,
    ) {
        this.current = current;
        this.next = next;
    }
}

export class AcademicYear {
    startYear: number;
    endYear: number;
    startDateMin: Date;
    startDateMax: Date;
    endDate: Date;

    constructor(
        startYear: number,
        endYear: number,
        startDateMin: Date,
        startDateMax: Date,
        endDate: Date
    ) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.startDateMin = startDateMin;
        this.startDateMax = startDateMax;
        this.endDate = endDate;
    }
}
