import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { GiftLog } from './gift-log.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class GiftLogService {

    private resourceUrl =  SERVER_API_URL + 'api/gift-logs';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/gift-logs';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(giftLog: GiftLog): Observable<GiftLog> {
        const copy = this.convert(giftLog);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(giftLog: GiftLog): Observable<GiftLog> {
        const copy = this.convert(giftLog);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<GiftLog> {
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
     * Convert a returned JSON object to GiftLog.
     */
    private convertItemFromServer(json: any): GiftLog {
        const entity: GiftLog = Object.assign(new GiftLog(), json);
        entity.createDate = this.dateUtils
            .convertDateTimeFromServer(json.createDate);
        return entity;
    }

    /**
     * Convert a GiftLog to a JSON which can be sent to the server.
     */
    private convert(giftLog: GiftLog): GiftLog {
        const copy: GiftLog = Object.assign({}, giftLog);

        copy.createDate = this.dateUtils.toDate(giftLog.createDate);
        return copy;
    }
}
