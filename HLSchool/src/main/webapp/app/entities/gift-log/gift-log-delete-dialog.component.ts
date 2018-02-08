import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { GiftLog } from './gift-log.model';
import { GiftLogPopupService } from './gift-log-popup.service';
import { GiftLogService } from './gift-log.service';

@Component({
    selector: 'jhi-gift-log-delete-dialog',
    templateUrl: './gift-log-delete-dialog.component.html'
})
export class GiftLogDeleteDialogComponent {

    giftLog: GiftLog;

    constructor(
        private giftLogService: GiftLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.giftLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'giftLogListModification',
                content: 'Deleted an giftLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-gift-log-delete-popup',
    template: ''
})
export class GiftLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private giftLogPopupService: GiftLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.giftLogPopupService
                .open(GiftLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
