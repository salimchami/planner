import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_API_URL} from '../../app.constants';
import {Client} from '../model';
import {ClientContractRequest} from 'app/shared/model/client-contract-request.model';

export type EntityResponseType = HttpResponse<Client>;
export type ContractRequestEntityResponseType = HttpResponse<ClientContractRequest>;

@Injectable()
export class ClientService {
    private resourceUrl = SERVER_API_URL + 'api/clients';

    constructor(private http: HttpClient) {
    }

    getCurrent(): Observable<HttpResponse<Client>> {
        return this.http.get<Client>('api/clients/current', {observe: 'response'});
    }

    update(client: Client): Observable<EntityResponseType> {
        const clientCopy = Object.assign({}, client);
        return this.http.put<Client>('api/clientConf/infos', clientCopy, {observe: 'response'});
    }

    /**
     * Convert a SchoolClass to a JSON which can be sent to the server.
     */
    private convert(client: ClientContractRequest): ClientContractRequest {
        return Object.assign({}, client);
    }

    updateContract(client: ClientContractRequest): Observable<ContractRequestEntityResponseType>  {
        const clientCopy = this.convert(client);
        return this.http.post<ClientContractRequest>('api/clientConf/contract', clientCopy, {observe: 'response'});
    }
}
