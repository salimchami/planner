import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';

import {Student, Teacher} from '../model';
import {createRequestOption} from './utils/request-util';
import {JhiLanguageService} from 'ng-jhipster';
import {map} from 'rxjs/operators';

export type EntityResponseType = HttpResponse<Student>;
export type EntityResponseArrayType = HttpResponse<Array<Student>>;

@Injectable()
export class StudentService {

    private resourceUrl = SERVER_API_URL + 'api/students';

    constructor(private http: HttpClient,
                private languageService: JhiLanguageService) {
    }

    create(student: Student): Observable<EntityResponseType> {
        const studentCopy = this.convert(student);
        this.setLangKey(studentCopy);
        return this.http.post<Student>(this.resourceUrl, studentCopy, {observe: 'response'});
    }

    update(student: Student): Observable<EntityResponseArrayType> {
        const studentCopy = this.convert(student);
        this.setLangKey(studentCopy);
        return this.http.put<Array<Student>>(this.resourceUrl, studentCopy, {observe: 'response'});
    }

    find(name: string): Observable<EntityResponseType> {
        return this.http.get<Student>(`${this.resourceUrl}/${name}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    query(req?: any): Observable<HttpResponse<Student[]>> {
        const options = createRequestOption(req);
        return this.http.get<Student[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<Student[]>) => this.convertArrayResponse(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    findByNamesLoginEmail(search: string): Observable<HttpResponse<Student[]>> {
        return this.http.get<Student[]>(`${this.resourceUrl}/search/${search}`, {observe: 'response'})
            .pipe(map((res: HttpResponse<Student[]>) => this.convertArrayResponse(res)));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Student = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Student[]>): HttpResponse<Student[]> {
        const jsonResponse: Student[] = res.body;
        const body: Student[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    private setLangKey(studentCopy: Student) {
        if (!studentCopy.langKey) {
            this.languageService.getCurrent().then((key) => {
                studentCopy.langKey = key;
            });
        }
    }

    /**
     * Convert a returned JSON object to Student.
     */
    private convertItemFromServer(student: Student): Student {
        return Object.assign({}, student);
    }

    /**
     * Convert a Student to a JSON which can be sent to the server.
     */
    private convert(student: Student): Student {
        return Object.assign({}, student);
    }

    private convertArray(students: Array<Student>): Array<Student> {
        return Object.assign([], students);
    }
}
