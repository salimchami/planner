import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';

import {Subject} from '../model';
import {createRequestOption} from './utils/request-util';
import {map} from 'rxjs/operators';

export type EntityResponseType = HttpResponse<Subject>;
export type EntityResponseArrayType = HttpResponse<Array<Subject>>;

@Injectable()
export class SubjectService {

    private resourceUrl = SERVER_API_URL + 'api/subjects';

    constructor(private http: HttpClient) {
    }

    create(subject: Subject): Observable<EntityResponseType> {
        const subjectCopy = this.convert(subject);
        return this.http.post<Subject>(this.resourceUrl, subjectCopy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    update(subject: Subject): Observable<EntityResponseArrayType> {
        const subjectCopy = this.convert(subject);
        return this.http.put<Array<Subject>>(this.resourceUrl, subjectCopy, {observe: 'response'})
            .pipe(map((res: EntityResponseArrayType) => res));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<Subject>(`${this.resourceUrl}/${id}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    query(req?: any): Observable<HttpResponse<Subject[]>> {
        const options = createRequestOption(req);
        return this.http.get<Subject[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<Subject[]>) => this.convertArrayResponse(res)));
    }

    findByGradesIds(grades: any): Observable<EntityResponseArrayType> {
        return this.http.post<Array<Subject>>(`${this.resourceUrl}/by-grades`, grades.map((grade) => grade.id), {observe: 'response'})
            .pipe(map((res: EntityResponseArrayType) => res));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Subject = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Subject[]>): HttpResponse<Subject[]> {
        const jsonResponse: Subject[] = res.body;
        const body: Subject[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Subject.
     */
    private convertItemFromServer(subject: Subject): Subject {
        return Object.assign({}, subject);
    }

    /**
     * Convert a Subject to a JSON which can be sent to the server.
     */
    private convert(subject: Subject): Subject {
        return Object.assign({}, subject);
    }

    private convertArray(subjects: Array<Subject>): Array<Subject> {
        return Object.assign([], subjects);
    }
}
