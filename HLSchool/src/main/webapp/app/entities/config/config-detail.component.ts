import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Config } from './config.model';
import { ConfigService } from './config.service';

@Component({
    selector: 'jhi-config-detail',
    templateUrl: './config-detail.component.html'
})
export class ConfigDetailComponent implements OnInit, OnDestroy {

    config: Config;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private configService: ConfigService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfigs();
    }

    load(id) {
        this.configService.find(id).subscribe((config) => {
            this.config = config;
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

    registerChangeInConfigs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'configListModification',
            (response) => this.load(this.config.id)
        );
    }
}
