import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';

import {Grade} from '../model';
import {createRequestOption} from './utils/request-util';
import {map} from 'rxjs/operators';
import {Pricing} from 'app/shared/model/pricing.model';

@Injectable()
export class PricingService {

    private resourceUrl = SERVER_API_URL + 'public/pricings';

    constructor(private http: HttpClient) {
    }

    query(req?: any): Observable<HttpResponse<Pricing[]>> {
        const options = createRequestOption(req);
        return this.http.get<Pricing[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: HttpResponse<Pricing[]>) => this.convertArrayResponse(res)));
    }

    private convertArrayResponse(res: HttpResponse<Pricing[]>): HttpResponse<Pricing[]> {
        const jsonResponse: Pricing[] = res.body;
        const body: Pricing[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(Object.assign({}, jsonResponse[i]));
        }
        return res.clone({body});
    }
}
