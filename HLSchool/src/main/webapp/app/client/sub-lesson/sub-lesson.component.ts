import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Lesson } from './../lesson/lesson.model';
import { SubLesson } from './sub-lesson.model';
import { SubLessonService } from './sub-lesson.service';

@Component({
    selector: 'jhi-client-sub-lesson',
    templateUrl: './sub-lesson.component.html',
    styleUrls: [
        'sub-lesson.scss'
    ]
})
export class ClientSubLessonComponent implements OnInit, OnDestroy {
    lesson: Lesson;
    subLessons: SubLesson[];
    i = 2;
    constructor(
        private router: Router,
        private storeService: StoreService,
        private subLessonService: SubLessonService
    ) {
    }
    ngOnInit() {
        this.lesson = this.storeService.course;
        this.loadAll();
    }

    ngOnDestroy() {
    }
    loadAll() {
        this.subLessonService.getLessonsByCourseId(this.lesson.id, {
            page: 0,
            size: 50,
            sort: null}).subscribe(
            (res: ResponseWrapper) => this.onLoadSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadError(res.json)
        );
    }
    private onLoadSuccess(data, headers) {
        // this.page = pagingParams.page;
        this.subLessons = data;
    }
    private onLoadError(error) {
    }
}
