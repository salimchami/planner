import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Student} from '../../model';
import {StudentService} from '../student.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable()
export class StudentResolver implements Resolve<Student> {

    constructor(private service: StudentService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Student> | Promise<Student> | Student {
        return this.service.find(route.paramMap.get('id'))
            .pipe(map((res) => {
                return res.body;
            }));
    }

}
