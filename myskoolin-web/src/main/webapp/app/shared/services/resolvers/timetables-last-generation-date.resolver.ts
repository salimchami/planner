// ClientResolver
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {TimetableService} from '../../../shared/services/timetable.service';

@Injectable()
export class TimeTablesLastGenerationDateResolver implements Resolve<Date> {

    constructor(private service: TimetableService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Date>
        | Promise<Date> | Date {
        return this.service.lastGenerationDate().pipe(map((res) => res.body));
    }

}
