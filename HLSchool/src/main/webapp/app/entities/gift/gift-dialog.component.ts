import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Gift } from './gift.model';
import { GiftPopupService } from './gift-popup.service';
import { GiftService } from './gift.service';

@Component({
    selector: 'jhi-gift-dialog',
    templateUrl: './gift-dialog.component.html'
})
export class GiftDialogComponent implements OnInit {

    gift: Gift;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private giftService: GiftService,
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
        this.dataUtils.clearInputImage(this.gift, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.gift.id !== undefined) {
            this.subscribeToSaveResponse(
                this.giftService.update(this.gift));
        } else {
            this.subscribeToSaveResponse(
                this.giftService.create(this.gift));
        }
    }

    private subscribeToSaveResponse(result: Observable<Gift>) {
        result.subscribe((res: Gift) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Gift) {
        this.eventManager.broadcast({ name: 'giftListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-gift-popup',
    template: ''
})
export class GiftPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private giftPopupService: GiftPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.giftPopupService
                    .open(GiftDialogComponent as Component, params['id']);
            } else {
                this.giftPopupService
                    .open(GiftDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
