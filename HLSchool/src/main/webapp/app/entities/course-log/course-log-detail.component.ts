import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CourseLog } from './course-log.model';
import { CourseLogService } from './course-log.service';

@Component({
    selector: 'jhi-course-log-detail',
    templateUrl: './course-log-detail.component.html'
})
export class CourseLogDetailComponent implements OnInit, OnDestroy {

    courseLog: CourseLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private courseLogService: CourseLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCourseLogs();
    }

    load(id) {
        this.courseLogService.find(id).subscribe((courseLog) => {
            this.courseLog = courseLog;
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

    registerChangeInCourseLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'courseLogListModification',
            (response) => this.load(this.courseLog.id)
        );
    }
}
