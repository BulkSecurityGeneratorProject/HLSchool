import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { SubLessonLog } from './sub-lesson-log.model';
import { SubLessonLogService } from './sub-lesson-log.service';

@Component({
    selector: 'jhi-sub-lesson-log-detail',
    templateUrl: './sub-lesson-log-detail.component.html'
})
export class SubLessonLogDetailComponent implements OnInit, OnDestroy {

    subLessonLog: SubLessonLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private subLessonLogService: SubLessonLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSubLessonLogs();
    }

    load(id) {
        this.subLessonLogService.find(id).subscribe((subLessonLog) => {
            this.subLessonLog = subLessonLog;
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

    registerChangeInSubLessonLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'subLessonLogListModification',
            (response) => this.load(this.subLessonLog.id)
        );
    }
}
