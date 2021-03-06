import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Course } from './course.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CourseService {

    private resourceUrl =  SERVER_API_URL + 'api/courses';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/courses';
    private resourceInLogUrl = SERVER_API_URL + 'api/coursesInLog';
    private resourceNotInLogUrl = SERVER_API_URL + 'api/coursesNotInLog';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(course: Course): Observable<Course> {
        const copy = this.convert(course);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(course: Course): Observable<Course> {
        const copy = this.convert(course);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Course> {
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

    queryInLog(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceInLogUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    queryNotInLog(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceNotInLogUrl, options)
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
     * Convert a returned JSON object to Course.
     */
    private convertItemFromServer(json: any): Course {
        const entity: Course = Object.assign(new Course(), json);
        entity.createDate = this.dateUtils
            .convertDateTimeFromServer(json.createDate);
        return entity;
    }

    /**
     * Convert a Course to a JSON which can be sent to the server.
     */
    private convert(course: Course): Course {
        const copy: Course = Object.assign({}, course);

        copy.createDate = this.dateUtils.toDate(course.createDate);
        return copy;
    }
}
