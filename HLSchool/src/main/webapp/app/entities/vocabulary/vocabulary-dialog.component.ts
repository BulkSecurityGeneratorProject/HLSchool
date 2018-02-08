import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Vocabulary } from './vocabulary.model';
import { VocabularyPopupService } from './vocabulary-popup.service';
import { VocabularyService } from './vocabulary.service';

@Component({
    selector: 'jhi-vocabulary-dialog',
    templateUrl: './vocabulary-dialog.component.html'
})
export class VocabularyDialogComponent implements OnInit {

    vocabulary: Vocabulary;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private vocabularyService: VocabularyService,
        private elementRef: ElementRef,
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

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.vocabulary, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.vocabulary.id !== undefined) {
            this.subscribeToSaveResponse(
                this.vocabularyService.update(this.vocabulary));
        } else {
            this.subscribeToSaveResponse(
                this.vocabularyService.create(this.vocabulary));
        }
    }

    private subscribeToSaveResponse(result: Observable<Vocabulary>) {
        result.subscribe((res: Vocabulary) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Vocabulary) {
        this.eventManager.broadcast({ name: 'vocabularyListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-vocabulary-popup',
    template: ''
})
export class VocabularyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vocabularyPopupService: VocabularyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.vocabularyPopupService
                    .open(VocabularyDialogComponent as Component, params['id']);
            } else {
                this.vocabularyPopupService
                    .open(VocabularyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
