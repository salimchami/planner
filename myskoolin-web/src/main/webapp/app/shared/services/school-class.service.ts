import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';

import {createRequestOption} from './utils/request-util';
import {SchoolClass} from '../model';
import {map} from 'rxjs/operators';
import {DateTimeHelper} from '../services/utils/date-time-helper.service';

export type EntityResponseType = HttpResponse<SchoolClass>;
export type EntityResponseArrayType = HttpResponse<Array<SchoolClass>>;

@Injectable()
export class SchoolClassService {

    private resourceUrl = SERVER_API_URL + 'api/school-classes';

    constructor(private http: HttpClient, private dateTimeHelper: DateTimeHelper) {
    }

    create(schoolClass: SchoolClass): Observable<EntityResponseType> {
        const schoolClassCopy = this.convert(schoolClass);
        return this.http.post<SchoolClass>(this.resourceUrl, schoolClassCopy, {observe: 'response'});
    }

    update(schoolClass: SchoolClass): Observable<EntityResponseType> {
        const schoolClassCopy = this.convert(schoolClass);
        return this.http.put<SchoolClass>(this.resourceUrl, schoolClassCopy, {observe: 'response'});
    }

    find(name: string): Observable<EntityResponseType> {
        return this.http.get<SchoolClass>(`${this.resourceUrl}/${name}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    query(req?: any): Observable<HttpResponse<SchoolClass[]>> {
        const options = createRequestOption(req);
        return this.http.get<SchoolClass[]>(this.resourceUrl + '?timestamp=' + this.dateTimeHelper.timestamp(new Date()), {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<SchoolClass[]>) => this.convertArrayResponse(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SchoolClass = this.convertItemFromServer(res.body);
        return res.clone({body});
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
     * Convert a returned JSON object to SchoolClass.
     */
    private convertItemFromServer(schoolClass: SchoolClass): SchoolClass {
        return Object.assign(new SchoolClass(), schoolClass);
    }

    /**
     * Convert a SchoolClass to a JSON which can be sent to the server.
     */
    private convert(schoolClass: SchoolClass): SchoolClass {
        return Object.assign(new SchoolClass(), schoolClass);
    }

}
