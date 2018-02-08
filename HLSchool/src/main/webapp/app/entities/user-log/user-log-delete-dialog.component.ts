import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserLog } from './user-log.model';
import { UserLogPopupService } from './user-log-popup.service';
import { UserLogService } from './user-log.service';

@Component({
    selector: 'jhi-user-log-delete-dialog',
    templateUrl: './user-log-delete-dialog.component.html'
})
export class UserLogDeleteDialogComponent {

    userLog: UserLog;

    constructor(
        private userLogService: UserLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userLogListModification',
                content: 'Deleted an userLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-log-delete-popup',
    template: ''
})
export class UserLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userLogPopupService: UserLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userLogPopupService
                .open(UserLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
