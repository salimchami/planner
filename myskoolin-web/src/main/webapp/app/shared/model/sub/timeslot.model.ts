export class Timeslot {

    constructor(
        public title?: string,
        public comment?: string,
        public canceled?: boolean,
        public startTime?: string,
        public endTime?: string,
        public bgColor?: string,
        public fontColorCssClass?: string,
        public day?: string,
        public date?: Date,
        public autoAlterable?: boolean,
        public half?: boolean,
    ) {
    }
}
