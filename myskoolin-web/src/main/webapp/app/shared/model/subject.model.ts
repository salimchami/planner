import {BaseEntity} from '../../shared';
import {Grade} from './grade.model';
import {GradeSerie} from './grade-serie.model';

class PreferredPartsOfDays {
    constructor(
        public day: any,
        public partsOfDay: Array<any>
    ) {
    }
}

export class Subject implements BaseEntity {

    public static sort(subjects: Array<Subject>): Array<Subject> {
        return subjects.sort((subject1, subject2) => (subject1.name > subject2.name) ? 1 : ((subject2.name > subject1.name) ? -1 : 0));
    }

    constructor(
        public id?: string,
        public clientId?: string,
        public name?: string,
        public customName?: string,
        public grade?: Grade,
        public gradeSerie?: GradeSerie,
        public coefficient?: number,
        public foreignLanguage?: boolean,
        public minMinutesPerDay?: number,
        public maxMinutesPerDay?: number,
        public minutesPerWeek?: number,
        public coursesFrequencyPerWeek?: number,
        public color?: string,
        public bgColor?: string,
        public preferredPartsOfDaysInTimetables?: Array<PreferredPartsOfDays>,
        public schoolRoomsTypes?: Array<any>,
    ) {
    }

    update(values: any, clientId?: string, id?: any) {
        if (id != null && typeof id !== 'undefined') {
            this.id = id;
        } else {
            this.id = values.id;
        }

        this.name = values.name;
        this.clientId = clientId;
        this.customName = values.customName;
        this.grade = values.grade;
        this.gradeSerie = values.gradeSerie;
        this.coefficient = values.coefficient;
        this.foreignLanguage = values.foreignLanguage;
        this.maxMinutesPerDay = values.maxMinutesPerDay;
        this.minMinutesPerDay = values.minMinutesPerDay;
        this.minutesPerWeek = values.minutesPerWeek;
        this.coursesFrequencyPerWeek = values.coursesFrequencyPerWeek;
        this.color = values.color;
        this.bgColor = values.bgColor;
        this.preferredPartsOfDaysInTimetables = values.preferredPartsOfDaysInTimetables;
        this.schoolRoomsTypes = values.schoolRoomsTypes;
    }
}
