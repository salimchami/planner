import {WeekDay} from './week-day.model';

export class CanteenRegistration {

    constructor(
        public recorded?: boolean,
        public breakfast?: boolean,
        public lunch?: boolean,
        public dinner?: boolean,
        public weekDays?: Array<WeekDay>,
        public subscriptionPeriodStart?: Date,
        public subscriptionPeriodEnd?: Date,
    ) {
    }
}
