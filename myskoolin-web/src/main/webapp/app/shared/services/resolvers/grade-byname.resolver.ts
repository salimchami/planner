import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {GradeService} from '../grade.service';
import {Observable} from 'rxjs';
import {Grade} from '../../model';
import {map} from 'rxjs/operators';

@Injectable()
export class GradeBynameResolver implements Resolve<Grade> {

    constructor(private service: GradeService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Grade> | Promise<Grade> | Grade {
        return this.service.find(route.paramMap.get('name'))
            .pipe(map((res) => res.body));
    }

}
