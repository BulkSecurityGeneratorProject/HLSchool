import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { SubLessonLog } from './sub-lesson-log.model';
import { SubLessonLogPopupService } from './sub-lesson-log-popup.service';
import { SubLessonLogService } from './sub-lesson-log.service';
import { User, UserService } from '../../shared';
import { SubLesson, SubLessonService } from '../sub-lesson';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-sub-lesson-log-dialog',
    templateUrl: './sub-lesson-log-dialog.component.html'
})
export class SubLessonLogDialogComponent implements OnInit {

    subLessonLog: SubLessonLog;
    isSaving: boolean;

    users: User[];

    sublessons: SubLesson[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private subLessonLogService: SubLessonLogService,
        private userService: UserService,
        private subLessonService: SubLessonService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.subLessonService.query()
            .subscribe((res: ResponseWrapper) => { this.sublessons = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.subLessonLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.subLessonLogService.update(this.subLessonLog));
        } else {
            this.subscribeToSaveResponse(
                this.subLessonLogService.create(this.subLessonLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<SubLessonLog>) {
        result.subscribe((res: SubLessonLog) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: SubLessonLog) {
        this.eventManager.broadcast({ name: 'subLessonLogListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackSubLessonById(index: number, item: SubLesson) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-sub-lesson-log-popup',
    template: ''
})
export class SubLessonLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private subLessonLogPopupService: SubLessonLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.subLessonLogPopupService
                    .open(SubLessonLogDialogComponent as Component, params['id']);
            } else {
                this.subLessonLogPopupService
                    .open(SubLessonLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
