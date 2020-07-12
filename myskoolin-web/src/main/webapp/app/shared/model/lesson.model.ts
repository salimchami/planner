import {SchoolRoom} from './school-room.model';
import {Subject} from './subject.model';
import {Teacher} from './teacher.model';
import {Timeslot} from './sub';
import {SchoolClass} from './schoolclass.model';

export class Lesson {
    constructor(
        public id?: string,
        public schoolRoom?: SchoolRoom,
        public subject?: Subject,
        public teacher?: Teacher,
        public timeSlot?: Timeslot,
        public schoolClass?: SchoolClass,
    ) {
    }
}
