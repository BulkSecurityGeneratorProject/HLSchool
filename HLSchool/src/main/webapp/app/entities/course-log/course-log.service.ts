import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CourseLog } from './course-log.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CourseLogService {

    private resourceUrl =  SERVER_API_URL + 'api/course-logs';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/course-logs';

    constructor(private http: Http) { }

    create(courseLog: CourseLog): Observable<CourseLog> {
        const copy = this.convert(courseLog);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(courseLog: CourseLog): Observable<CourseLog> {
        const copy = this.convert(courseLog);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<CourseLog> {
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
     * Convert a returned JSON object to CourseLog.
     */
    private convertItemFromServer(json: any): CourseLog {
        const entity: CourseLog = Object.assign(new CourseLog(), json);
        return entity;
    }

    /**
     * Convert a CourseLog to a JSON which can be sent to the server.
     */
    private convert(courseLog: CourseLog): CourseLog {
        const copy: CourseLog = Object.assign({}, courseLog);
        return copy;
    }
}
