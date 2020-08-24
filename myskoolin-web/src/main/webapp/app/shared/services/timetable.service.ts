import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';

import {createRequestOption} from './utils/request-util';
import {map} from 'rxjs/operators';
import {SchoolClassTimetable} from 'app/shared/model/school-class-timetable.model';

export type EntityResponseType = HttpResponse<SchoolClassTimetable>;
export type EntityResponseNumberType = HttpResponse<number>;
export type EntityResponseDateType = HttpResponse<Date>;

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

    find(schoolClassId: string): Observable<EntityResponseType> {
        return this.http.get<SchoolClassTimetable>(`${this.resourceUrl}/${schoolClassId}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SchoolClassTimetable = this.convertItemFromServer(res.body);
        return res.clone({body});
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

    countTimeTables(): Observable<EntityResponseNumberType> {
        return this.http.get<number>(`${this.resourceUrl}/count`, {observe: 'response'})
            .pipe(map((res: EntityResponseNumberType) => res));
    }

    lastGenerationDate(): Observable<EntityResponseDateType> {
        return this.http.get<Date>(`${this.resourceUrl}/lastGenerationDate`, {observe: 'response'})
            .pipe(map((res: EntityResponseDateType) => res));
    }
}
