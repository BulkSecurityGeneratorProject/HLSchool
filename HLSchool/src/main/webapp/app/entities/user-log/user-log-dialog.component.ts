import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserLog } from './user-log.model';
import { UserLogPopupService } from './user-log-popup.service';
import { UserLogService } from './user-log.service';

@Component({
    selector: 'jhi-user-log-dialog',
    templateUrl: './user-log-dialog.component.html'
})
export class UserLogDialogComponent implements OnInit {

    userLog: UserLog;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private userLogService: UserLogService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userLog.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userLogService.update(this.userLog));
        } else {
            this.subscribeToSaveResponse(
                this.userLogService.create(this.userLog));
        }
    }

    private subscribeToSaveResponse(result: Observable<UserLog>) {
        result.subscribe((res: UserLog) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: UserLog) {
        this.eventManager.broadcast({ name: 'userLogListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-user-log-popup',
    template: ''
})
export class UserLogPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userLogPopupService: UserLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userLogPopupService
                    .open(UserLogDialogComponent as Component, params['id']);
            } else {
                this.userLogPopupService
                    .open(UserLogDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
