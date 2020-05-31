import {DateTimeHelper} from './date-time-helper.service';

export class ArraysHelper {

    private static sortDirection(direction: string) {
        if (direction === 'asc') {
            return {
                upper: 1,
                lower: -1
            };
        } else if (direction === 'desc') {
            return {
                upper: -1,
                lower: 1
            };
        }
    }

    static sortArray(array: any, field: string, direction: string): Array<any> {
        if (!Array.isArray(array)) {
            return;
        }
        if (direction === undefined) {
            direction = 'asc';
        }
        const directionNumbers = ArraysHelper.sortDirection(direction);
        array.sort((a: any, b: any) => {
            if (a[field] < b[field]) {
                return directionNumbers.lower;
            } else if (a[field] > b[field]) {
                return directionNumbers.upper;
            } else {
                return 0;
            }
        });
        return array;

    }

    constructor() {
    }
}
