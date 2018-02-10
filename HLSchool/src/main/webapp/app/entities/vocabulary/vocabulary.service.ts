import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Vocabulary } from './vocabulary.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class VocabularyService {

    private resourceUrl =  SERVER_API_URL + 'api/vocabularies';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/vocabularies';

    constructor(private http: Http) { }

    create(vocabulary: Vocabulary): Observable<Vocabulary> {
        const copy = this.convert(vocabulary);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(vocabulary: Vocabulary): Observable<Vocabulary> {
        const copy = this.convert(vocabulary);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Vocabulary> {
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
     * Convert a returned JSON object to Vocabulary.
     */
    private convertItemFromServer(json: any): Vocabulary {
        const entity: Vocabulary = Object.assign(new Vocabulary(), json);
        return entity;
    }

    /**
     * Convert a Vocabulary to a JSON which can be sent to the server.
     */
    private convert(vocabulary: Vocabulary): Vocabulary {
        const copy: Vocabulary = Object.assign({}, vocabulary);
        return copy;
    }
}
