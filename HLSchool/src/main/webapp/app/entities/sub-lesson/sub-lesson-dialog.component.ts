import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { SubLesson } from './sub-lesson.model';
import { SubLessonPopupService } from './sub-lesson-popup.service';
import { SubLessonService } from './sub-lesson.service';

@Component({
    selector: 'jhi-sub-lesson-dialog',
    templateUrl: './sub-lesson-dialog.component.html'
})
export class SubLessonDialogComponent implements OnInit {

    subLesson: SubLesson;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private subLessonService: SubLessonService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
        if (this.subLesson.id !== undefined) {
            this.subscribeToSaveResponse(
                this.subLessonService.update(this.subLesson));
        } else {
            this.subscribeToSaveResponse(
                this.subLessonService.create(this.subLesson));
        }
    }

    private subscribeToSaveResponse(result: Observable<SubLesson>) {
        result.subscribe((res: SubLesson) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: SubLesson) {
        this.eventManager.broadcast({ name: 'subLessonListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-sub-lesson-popup',
    template: ''
})
export class SubLessonPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private subLessonPopupService: SubLessonPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.subLessonPopupService
                    .open(SubLessonDialogComponent as Component, params['id']);
            } else {
                this.subLessonPopupService
                    .open(SubLessonDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
