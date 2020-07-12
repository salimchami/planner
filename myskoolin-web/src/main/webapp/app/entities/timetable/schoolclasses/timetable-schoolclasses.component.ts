import {Component, OnInit, OnDestroy, ViewChild, TemplateRef} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Client, Grade, Lesson, SchoolClass, SchoolRoom, Subject, Teacher, Timeslot} from '../../../shared/model';
import {CalendarHelper} from '../../../shared/services/utils/calendar-helper.service';
import {SchoolClassTimetable} from '../../../shared/model/school-class-timetable.model';
import {CalendarView} from 'angular-calendar';
import {DateTimeHelper} from '../../../shared/services/utils/date-time-helper.service';
import {TimetableService} from '../../../shared/services/timetable.service';

@Component({
    selector: 'jhi-timetables-schoolclasses',
    templateUrl: './timetable-schoolclasses.component.html',
    styles: [`
        :host >>> .popover {
            width: 900px;
            height: 200px;
        }

        .schoolRoom-timeSlot {
            font-weight: bold;
            font-size: small;
        }
    `]
})
export class TimetableSchoolClassesComponent implements OnInit, OnDestroy {
    @ViewChild('calendar', {}) modalContent: TemplateRef<any>;
    view: CalendarView = CalendarView.Month;
    viewDate: Date = new Date();
    schoolClasses: Array<SchoolClass>;
    schoolClassesByGrade: Array<SchoolClass>;
    schoolClassesStaticTimeSlots: Array<any>;
    schoolClass: SchoolClass;
    schoolClassTimeTable: SchoolClassTimetable;
    client: Client;
    grades: Array<Grade>;
    grade: Grade;
    subjects: Array<Subject>;
    timetableValidations: Array<any>;
    schoolRooms: Array<SchoolRoom>;
    teachers: Array<Teacher>;

    constructor(
        private route: ActivatedRoute,
        private calendarHelper: CalendarHelper,
        private dateTimeHelper: DateTimeHelper,
        private timetableService: TimetableService,
    ) {
    }

    ngOnInit() {
        this.schoolClassesStaticTimeSlots = [];
        this.timetableValidations = [];
        this.client = this.route.snapshot.data['client'];
        this.subjects = this.route.snapshot.data['subjects'];
        this.schoolClasses = this.route.snapshot.data['schoolClasses'];
        this.grades = this.route.snapshot.data['grades'];
        this.schoolRooms = this.route.snapshot.data['schoolRooms'];
        this.teachers = this.route.snapshot.data['teachers'];
    }

    ngOnDestroy() {
    }

    gradeChange(grade: Grade) {
        this.schoolClassesStaticTimeSlots = [];
        this.schoolClassesByGrade = this.schoolClasses.filter((schoolClass) => schoolClass.grade.id === grade.id);
    }

    schoolClassChange(schoolClass: SchoolClass) {
        const timeSlots: Array<Timeslot> = this.schoolClassTimeTable.staticTimeTable.map((lesson) => lesson.timeSlot);
        if (this.schoolClassTimeTable != null && typeof this.schoolClassTimeTable !== 'undefined') {
            this.schoolClassesStaticTimeSlots = this.calendarHelper.convertTimeSlotsToFullCalendarEvents(
                this.client.timeTableOptions.firstWeekDay.name,
                timeSlots, false);
            this.timetableValidations = this.calendarHelper.timetableValidations(this.schoolClassTimeTable.staticTimeTable, this.subjects);
        }
    }

    timetableIsValid() {
        return this.timetableValidations.reduce((sum, next) => sum && next.valid, true);
    }

    schoolRoomNameFromLesson(lesson: Lesson) {
        const schoolRooms = this.schoolRooms.filter((schoolRoom) => schoolRoom.id === lesson.schoolRoom.id);
        if (!!schoolRooms.length) {
            return schoolRooms[0].name;
        }
    }

    teacherFullNameFromTimeSlot(lesson: Lesson) {
        if (lesson.teacher) {
            const teachers = this.teachers.filter((teacher) => lesson.teacher.id === teacher.id);
            if (!!teachers.length) {
                return teachers[0].firstName + ' ' + teachers[0].lastName;
            }
        }
        return '';
    }
}
