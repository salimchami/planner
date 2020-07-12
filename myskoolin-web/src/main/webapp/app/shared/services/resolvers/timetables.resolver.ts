import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {SchoolClassTimetable} from '../../model';
import {TimetableService} from '../../services/timetable.service';

@Injectable()
export class TimeTablesResolver implements Resolve<SchoolClassTimetable[]> {

    constructor(private service: TimetableService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SchoolClassTimetable[]>
        | Promise<SchoolClassTimetable[]> | SchoolClassTimetable[] {
        return this.service.query()
            .pipe(map((res) => {
                return res.body;
            }));
    }

}
