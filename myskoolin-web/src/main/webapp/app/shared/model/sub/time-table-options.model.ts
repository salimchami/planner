import {Timeslot} from './timeslot.model';
import {WeekDay} from './week-day.model';

export class TimeTableOptions {
    constructor(
        public schoolRoomsDistances: boolean,
        public firstWeekDay: WeekDay,
        public surfaceMinPerStudent: number,
        public coursesStartTime: string,
        public coursesEndTime: string,
        public coursesTimeSlots: Array<Timeslot>,
        public extraActivities: Array<Timeslot>,
        public calendarTimelineDuration: number,
        public oneHourDuration: number
    ) {
    }

    convert(json: any) {
        this.schoolRoomsDistances = json.schoolRoomsDistances;
        this.firstWeekDay = json.firstWeekDay;
        this.surfaceMinPerStudent = json.firstWeekDay;
        this.coursesStartTime = json.firstWeekDay;
        this.coursesEndTime = json.coursesEndTime;
        this.extraActivities = json.extraActivities;
        this.coursesTimeSlots = json.coursesTimeSlots;
        this.oneHourDuration = json.oneHourDuration;
        this.calendarTimelineDuration = json.calendarTimelineDuration;
        return this;
    }
}
