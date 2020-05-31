import {BaseEntity} from '../../shared';
// import {SchoolRoomDistance} from './sub/school-room-distance.model';
import {Timeslot} from './sub';

export class SchoolRoom implements BaseEntity {
    constructor(
        public clientId?: any,
        public name?: string,
        public type?: any,
        public surface?: string,
        public seats?: number,
        public closed?: boolean,
        // public distances?: Array<SchoolRoomDistance>,
        public timetable?: Array<Timeslot>,
        public comment?: string,
        public longitude?: string,
        public latitude?: string,
        public id?: string,
    ) {
    }

    public update(name: string,
                  type?: any,
                  surface?: string,
                  seats?: number,
                  closed?: boolean,
                  // distances?: Array<SchoolRoomDistance>,
                  timetable?: Array<Timeslot>,
                  comment?: string,
                  longitude?: string,
                  latitude?: string) {
        this.name = name;
        this.type = type;
        this.surface = surface;
        this.seats = seats;
        this.closed = closed;
        // this.distances = distances;
        this.timetable = timetable;
        this.comment = comment;
        this.longitude = longitude;
        this.latitude = latitude;
        return this;
    }

    public convert(json: any) {
        this.clientId = json.clientId;
        this.id = json.id;
        this.name = json.name;
        this.type = json.type;
        this.surface = json.surface;
        this.seats = json.seats;
        this.closed = json.closed;
        // this.distances = json.distances;
        this.timetable = json.timetable;
        this.comment = json.comment;
        this.longitude = json.longitude;
        this.latitude = json.latitude;
        return this;
    }
}
