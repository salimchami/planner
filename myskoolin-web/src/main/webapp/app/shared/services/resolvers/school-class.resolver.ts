import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {SchoolClass} from '../../model';
import {SchoolClassService} from '../school-class.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable()
export class SchoolClassResolver implements Resolve<SchoolClass> {

    constructor(private service: SchoolClassService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SchoolClass> | Promise<SchoolClass> | SchoolClass {
        return this.service.find(route.paramMap.get('id'))
            .pipe(map((res) => {
                return res.body;
            }));
    }

}
