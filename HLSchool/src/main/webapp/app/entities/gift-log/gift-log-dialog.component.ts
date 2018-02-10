import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { GiftLog } from './gift-log.model';
import { GiftLogPopupService } from './gift-log-popup.service';
import { GiftLogService } from './gift-log.service';
import { User, UserService } from '../../shared';
import { Gift, GiftService } from '../gift';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-gift-log-dialog',
    templateUrl: './gift-log-dialog.component.html'
})
export class GiftLogDialogComponent implements OnInit {

    giftLog: GiftLog;
    isSaving: boolean;

    users: User[];

    gifts: Gift[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private giftLogService: GiftLogService,
        private userService: UserService,
        private giftService: GiftService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.giftService.query()
            .subscribe((res: ResponseWrapper) => { this.gifts = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
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
        if (this.giftLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.giftLogService.update(this.giftLog));
        } else {
            this.subscribeToSaveResponse(
                this.giftLogService.create(this.giftLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<GiftLog>) {
        result.subscribe((res: GiftLog) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: GiftLog) {
        this.eventManager.broadcast({ name: 'giftLogListModification', content: 'OK'});
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

    trackGiftById(index: number, item: Gift) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-gift-log-popup',
    template: ''
})
export class GiftLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private giftLogPopupService: GiftLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.giftLogPopupService
                    .open(GiftLogDialogComponent as Component, params['id']);
            } else {
                this.giftLogPopupService
                    .open(GiftLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
