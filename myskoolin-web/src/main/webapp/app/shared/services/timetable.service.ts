import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';

import {createRequestOption} from './utils/request-util';
import {map} from 'rxjs/operators';
import {SchoolClassTimetable} from 'app/shared/model/sub/school-class-timetable.model';
import {SchoolClass} from '../model/';
import {DateTimeHelper} from './../services/utils/date-time-helper.service';

export type EntityResponseType = HttpResponse<SchoolClassTimetable>;

@Injectable()
export class TimetableService {

    private resourceUrl = SERVER_API_URL + 'api/timetables';

    constructor(private http: HttpClient, private dateTimeHelper: DateTimeHelper) {
    }

    generateTimetables(req?: any): Observable<HttpResponse<SchoolClass[]>> {
        const options = createRequestOption(req);
        return this.http.get<SchoolClass[]>(this.resourceUrl + '/generate?timestamp=' + this.dateTimeHelper.timestamp(new Date()),
            {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<SchoolClass[]>) =>
                this.convertArrayResponse(res)));
    }

    private convertArrayResponse(res: HttpResponse<SchoolClass[]>): HttpResponse<SchoolClass[]> {
        const jsonResponse: SchoolClass[] = res.body;
        const body: SchoolClass[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Timetable.
     */
    private convertItemFromServer(schoolClass: SchoolClass): SchoolClass {
        return Object.assign({}, schoolClass);
    }

    /**
     * Convert a Timetable to a JSON which can be sent to the server.
     */
    private convert(schoolClass: SchoolClass): SchoolClass {
        return Object.assign({}, schoolClass);
    }

    private convertArray(schoolClasses: Array<SchoolClass>): Array<SchoolClass> {
        return Object.assign([], schoolClasses);
    }

}
