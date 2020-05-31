import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {SchoolRoom} from '../../model';
import {SchoolRoomService} from '../school-room.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable()
export class SchoolRoomBynameResolver implements Resolve<SchoolRoom> {

    constructor(private service: SchoolRoomService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SchoolRoom> | Promise<SchoolRoom> | SchoolRoom {
        return this.service.find(route.paramMap.get('name'))
            .pipe(map((res) => res.body));
    }

}
