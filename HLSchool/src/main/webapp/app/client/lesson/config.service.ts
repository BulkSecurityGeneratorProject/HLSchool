import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Config } from './config.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ConfigService {

    private resourceUrl =  SERVER_API_URL + 'api/configs';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/configs';

    constructor(private http: Http) { }

    create(config: Config): Observable<Config> {
        const copy = this.convert(config);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(config: Config): Observable<Config> {
        const copy = this.convert(config);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Config> {
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
     * Convert a returned JSON object to Config.
     */
    private convertItemFromServer(json: any): Config {
        const entity: Config = Object.assign(new Config(), json);
        return entity;
    }

    /**
     * Convert a Config to a JSON which can be sent to the server.
     */
    private convert(config: Config): Config {
        const copy: Config = Object.assign({}, config);
        return copy;
    }
}
