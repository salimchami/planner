import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Teacher} from '../../model';
import {TeacherService} from '../teacher.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable()
export class TeacherResolver implements Resolve<Teacher> {

    constructor(private service: TeacherService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Teacher> | Promise<Teacher> | Teacher {
        return this.service.find(route.paramMap.get('id'))
            .pipe(map((res) => {
                return res.body;
            }));
    }

}
