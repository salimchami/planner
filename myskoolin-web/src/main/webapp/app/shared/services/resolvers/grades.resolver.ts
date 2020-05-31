import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Grade} from '../../model';
import {GradeService} from '../grade.service';
import {map} from 'rxjs/operators';

@Injectable()
export class GradesResolver implements Resolve<Grade[]> {

    constructor(private service: GradeService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Grade[]> | Promise<Grade[]> | Grade[] {
        return this.service.query()
            .pipe(map((res) => res.body));
    }

}
