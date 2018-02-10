import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Gift } from './gift.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class GiftService {

    private resourceUrl =  SERVER_API_URL + 'api/gifts';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/gifts';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(gift: Gift): Observable<Gift> {
        const copy = this.convert(gift);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(gift: Gift): Observable<Gift> {
        const copy = this.convert(gift);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Gift> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Gift.
     */
    private convertItemFromServer(json: any): Gift {
        const entity: Gift = Object.assign(new Gift(), json);
        entity.createDate = this.dateUtils
            .convertDateTimeFromServer(json.createDate);
        return entity;
    }

    /**
     * Convert a Gift to a JSON which can be sent to the server.
     */
    private convert(gift: Gift): Gift {
        const copy: Gift = Object.assign({}, gift);

        copy.createDate = this.dateUtils.toDate(gift.createDate);
        return copy;
    }
}
