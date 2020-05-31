import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';
import {Teacher} from '../model';
import {createRequestOption} from './utils/request-util';
import {JhiLanguageService} from 'ng-jhipster';
import {map} from 'rxjs/operators';

export type EntityResponseType = HttpResponse<Teacher>;
export type EntityResponseArrayType = HttpResponse<Array<Teacher>>;

@Injectable()
export class TeacherService {

    private resourceUrl = SERVER_API_URL + 'api/teachers';

    constructor(private http: HttpClient,
                private languageService: JhiLanguageService) {
    }

    create(teacher: Teacher): Observable<EntityResponseType> {
        const teacherCopy = this.convert(teacher);
        this.setLangKey(teacherCopy);
        return this.http.post<Teacher>(this.resourceUrl, teacherCopy, {observe: 'response'});
    }

    update(teacher: Teacher): Observable<EntityResponseType> {
        const teacherCopy = this.convert(teacher);
        this.setLangKey(teacherCopy);
        return this.http.put<Teacher>(this.resourceUrl, teacherCopy, {observe: 'response'});
    }

    find(name: string): Observable<EntityResponseType> {
        return this.http.get<Teacher>(`${this.resourceUrl}/${name}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    query(req?: any): Observable<HttpResponse<Teacher[]>> {
        const options = createRequestOption(req);
        return this.http.get<Teacher[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<Teacher[]>) => this.convertArrayResponse(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    findByGrade(gradeId: string): Observable<HttpResponse<Teacher[]>> {
        return this.http.get<Teacher[]>(`${this.resourceUrl}/byGrade/${gradeId}`, {observe: 'response'})
            .pipe(map((res: HttpResponse<Teacher[]>) => this.convertArrayResponse(res)));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Teacher = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Teacher[]>): HttpResponse<Teacher[]> {
        const jsonResponse: Teacher[] = res.body;
        const body: Teacher[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    private setLangKey(teacherCopy: Teacher) {
        if (!teacherCopy.langKey) {
            this.languageService.getCurrent().then((key) => {
                teacherCopy.langKey = key;
            });
        }
    }

    /**
     * Convert a returned JSON object to Teacher.
     */
    private convertItemFromServer(teacher: Teacher): Teacher {
        return Object.assign({}, teacher);
    }

    /**
     * Convert a Teacher to a JSON which can be sent to the server.
     */
    private convert(teacher: Teacher): Teacher {
        return Object.assign({}, teacher);
    }

    private convertArray(teachers: Array<Teacher>): Array<Teacher> {
        return Object.assign([], teachers);
    }

    findByGradesIdsAndSubjectsIds(subjectsIds: any, gradesIds: any): Observable<EntityResponseArrayType> {
        return this.http.post<Array<Teacher>>(`${this.resourceUrl}/by-grades-and-subjects`, {
            gradesIds,
            subjectsIds
        }, {observe: 'response'})
            .pipe(map((res: EntityResponseArrayType) => res));
    }
}
