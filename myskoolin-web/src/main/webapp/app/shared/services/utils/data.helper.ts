import {DateTimeHelper} from './date-time-helper.service';
import {el} from '@angular/platform-browser/testing/src/browser_util';
import {Injectable} from '@angular/core';

export class DataHelper {

    static arrayGroupBy(array, f) {
        const groups = {};
        array.forEach(function(o) {
            const group = JSON.stringify(f(o));
            groups[group] = groups[group] || [];
            groups[group].push(o);
        });
        return Object.keys(groups).map(function(group) {
            return groups[group];
        });
    }

    arrayToString(array: Array<any>): string {
        let result = '';
        array.forEach((value, index) => {
            switch (index) {
                case 0:
                    result = value;
                    break;
                case 1:
                    result = result + ' - ' + value;
                    break;
            }
        });
        return result;
    }

    constructor() {
    }
}
