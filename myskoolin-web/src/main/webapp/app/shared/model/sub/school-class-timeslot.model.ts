import {Subject, Timeslot, SchoolRoom} from './../';

export class SchoolClassTimeSlot extends Timeslot {

    constructor(
        public schoolRoomId?: string,
        public subjectId?: string,
        public teachersIds?: Array<string>,
    ) {
        super();
    }
}
