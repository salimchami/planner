import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Subject} from '../../model';
import {SubjectService} from '../subject.service';
import {map} from 'rxjs/operators';

@Injectable()
export class SubjectResolver implements Resolve<Subject> {

    constructor(private service: SubjectService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Subject> | Promise<Subject> | Subject {
        return this.service.find(route.paramMap.get('id'))
            .pipe(map((res) => res.body));
    }

}
