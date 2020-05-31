import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JhiLanguageService} from 'ng-jhipster';
import {LANGUAGES} from '../..';
import {DATES_FORMAT, DATES_TIME_FORMAT} from '../../constants/date.constants';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

@Injectable()
export class DateTimeHelper {

    constructor(private languageService: JhiLanguageService) {
    }

    dateFormat() {
        switch (this.languageService.currentLang) {
            case LANGUAGES.FR:
                return DATES_FORMAT.FR;
            case LANGUAGES.EN:
                return DATES_FORMAT.EN;
        }
    }

    dateTimeFormat() {
        switch (this.languageService.currentLang) {
            case LANGUAGES.FR:
                return DATES_TIME_FORMAT.FR;
            case LANGUAGES.EN:
                return DATES_TIME_FORMAT.EN;
        }
    }

    displayHoursAndMinutes(minutes: number): string {
        return Math.floor((minutes / 60)) + ' h ' + (minutes % 60) + ' mn';
    }

    formatHourAndMinutes(date: Date) {
        return this.twoDigitTimeFormat(date.getHours().toString(), date.getMinutes().toString());
    }

    timestamp(date: Date) {
        return date.getFullYear().toString() + date.getMonth().toString() + date.getDay().toString()
            + date.getHours().toString() + date.getMinutes().toString() + date.getSeconds().toString()
            + date.getMilliseconds().toString();
    }

    twoDigitTimeFormat(hour: string, minutes: string) {
        const finalHour = hour.toString().length === 1 ? `0${hour}` : hour;
        const finalMinutes = minutes.toString().length === 1 ? `0${minutes}` : minutes;
        return finalHour + ':' + finalMinutes;
    }
}
