import {Time} from './time.model';

export class Timeslot {
    constructor(
        public title?: string,
        public comment?: string,
        public canceled?: boolean,
        public startTime?: Time,
        public endTime?: Time,
        public bgColor?: string,
        public fontColorCssClass?: string,
        public day?: any,
        public date?: Date,
        public autoAlterable?: boolean,
        public half?: boolean,
    ) {
    }
}
