import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';

import {Grade} from '../model';
import {createRequestOption} from './utils/request-util';
import {map} from 'rxjs/operators';

export type EntityResponseType = HttpResponse<Grade>;
export type EntityResponseArrayType = HttpResponse<Array<Grade>>;

@Injectable()
export class GradeService {

    private resourceUrl = SERVER_API_URL + 'api/grades';

    constructor(private http: HttpClient) {
    }

    create(grades: Array<Grade>, grade: Grade): Observable<EntityResponseType> {
        const gradeCopy = this.convert(grade);
        const gradesCopy = this.convertArray(grades);
        gradeCopy.order = grades.length + 1;
        gradesCopy.push(gradeCopy);
        return this.http.post<Grade>(this.resourceUrl, gradesCopy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    update(grades: Array<Grade>, grade: Grade): Observable<EntityResponseArrayType> {
        const gradesCopy: Array<Grade> = grades.map((gradeMap) => this.convert(gradeMap));
        const gradeCopy = this.convert(grade);
        gradesCopy.map((g, i) => {
            if (g.id === gradeCopy.id) {
                gradeCopy.order = gradesCopy[i].order;
                gradesCopy[i] = gradeCopy;
            }
        });
        return this.http.put<Array<Grade>>(this.resourceUrl, gradesCopy, {observe: 'response'})
            .pipe(map((res: EntityResponseArrayType) => res));
    }

    find(name: string): Observable<EntityResponseType> {
        return this.http.get<Grade>(`${this.resourceUrl}/${name}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    query(req?: any): Observable<HttpResponse<Grade[]>> {
        const options = createRequestOption(req);
        return this.http.get<Grade[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<Grade[]>) => this.convertArrayResponse(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Grade = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Grade[]>): HttpResponse<Grade[]> {
        const jsonResponse: Grade[] = res.body;
        const body: Grade[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Grade.
     */
    private convertItemFromServer(grade: Grade): Grade {
        return Object.assign({}, grade);
    }

    /**
     * Convert a Grade to a JSON which can be sent to the server.
     */
    private convert(grade: Grade): Grade {
        return Object.assign({}, grade);
    }

    private convertArray(grades: Array<Grade>): Array<Grade> {
        return Object.assign([], grades);
    }

}
