import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UserLog } from './user-log.model';
import { UserLogService } from './user-log.service';

@Component({
    selector: 'jhi-user-log-detail',
    templateUrl: './user-log-detail.component.html'
})
export class UserLogDetailComponent implements OnInit, OnDestroy {

    userLog: UserLog;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private userLogService: UserLogService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserLogs();
    }

    load(id) {
        this.userLogService.find(id).subscribe((userLog) => {
            this.userLog = userLog;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserLogs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userLogListModification',
            (response) => this.load(this.userLog.id)
        );
    }
}
