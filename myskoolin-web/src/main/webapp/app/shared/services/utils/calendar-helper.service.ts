import {Injectable} from '@angular/core';
import {WEEKDAYS} from '../../../app.constants';
import {JhiLanguageHelper} from '../..';
import {Lesson, Subject, Time, Timeslot} from '../../model';
import {DateTimeHelper} from './date-time-helper.service';
import {DataHelper} from './data.helper';

@Injectable()
export class CalendarHelper {

    constructor(private languageHelper?: JhiLanguageHelper,
                private dateTimeHelper?: DateTimeHelper
    ) {
    }

    defaultEventBorderColor() {
        return '#3B4650';
    }

    populateUniqueHalfTimeSlots(lessons: Array<Lesson>) {
        const halfTimeSlots = lessons.filter((lesson) => lesson.timeSlot.half);
        const groupedHalfLessonTimeSlots = DataHelper.arrayGroupBy(halfTimeSlots, (lesson: Lesson) => {
            return [lesson.timeSlot.day, lesson.timeSlot.startTime.hour, lesson.timeSlot.endTime.hour];
        });
        for (const groupedHalfLessonTimeSlot of groupedHalfLessonTimeSlots) {
            if (groupedHalfLessonTimeSlot.length !== 2) {
                const lessonToCopy: Lesson = Object.assign({}, groupedHalfLessonTimeSlot[0]);
                const timeSlotToAdd = new Timeslot('', '', false,
                    lessonToCopy.timeSlot.startTime, lessonToCopy.timeSlot.endTime, '', '',
                    lessonToCopy.timeSlot.day, lessonToCopy.timeSlot.date, false, true);
                const lessonToAdd = new Lesson(groupedHalfLessonTimeSlot.id, groupedHalfLessonTimeSlot.schoolRoom, groupedHalfLessonTimeSlot.subject,
                    groupedHalfLessonTimeSlot.teacher, timeSlotToAdd);
                lessons.push(lessonToAdd);
            }
        }
        return lessons;
    }

    convertTimeSlotsToFullCalendarEvents(clientFirstDayName: string, lessons: Array<Lesson>, translate: boolean): Array<any> {
        lessons = this.populateUniqueHalfTimeSlots(lessons);
        lessons = this.mergeSameSubjectsInTimeSlots(lessons);
        // lessons = this.mergeSameSubjectsInOverlappedTimeSlots(lessons);
        return lessons.map((lesson: Lesson, index) => {
            lesson.timeSlot.date = this.dateBasedOnTodayAndClientFirstDayFor(lesson.timeSlot.day, clientFirstDayName, new Date());
            const start: Date = new Date(lesson.timeSlot.date.getFullYear(), lesson.timeSlot.date.getMonth(), lesson.timeSlot.date.getDate(),
                +lesson.timeSlot.startTime.hour, +lesson.timeSlot.startTime.minutes, +lesson.timeSlot.startTime.seconds);
            const end: Date = new Date(lesson.timeSlot.date.getFullYear(), lesson.timeSlot.date.getMonth(), lesson.timeSlot.date.getDate(),
                +lesson.timeSlot.endTime.hour, +lesson.timeSlot.endTime.minutes, +lesson.timeSlot.endTime.seconds);
            let eventTile = '';
            if (translate) {
                this.languageHelper.translate(lesson.timeSlot.title).subscribe((title) => {
                    eventTile = title;
                });
            } else {
                eventTile = lesson.timeSlot.title;
            }
            return {
                title: eventTile,
                lesson,
                start,
                end,
                color: {primary: this.defaultEventBorderColor(), secondary: lesson.timeSlot.bgColor},
                meta: {
                    id: index
                },
                fontColor: lesson.timeSlot.fontColorCssClass,
                resizable: undefined
            };
        });
    }

    dateBasedOnTodayAndClientFirstDayFor(dayName: string, clientFirstDayName: string, today: Date) {
        const todayIndex = today.getDay();
        const searchedDayIndex = this.dayIndex(dayName);
        let diffDays;
        if (todayIndex > searchedDayIndex) {
            diffDays = searchedDayIndex - todayIndex;
        } else if (todayIndex === 0 && this.dayIndex(clientFirstDayName) === 1) {
            diffDays = searchedDayIndex - 7;
        } else {
            diffDays = searchedDayIndex - todayIndex;
        }
        today.setDate(today.getDate() + diffDays);
        return today;
    }

