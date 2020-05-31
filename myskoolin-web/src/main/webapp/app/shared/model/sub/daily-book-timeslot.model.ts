import {Timeslot} from './timeslot.model';
import {Subject} from '../subject.model';

export class DailyBookTimeSlot extends Timeslot {

    constructor(
        public subject?: Subject,
        // FIXME: change to Teacher type
        public teachers?: Array<any>,
        public targetDate?: Date
    ) {
        super();
    }
}
