// ClientResolver
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {TimetableService} from '../../../shared/services/timetable.service';

@Injectable()
export class TimeTablesCountResolver implements Resolve<number> {

    constructor(private service: TimetableService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<number>
        | Promise<number> | number {
        return this.service.countTimeTables().pipe(map((res) => res.body));
    }

}