    getFirstDayOfWeek(date, from) {
        from = from || 'sunday';
        const index = WEEKDAYS.indexOf(from);
        const start = index >= 0 ? index : 0;

        const d = new Date(date);
        const day = d.getDay();
        const diff = d.getDate() - day + (start > day ? start - 7 : start);
        d.setDate(diff);
        return d;
    }

    dayIndex(day: string) {
        let result;
        switch (day.toLowerCase()) {
            case 'monday':
                result = 1;
                break;
            case 'tuesday':
                result = 2;
                break;
            case 'wednesday':
                result = 3;
                break;
            case 'thursday':
                result = 4;
                break;
            case 'friday':
                result = 5;
                break;
            case 'saturday':
                result = 6;
                break;
            case 'sunday':
                result = 0;
                break;
        }
        return result;
    }

    timetableValidations(lessons: Array<Lesson>, subjects: Array<Subject>) {
        const validations = [];
        subjects.forEach((subject) => {
            const durationInMinutes = lessons
                .filter((lesson) => {
                    return lesson.subject.id === subject.id;
                })
                .reduce((sum, lesson) => {
                    return sum + this.timeSlotDurationInMinutes(lesson.timeSlot, new Date());
                }, 0);
            validations.push({
                subject,
                durationInMinutes,
                valid: subject.minutesPerWeek === durationInMinutes
            });
        });
        return validations;
    }

    timeSlotDurationInMinutes(timeSlot: Timeslot, now: Date): number {
        const startTime = new Date(now.getFullYear(), now.getMonth(), now.getDay(),
            +timeSlot.startTime.hour, +timeSlot.startTime.minutes, +timeSlot.startTime.seconds);
        const endTime = new Date(now.getFullYear(), now.getMonth(), now.getDay(),
            +timeSlot.endTime.hour, +timeSlot.endTime.minutes, +timeSlot.endTime.seconds);
        const result = (endTime.getTime() / 1000 / 60) - (startTime.getTime() / 1000 / 60);
        return timeSlot.half ? result / 2 : result;
    }

    mergeSameSubjectsInTimeSlots(lessons: Array<Lesson>) {
        return lessons.reduce((lessonsTmp: Array<Lesson>, currentLesson: Lesson) => {
            const adjacentLesson = lessonsTmp.find((lesson) => {
                const currentTimeSlot = currentLesson.timeSlot;
                const adjacentTimeSlot = lesson.timeSlot;
                const sameSchoolRooms = currentLesson.schoolRoom.id === lesson.schoolRoom.id;
                const sameTeachers = currentLesson.teacher.id === lesson.teacher.id;
                const sameSubjects = currentLesson.subject.id === lesson.subject.id;
                const sameDays = currentTimeSlot.day === adjacentTimeSlot.day;
                const sameEndAndStartTime = Time.equals(currentTimeSlot.startTime, adjacentTimeSlot.endTime);
                return sameSchoolRooms && sameTeachers && sameSubjects && sameDays && sameEndAndStartTime;
            });
            if (lessonsTmp.includes(adjacentLesson)) {
                lessonsTmp.push(
                    new Lesson(currentLesson.id, currentLesson.schoolRoom, currentLesson.subject,
                        currentLesson.teacher,
                        new Timeslot(currentLesson.timeSlot.title, currentLesson.timeSlot.comment, currentLesson.timeSlot.canceled,
                            adjacentLesson.timeSlot.startTime, currentLesson.timeSlot.endTime, currentLesson.timeSlot.bgColor,
                            currentLesson.timeSlot.fontColorCssClass, currentLesson.timeSlot.day, currentLesson.timeSlot.date,
                            currentLesson.timeSlot.autoAlterable, currentLesson.timeSlot.half)));
                lessonsTmp.splice(lessonsTmp.indexOf(adjacentLesson, 0), 1);
            } else {
                lessonsTmp.push(currentLesson);
            }
            return lessonsTmp;
        }, []);
    }
}
