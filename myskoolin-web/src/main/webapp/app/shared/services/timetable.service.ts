import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';

import {createRequestOption} from './utils/request-util';
import {map} from 'rxjs/operators';
import {SchoolClassTimetable} from 'app/shared/model/school-class-timetable.model';

export type EntityResponseType = HttpResponse<SchoolClassTimetable>;

@Injectable()
export class TimetableService {

    private resourceUrl = SERVER_API_URL + 'api/timetables';

    constructor(private http: HttpClient) {
    }

    generateTimetables(req?: any): Observable<HttpResponse<any>> {
        const options = createRequestOption(req);
        return this.http.post(this.resourceUrl + '/solve?timestamp=',
            {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<any>) => res));
    }

    solverStatus(schoolClassId: string): Observable<HttpResponse<string>> {
        return this.http.get<string>(`${this.resourceUrl}/solve/status/${schoolClassId}`, {observe: 'response'})
            .pipe(map((res: HttpResponse<string>) => res));
    }

    query(req?: any): Observable<HttpResponse<SchoolClassTimetable[]>> {
        const options = createRequestOption(req);
        return this.http.get<SchoolClassTimetable[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<SchoolClassTimetable[]>) => this.convertArrayResponse(res)));
    }

    private convertArrayResponse(res: HttpResponse<SchoolClassTimetable[]>): HttpResponse<SchoolClassTimetable[]> {
        const jsonResponse: SchoolClassTimetable[] = res.body;
        const body: SchoolClassTimetable[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Timetable.
     */
    private convertItemFromServer(schoolClassTimeTable: SchoolClassTimetable): SchoolClassTimetable {
        return Object.assign({}, schoolClassTimeTable);
    }

}
