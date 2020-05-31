import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Teacher} from '../../model/teacher.model';
import {TeacherService} from '../teacher.service';
import {map} from 'rxjs/operators';

@Injectable()
export class TeachersResolver implements Resolve<Teacher[]> {

    constructor(private service: TeacherService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Teacher[]> | Promise<Teacher[]> | Teacher[] {
        return this.service.query()
            .pipe(map((res) => {
                return res.body;
            }));
    }

}
