import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Course } from './course.model';
import { CourseService } from './course.service';

@Component({
    selector: 'jhi-client-course',
    templateUrl: './course.component.html',
    styleUrls: [
        'course.scss'
    ]
})
export class ClientCourseComponent implements OnInit, OnDestroy {
    courses: Course[];
    constructor(
        private router: Router,
        private courseService: CourseService,
        private jhiAlertService: JhiAlertService,
        private storeService: StoreService
    ) {
    }
    ngOnInit() {
        this.loadAll();
    }

    ngOnDestroy() {
    }

    loadAll() {
        this.courseService.query({
            page: 0,
            size: 10,
            sort: null}).subscribe(
            (res: ResponseWrapper) => this.onLoadSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadError(res.json)
        );
    }
    onLoadSuccess(data, headers) {
        this.courses = data;
    }

    onLoadError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    onClickCourse(course) {
        this.storeService.course = course;
        this.router.navigateByUrl('/client/lesson');
    }
}
