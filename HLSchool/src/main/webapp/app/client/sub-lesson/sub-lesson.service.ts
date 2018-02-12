import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { SubLesson } from './sub-lesson.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SubLessonService {

    private resourceUrl =  SERVER_API_URL + 'api/sub-lessons';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/sub-lessons';
    private SubLessonByLessonIdUrl = SERVER_API_URL + 'api/sublessonsByLessonId';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(subLesson: SubLesson): Observable<SubLesson> {
        const copy = this.convert(subLesson);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(subLesson: SubLesson): Observable<SubLesson> {
        const copy = this.convert(subLesson);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<SubLesson> {
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

    getLessonsByCourseId(id: number, req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.SubLessonByLessonIdUrl + '/' + id, options)
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
     * Convert a returned JSON object to SubLesson.
     */
    private convertItemFromServer(json: any): SubLesson {
        const entity: SubLesson = Object.assign(new SubLesson(), json);
        entity.createDate = this.dateUtils
            .convertDateTimeFromServer(json.createDate);
        return entity;
    }

    /**
     * Convert a SubLesson to a JSON which can be sent to the server.
     */
    private convert(subLesson: SubLesson): SubLesson {
        const copy: SubLesson = Object.assign({}, subLesson);

        copy.createDate = this.dateUtils.toDate(subLesson.createDate);
        return copy;
    }
}
