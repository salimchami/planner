// ClientResolver
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Client} from '../../model';
import {ClientService} from '../client.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {TimeTableOptions} from '../../../shared/model/sub/time-table-options.model';

@Injectable()
export class TimeTableOptionsResolver implements Resolve<TimeTableOptions> {

    constructor(private service: ClientService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TimeTableOptions>
        | Promise<TimeTableOptions> | TimeTableOptions {
        return this.service.getTimeTableOptions().pipe(map((res) => res.body));
    }

}
