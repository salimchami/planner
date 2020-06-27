import {BaseEntity} from '../../shared';
import {Grade} from './grade.model';
import {GradeSerie} from './grade-serie.model';
import {DailyBookTimeSlot} from './sub/daily-book-timeslot.model';
import {Teacher} from './teacher.model';
import {FormGroup} from '@angular/forms';
import {Student} from './student.model';
import {SchoolClassTimetable} from './sub/school-class-timetable.model';
import {User} from './user.model';
import {Subject} from './subject.model';

class TeachersBySubject {
    constructor(
        public subject: Subject,
        public teacherUsers: Array<Teacher>
    ) {
    }
}

export class SchoolClass implements BaseEntity {

    constructor(
        public id?: string,
        public name?: string,
        public customName?: string,
        public coursesStartDate?: Date,
        public coursesEndDate?: Date,
        public councilsDates?: Array<Date>,
        public headTeachers?: Array<string>,
        public grade?: Grade,
        public gradeSerie?: GradeSerie,
        public dailyBook?: Array<DailyBookTimeSlot>,
        public notation?: string,
        public deleted?: boolean,
        public students?: Array<Student>,
        public timetable?: SchoolClassTimetable,
        public teachersBySubjects?: Array<TeachersBySubject>
    ) {
        this.headTeachers = [];
        this.councilsDates = [];
        this.dailyBook = [];
        this.deleted = false;
        this.grade = new Grade();
        this.students = [];
        this.teachersBySubjects = [];
    }

    update(schoolClassUserForm: FormGroup, students: Array<Student>) {
        console.log('student : ' + students);
        this.students = students;
        this.name = schoolClassUserForm.controls.name.value;
        this.customName = schoolClassUserForm.controls.customName.value;
        this.coursesStartDate = schoolClassUserForm.controls.coursesStartDate.value;
        this.coursesEndDate = schoolClassUserForm.controls.coursesEndDate.value;
        this.councilsDates = [];
        schoolClassUserForm.controls.councilsDates.value.forEach((councilDate) => {
            const councilDateToSave = councilDate.date;
            const time = councilDate.time.split(':');
            councilDateToSave.setUTCHours(time[0], time[1]);
            this.councilsDates.push(councilDateToSave);
        });
        this.headTeachers = schoolClassUserForm.controls.headTeachers.value;
        this.grade = schoolClassUserForm.controls.grade.value;
        this.gradeSerie = schoolClassUserForm.controls.gradeSerie.value;
        this.notation = schoolClassUserForm.controls.notation.value;
        // this.teachersBySubjects =
    }
}
