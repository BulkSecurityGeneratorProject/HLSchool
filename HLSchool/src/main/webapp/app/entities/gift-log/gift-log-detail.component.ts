import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { GiftLog } from './gift-log.model';
import { GiftLogService } from './gift-log.service';

@Component({
    selector: 'jhi-gift-log-detail',
    templateUrl: './gift-log-detail.component.html'
})
export class GiftLogDetailComponent implements OnInit, OnDestroy {

    giftLog: GiftLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private giftLogService: GiftLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInGiftLogs();
    }

    load(id) {
        this.giftLogService.find(id).subscribe((giftLog) => {
            this.giftLog = giftLog;
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

    registerChangeInGiftLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'giftLogListModification',
            (response) => this.load(this.giftLog.id)
        );
    }
}
