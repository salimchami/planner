"use strict";
exports.__esModule = true;
var SchoolRoom = /** @class */ (function () {
    function SchoolRoom(clientId, name, type, surface, seats, closed, distances, timetable, comment, longitude, latitude, id) {
        this.clientId = clientId;
        this.name = name;
        this.type = type;
        this.surface = surface;
        this.seats = seats;
        this.closed = closed;
        this.distances = distances;
        this.timetable = timetable;
        this.comment = comment;
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
    }
    SchoolRoom.prototype.update = function (name, type, surface, seats, closed, distances, timetable, comment, longitude, latitude) {
        this.name = name;
        this.type = type;
        this.surface = surface;
        this.seats = seats;
        this.closed = closed;
        this.distances = distances;
        this.timetable = timetable;
        this.comment = comment;
        this.longitude = longitude;
        this.latitude = latitude;
        return this;
    };
    SchoolRoom.prototype.convert = function (json) {
        this.clientId = json.clientId;
        this.id = json.id;
        this.name = json.name;
        this.type = json.type;
        this.surface = json.surface;
        this.seats = json.seats;
        this.closed = json.closed;
        this.distances = json.distances;
        this.timetable = json.timetable;
        this.comment = json.comment;
        this.longitude = json.longitude;
        this.latitude = json.latitude;
        return this;
    };
    return SchoolRoom;
}());
exports.SchoolRoom = SchoolRoom;
//# sourceMappingURL=school-room.model.js.map