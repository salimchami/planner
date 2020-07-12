import {SchoolClass} from './schoolclass.model';
import {Lesson} from './lesson.model';

export class SchoolClassTimetable {

    constructor(
        public id?: string,
        public staticTimeTable?: Array<Lesson>,
        public events?: Array<Lesson>,
        public lastGenerationDate?: Date,
        public solverStatus?: string,
        public schoolClass?: SchoolClass,
    ) {
    }
}
