import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from '../../app.constants';

import { SchoolRoom } from '../model';
import {createRequestOption} from './utils/request-util';
import {Component} from '@angular/core/src/metadata/directives';
import {map} from 'rxjs/operators';

export type EntityResponseType = HttpResponse<SchoolRoom>;

@Injectable()
export class SchoolRoomService {

    private resourceUrl =  SERVER_API_URL + 'api/school-rooms';

    constructor(private http: HttpClient) { }

    create(schoolRoom: SchoolRoom): Observable<EntityResponseType> {
        const copy = this.convert(schoolRoom);
        return this.http.post<SchoolRoom>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    update(schoolRoom: SchoolRoom): Observable<EntityResponseType> {
        const copy = this.convert(schoolRoom);
        return this.http.put<SchoolRoom>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    find(name: string): Observable<EntityResponseType> {
        return this.http.get<SchoolRoom>(`${this.resourceUrl}/${name}`, { observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertResponse(res)));
    }

    query(req?: any): Observable<HttpResponse<SchoolRoom[]>> {
        const options = createRequestOption(req);
        return this.http.get<SchoolRoom[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: HttpResponse<SchoolRoom[]>) => this.convertArrayResponse(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SchoolRoom = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SchoolRoom[]>): HttpResponse<SchoolRoom[]> {
        const jsonResponse: SchoolRoom[] = res.body;
        const body: SchoolRoom[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SchoolRoom.
     */
    private convertItemFromServer(schoolRoom: SchoolRoom): SchoolRoom {
        const copy: SchoolRoom = Object.assign({}, schoolRoom);
        return copy;
    }

    /**
     * Convert a SchoolRoom to a JSON which can be sent to the server.
     */
    private convert(schoolRoom: SchoolRoom): SchoolRoom {
        const copy: SchoolRoom = Object.assign({}, schoolRoom);
        return copy;
    }

    open(schoolRoomDeleteDialogComponent: Component, param: any) {

    }
}
