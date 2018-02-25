import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Course } from './course.model';
import { CourseService } from './course.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'jhi-client-course',
    templateUrl: './course.component.html',
    styleUrls: [
        'course.scss'
    ]
})
export class ClientCourseComponent implements OnInit, OnDestroy {
    coursesInLog: Course[];
    coursesNotInLog: Course[];
    constructor(
        private router: Router,
        private courseService: CourseService,
        private jhiAlertService: JhiAlertService,
        private storeService: StoreService,
        private translateService: TranslateService
    ) {
    }
    ngOnInit() {
        this.loadAllInLog();
        this.loadAllNotInLog();
    }

    ngOnDestroy() {
    }

    loadAllInLog() {
        this.courseService.queryInLog({
            page: 0,
            size: 10,
            sort: null}).subscribe(
            (res: ResponseWrapper) => this.onLoadInLogSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadInLogError(res.json)
        );
    }
    onLoadInLogSuccess(data, headers) {
        this.coursesInLog = data;
    }

    onLoadInLogError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    loadAllNotInLog() {
        this.courseService.queryNotInLog({
            page: 0,
            size: 10,
            sort: null}).subscribe(
            (res: ResponseWrapper) => this.onLoadNotInLogSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadNotInLogError(res.json)
        );
    }
    onLoadNotInLogSuccess(data, headers) {
        this.coursesNotInLog = data;
    }

    onLoadNotInLogError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    onClickCourse(course) {
        this.storeService.course = course;
        this.router.navigateByUrl('/client/lesson');
    }
}
