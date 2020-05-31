import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Contact} from '../../shared/model';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

export type EntityResponseType = HttpResponse<Contact>;

@Injectable()
export class ContactService {

    private resourceUrl = 'public/contacts';

    /**
     * Convert a Contact to a JSON which can be sent to the server.
     */
    private static convert(contact: Contact): Contact {
        return Object.assign({}, contact);
    }

    constructor(private http: HttpClient) {
    }

    create(contact: Contact): Observable<EntityResponseType> {
        const copy = ContactService.convert(contact);
        return this.http
            .post<Contact>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => res));
    }

}
