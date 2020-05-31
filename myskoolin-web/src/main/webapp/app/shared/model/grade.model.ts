import {BaseEntity} from '../../shared';
import {GradeSerie} from './grade-serie.model';
import {TimeTableOptions} from './sub/time-table-options.model';
import {Subject} from './subject.model';

export class Grade implements BaseEntity {

    public static sort(grades: Array<Grade>) {
        return grades.sort((grade1, grade2) => (grade1.name > grade2.name) ? 1 : ((grade2.name > grade1.name) ? -1 : 0));
    }

    constructor(
        public clientId?: any,
        public name?: string,
        public order?: number,
        public notation?: any,
        public diminutive?: string,
        public maxMinutesPerDay?: number,
        public series?: Array<GradeSerie>,
        public timeTableOptions?: TimeTableOptions,
        public subjects?: Array<Subject>,
        public id?: string,
    ) {
        this.subjects = [];
        this.series = [];
    }

    public update(id: string,
                  name?: string,
                  order?: number,
                  notation?: any,
                  diminutive?: string,
                  maxMinutesPerDay?: number,
                  series?: Array<GradeSerie>,
                  timeTableOptions?: TimeTableOptions
    ) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.notation = notation;
        this.diminutive = diminutive;
        this.maxMinutesPerDay = maxMinutesPerDay;
        this.series = series;
        this.timeTableOptions = timeTableOptions;
        return this;
    }

    public convert(json: any) {
        this.clientId = json.clientId;
        this.id = json.id;
        this.name = json.name;
        this.order = json.order;
        this.notation = json.notation;
        this.diminutive = json.diminutive;
        this.maxMinutesPerDay = json.maxMinutesPerDay;
        this.series = json.series;
        this.timeTableOptions = json.timeTableOptions;
        return this;
    }
}
