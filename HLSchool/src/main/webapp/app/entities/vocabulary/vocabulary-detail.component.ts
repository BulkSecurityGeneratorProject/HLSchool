import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Vocabulary } from './vocabulary.model';
import { VocabularyService } from './vocabulary.service';

@Component({
    selector: 'jhi-vocabulary-detail',
    templateUrl: './vocabulary-detail.component.html'
})
export class VocabularyDetailComponent implements OnInit, OnDestroy {

    vocabulary: Vocabulary;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private vocabularyService: VocabularyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVocabularies();
    }

    load(id) {
        this.vocabularyService.find(id).subscribe((vocabulary) => {
            this.vocabulary = vocabulary;
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

    registerChangeInVocabularies() {
        this.eventSubscriber = this.eventManager.subscribe(
            'vocabularyListModification',
            (response) => this.load(this.vocabulary.id)
        );
    }
}
