import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Lesson } from './lesson.model';
import { Course } from './../course/course.model';
import { LessonService } from './lesson.service';

@Component({
    selector: 'jhi-client-lesson',
    templateUrl: './lesson.component.html',
    styleUrls: [
        'lesson.scss'
    ]
})
export class ClientLessonComponent implements OnInit, OnDestroy {
    course: Course;
    lessons: Lesson[];
    i = 2;
    constructor(
        private router: Router,
        private storeService: StoreService,
        private lessonService: LessonService
    ) {
    }
    ngOnInit() {
        this.course = this.storeService.course;
        this.loadAll();
    }

    ngOnDestroy() {
    }
    loadAll() {
        this.lessonService.getLessonsByCourseId(this.course.id, {
            page: 0,
            size: 50,
            sort: null}).subscribe(
            (res: ResponseWrapper) => this.onLoadSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadError(res.json)
        );
    }
    private onLoadSuccess(data, headers) {
        // this.page = pagingParams.page;
        this.lessons = data;
    }
    private onLoadError(error) {
    }
}
