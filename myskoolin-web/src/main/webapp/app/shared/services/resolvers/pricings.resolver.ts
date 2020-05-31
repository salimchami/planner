import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {PricingService} from '../../../../app/shared/services/pricing.service';
import {Pricing} from '../../../../app/shared/model/pricing.model';

@Injectable()
export class PricingsResolver implements Resolve<Pricing[]> {

    constructor(private service: PricingService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Pricing[]> | Promise<Pricing[]> | Pricing[] {
        return this.service.query()
            .pipe(map((res) => res.body));
    }

}
