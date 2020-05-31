import {AcademicYear} from '../academic-years/academic-years.model';

export class Report {
    constructor(
        public title?: string,
        public subTitle?: string,
        public comment?: string,
        public appreciation?: string,
        public globalAppreciation?: string,
        public period?: string,
        public periodNumber?: number,
        public academicYear?: AcademicYear,
        public subjectId?: string,
        public averageScore?: number,
        public schoolClassAverageScore?: number,
        public schoolClassAverageHighScore?: number,
        public schoolClassAverageDownScore?: number,
    ) {
    }
}
