// ClientResolver
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';

import {SchoolClassService} from '../school-class.service';
import {SchoolClass} from '../../model';
import {map} from 'rxjs/operators';

@Injectable()
export class SchoolClassesResolver implements Resolve<SchoolClass[]> {

    constructor(private service: SchoolClassService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SchoolClass[]> | Promise<SchoolClass[]> | SchoolClass[] {
        return this.service.query()
            .pipe(map((res) => {
                return res.body;
            }));
    }

}
