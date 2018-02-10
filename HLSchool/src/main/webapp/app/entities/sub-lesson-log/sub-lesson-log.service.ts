import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SubLessonLog } from './sub-lesson-log.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SubLessonLogService {

    private resourceUrl =  SERVER_API_URL + 'api/sub-lesson-logs';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/sub-lesson-logs';

    constructor(private http: Http) { }

    create(subLessonLog: SubLessonLog): Observable<SubLessonLog> {
        const copy = this.convert(subLessonLog);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(subLessonLog: SubLessonLog): Observable<SubLessonLog> {
        const copy = this.convert(subLessonLog);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<SubLessonLog> {
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
     * Convert a returned JSON object to SubLessonLog.
     */
    private convertItemFromServer(json: any): SubLessonLog {
        const entity: SubLessonLog = Object.assign(new SubLessonLog(), json);
        return entity;
    }

    /**
     * Convert a SubLessonLog to a JSON which can be sent to the server.
     */
    private convert(subLessonLog: SubLessonLog): SubLessonLog {
        const copy: SubLessonLog = Object.assign({}, subLessonLog);
        return copy;
    }
}
