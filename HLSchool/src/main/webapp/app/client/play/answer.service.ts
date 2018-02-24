import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Answer } from './answer.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AnswerService {

    private resourceUrl =  SERVER_API_URL + 'api/answers';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/answers';
    private resourceByQuestionId = SERVER_API_URL + 'api/answersByQuestionId';
    private resourceFullInfoByQuestionId = SERVER_API_URL + 'api/answersFullInfoByQuestionId';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(answer: Answer): Observable<Answer> {
        const copy = this.convert(answer);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(answer: Answer): Observable<Answer> {
        const copy = this.convert(answer);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Answer> {
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

    queryByQuestionId(id: number, req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceByQuestionId + '/' + id, options);
    }

    queryFullInfoByQuestionId(id: number, req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceFullInfoByQuestionId + '/' + id, options)
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
     * Convert a returned JSON object to Answer.
     */
    private convertItemFromServer(json: any): Answer {
        const entity: Answer = Object.assign(new Answer(), json);
        entity.createDate = this.dateUtils
            .convertDateTimeFromServer(json.createDate);
        return entity;
    }

    /**
     * Convert a Answer to a JSON which can be sent to the server.
     */
    private convert(answer: Answer): Answer {
        const copy: Answer = Object.assign({}, answer);

        copy.createDate = this.dateUtils.toDate(answer.createDate);
        return copy;
    }
}
