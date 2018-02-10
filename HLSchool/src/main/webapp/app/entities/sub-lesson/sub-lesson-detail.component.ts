import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { SubLesson } from './sub-lesson.model';
import { SubLessonService } from './sub-lesson.service';

@Component({
    selector: 'jhi-sub-lesson-detail',
    templateUrl: './sub-lesson-detail.component.html'
})
export class SubLessonDetailComponent implements OnInit, OnDestroy {

    subLesson: SubLesson;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private subLessonService: SubLessonService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSubLessons();
    }

    load(id) {
        this.subLessonService.find(id).subscribe((subLesson) => {
            this.subLesson = subLesson;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSubLessons() {
        this.eventSubscriber = this.eventManager.subscribe(
            'subLessonListModification',
            (response) => this.load(this.subLesson.id)
        );
    }
}
