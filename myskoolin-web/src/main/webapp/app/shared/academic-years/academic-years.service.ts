import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {AcademicYears} from './academic-years.model';

@Injectable()
export class AcademicYearsService  {

    private apiUrl = 'public/time/currentAcademicYears';

    constructor(private http: HttpClient) {
    }

    get(): Observable<HttpResponse<AcademicYears>> {
        return this.http.get<AcademicYears>(this.apiUrl, {observe : 'response'});
    }
}
