import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Newsletter} from '../../shared/model/newsletter.model';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

export type EntityResponseType = HttpResponse<Newsletter>;

@Injectable()
export class NewsletterService {

    private resourceUrl = 'public/newsletters';
    private confirmationResourceUrl = 'public/newsletters/confirmSubscription';
    private newsletterByKeyResourceUrl = 'public/newsletters/byConfirmToken';

    private static convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Newsletter = NewsletterService.convertItemFromServer(res.body);
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Contact.
     */
    private static convertItemFromServer(newsletter: Newsletter): Newsletter {
        return Object.assign({}, newsletter);
    }

    /**
     * Convert a Contact to a JSON which can be sent to the server.
     */
    private static convert(newsletter: Newsletter): Newsletter {
        return Object.assign({}, newsletter);
    }

    constructor(private http: HttpClient) {
    }

    create(newsletter: Newsletter): Observable<EntityResponseType> {
        const copy = NewsletterService.convert(newsletter);
        return this.http
            .post<Newsletter>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res) => res));
    }

    confirm(key: string) {
        return this.http.get<Newsletter>(`${this.newsletterByKeyResourceUrl}/${key}`)
            .subscribe((newsletter: Newsletter) => {
                return this.http.put<Newsletter>(this.confirmationResourceUrl, newsletter)
                    .subscribe((result: any) => {
                        return result;
                    });
            });
    }

}
