import {Injectable} from '@angular/core';
import {WEEKDAYS} from '../../../app.constants';
import {JhiLanguageHelper} from '../..';
import {Lesson, Subject, Timeslot} from '../../model';

@Injectable()
export class CalendarHelper {

    constructor(private languageHelper?: JhiLanguageHelper
    ) {
    }

    defaultEventBorderColor() {
        return '#3B4650';
    }

    // populateUniqueHalfTimeSlots(lessons: Array<Lesson>) {
    //     const halfTimeSlots = lessons.filter((lesson) => lesson.timeSlot.half);
    //     const groupedHalfLessonTimeSlots = DataHelper.arrayGroupBy(halfTimeSlots, (lesson: Lesson) => {
    //         return [lesson.timeSlot.day, lesson.timeSlot.startTime.hour, lesson.timeSlot.endTime.hour];
    //     });
    //     for (const groupedHalfLessonTimeSlot of groupedHalfLessonTimeSlots) {
    //         if (groupedHalfLessonTimeSlot.length !== 2) {
    //             const lessonToCopy: Lesson = Object.assign({}, groupedHalfLessonTimeSlot[0]);
    //             const timeSlotToAdd = new Timeslot('', '', false,
    //                 lessonToCopy.timeSlot.startTime, lessonToCopy.timeSlot.endTime, '', '',
    //                 lessonToCopy.timeSlot.day, lessonToCopy.timeSlot.date, false, true);
    //             const lessonToAdd = new Lesson(groupedHalfLessonTimeSlot.id, groupedHalfLessonTimeSlot.schoolRoom, groupedHalfLessonTimeSlot.subject,
    //                 groupedHalfLessonTimeSlot.teacher, timeSlotToAdd);
    //             lessons.push(lessonToAdd);
    //         }
    //     }
    //     return lessons;
    // }

    convertTimeSlotsToFullCalendarEvents(clientFirstDayName: string, lessons: Array<Lesson>, translate: boolean): Array<any> {
        // lessons = this.populateUniqueHalfTimeSlots(lessons);
        lessons = this.mergeSameSubjectsInTimeSlots(lessons);
        // lessons = this.mergeSameSubjectsInOverlappedTimeSlots(lessons);
        return lessons.map((lesson: Lesson, index) => {
            const date = this.dateBasedOnTodayAndClientFirstDayFor(lesson.timeSlot.day, clientFirstDayName, new Date());
            return {
                title: this.eventTileFromLesson(translate, lesson),
                lesson,
                start: CalendarHelper.dateFromLesson(date, lesson.timeSlot.startTime),
                end: CalendarHelper.dateFromLesson(date, lesson.timeSlot.endTime),
                color: {primary: this.defaultEventBorderColor(), secondary: lesson.timeSlot.bgColor},
                meta: {
                    id: index
                },
                fontColor: lesson.timeSlot.fontColorCssClass,
                resizable: undefined
            };
        });
    }

    private static dateFromLesson(date: Date, time: string) {
        const hour = +time.substring(0, 2);
        const minutes = +time.substring(3, 5);
        const seconds = +time.substring(6);
        return new Date(date.getFullYear(), date.getMonth(), date.getDate(), hour, minutes, seconds);
    }

    private eventTileFromLesson(translate: boolean, lesson: Lesson) {
        if (translate) {
            this.languageHelper.translate(lesson.timeSlot.title).subscribe((title) => {
                return title;
            });
        } else {
            return lesson.timeSlot.title;
        }
    }

    dateBasedOnTodayAndClientFirstDayFor(day: any, clientFirstDayName: string, today: Date) {
        const todayIndex = today.getDay();
        const searchedDayIndex = this.dayIndex(day.name);
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
        switch (day) {
            case 'MONDAY':
                result = 1;
                break;
            case 'TUESDAY':
                result = 2;
                break;
            case 'WEDNESDAY':
                result = 3;
                break;
            case 'THURSDAY':
                result = 4;
                break;
            case 'FRIDAY':
                result = 5;
                break;
            case 'SATURDAY':
                result = 6;
                break;
            case 'SUNDAY':
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
        const startHour = +timeSlot.startTime.substring(0, 2);
        const startMinutes = +timeSlot.startTime.substring(3, 5);
        const startSeconds = +timeSlot.startTime.substring(6);
        const endHour = +timeSlot.endTime.substring(0, 2);
        const endMinutes = +timeSlot.endTime.substring(3, 5);
        const endSeconds = +timeSlot.endTime.substring(6);
        const startTime = new Date(now.getFullYear(), now.getMonth(), now.getDay(),
            startHour, startMinutes, startSeconds);
        const endTime = new Date(now.getFullYear(), now.getMonth(), now.getDay(),
            endHour, endMinutes, endSeconds);
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
                const sameEndAndStartTime = currentTimeSlot.startTime === adjacentTimeSlot.endTime;
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
