export class Time {
    constructor(
        public hour: string,
        public minutes: string,
        public seconds: string,
        public partOfDay: string,
    ) {
    }

    static equals(time1: Time, time2: Time) {
        return time1.hour === time2.hour
            && time1.minutes === time2.minutes
            && time1.seconds === time2.seconds
            && time1.partOfDay === time2.partOfDay;
    }
}
