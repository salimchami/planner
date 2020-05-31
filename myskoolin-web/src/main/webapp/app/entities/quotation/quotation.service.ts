import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Interlocutor, SchoolmeSubscription} from '../../shared/model';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

export type EntityResponseType = HttpResponse<Interlocutor>;

@Injectable()
export class QuotationService {

    private resourceUrl = 'public/subscriptions';

    /**
     * Convert a Contact to a JSON which can be sent to the server.
     */
    private static convert(quotation: SchoolmeSubscription): SchoolmeSubscription {
        return Object.assign({}, quotation);
    }

    constructor(private http: HttpClient) {
    }

    create(subscription: SchoolmeSubscription): Observable<EntityResponseType> {
        const copy = QuotationService.convert(subscription);
        return this.http
            .post<Interlocutor>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => res));
    }
}
