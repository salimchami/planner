import {Injectable} from '@angular/core';
import {WEEKDAYS} from '../../../app.constants';
import {JhiLanguageHelper} from '../..';
import {Lesson, Subject, Timeslot} from '../../model';
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

    populateUniqueHalfTimeSlots(timeSlots: Array<Timeslot>) {
        const halfTimeSlots = timeSlots.filter((timeSlot) => timeSlot.half);
        const groupedHalfTimeSlots = DataHelper.arrayGroupBy(halfTimeSlots, (timeslot: Timeslot) => {
            return [timeslot.day, timeslot.startTime.hour, timeslot.endTime.hour];
        });
        for (const groupedHalfTimeSlot of groupedHalfTimeSlots) {
            if (groupedHalfTimeSlot.length !== 2) {
                const timeSlotToCopy: Timeslot = Object.assign({}, groupedHalfTimeSlot[0]);
                const timeSlotToAdd = new Timeslot('', '', false,
                    timeSlotToCopy.startTime, timeSlotToCopy.endTime, '', '',
                    timeSlotToCopy.day, timeSlotToCopy.date, false, true);
                timeSlots.push(timeSlotToAdd);
            }
        }
        return timeSlots;
    }

    convertTimeSlotsToFullCalendarEvents(clientFirstDayName: string, timeSlots: Array<Timeslot>, translate: boolean): Array<any> {
        timeSlots = this.populateUniqueHalfTimeSlots(timeSlots);
        return timeSlots.map((timeSlot: Timeslot, index) => {
            timeSlot.date = this.dateBasedOnTodayAndClientFirstDayFor(timeSlot.day.name, clientFirstDayName, new Date());
            const start: Date = new Date(timeSlot.date.getFullYear(), timeSlot.date.getMonth(), timeSlot.date.getDate(),
                +timeSlot.startTime.hour, +timeSlot.startTime.minutes, +timeSlot.startTime.seconds);
            const end: Date = new Date(timeSlot.date.getFullYear(), timeSlot.date.getMonth(), timeSlot.date.getDate(),
                +timeSlot.endTime.hour, +timeSlot.endTime.minutes, +timeSlot.endTime.seconds);
            let eventTile = '';
            if (translate) {
                this.languageHelper.translate(timeSlot.title).subscribe((title) => {
                    eventTile = title;
                });
            } else {
                eventTile = timeSlot.title;
            }
            return {
                title: eventTile,
                timeSlot,
                start,
                end,
                color: {primary: this.defaultEventBorderColor(), secondary: timeSlot.bgColor},
                meta: {
                    id: index
                },
                fontColor: timeSlot.fontColorCssClass,
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

}
