import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { UserLog } from './user-log.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class UserLogService {

    private resourceUrl =  SERVER_API_URL + 'api/user-logs';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(userLog: UserLog): Observable<UserLog> {
        const copy = this.convert(userLog);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(userLog: UserLog): Observable<UserLog> {
        const copy = this.convert(userLog);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<UserLog> {
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

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to UserLog.
     */
    private convertItemFromServer(json: any): UserLog {
        const entity: UserLog = Object.assign(new UserLog(), json);
        entity.createDate = this.dateUtils
            .convertDateTimeFromServer(json.createDate);
        return entity;
    }

    /**
     * Convert a UserLog to a JSON which can be sent to the server.
     */
    private convert(userLog: UserLog): UserLog {
        const copy: UserLog = Object.assign({}, userLog);

        copy.createDate = this.dateUtils.toDate(userLog.createDate);
        return copy;
    }
}
