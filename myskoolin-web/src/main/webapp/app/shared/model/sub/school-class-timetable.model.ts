import {SchoolClassTimeSlot} from './school-class-timeslot.model';

export class SchoolClassTimetable {

    constructor(
        public timeslots?: Array<SchoolClassTimeSlot>,
        public staticTimeTable?: Array<SchoolClassTimeSlot>,
        public events?: Array<SchoolClassTimeSlot>,
        public lastGenerationDate?: Date,
    ) {
    }
}
