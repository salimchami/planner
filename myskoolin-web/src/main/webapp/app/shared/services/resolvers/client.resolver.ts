// ClientResolver
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Client} from '../../model';
import {ClientService} from '../client.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable()
export class ClientResolver implements Resolve<Client> {

    constructor(private service: ClientService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Client> | Promise<Client> | Client {
        return this.service.getCurrent().pipe(map((res) => res.body));
    }

}
