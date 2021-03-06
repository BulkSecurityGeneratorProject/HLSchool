import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { LessonLog } from './lesson-log.model';
import { LessonLogService } from './lesson-log.service';

@Component({
    selector: 'jhi-lesson-log-detail',
    templateUrl: './lesson-log-detail.component.html'
})
export class LessonLogDetailComponent implements OnInit, OnDestroy {

    lessonLog: LessonLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private lessonLogService: LessonLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLessonLogs();
    }

    load(id) {
        this.lessonLogService.find(id).subscribe((lessonLog) => {
            this.lessonLog = lessonLog;
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

    registerChangeInLessonLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'lessonLogListModification',
            (response) => this.load(this.lessonLog.id)
        );
    }
}
