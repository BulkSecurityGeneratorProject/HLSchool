import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Vocabulary } from './vocabulary.model';
import { VocabularyPopupService } from './vocabulary-popup.service';
import { VocabularyService } from './vocabulary.service';

@Component({
    selector: 'jhi-vocabulary-delete-dialog',
    templateUrl: './vocabulary-delete-dialog.component.html'
})
export class VocabularyDeleteDialogComponent {

    vocabulary: Vocabulary;

    constructor(
        private vocabularyService: VocabularyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vocabularyService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'vocabularyListModification',
                content: 'Deleted an vocabulary'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vocabulary-delete-popup',
    template: ''
})
export class VocabularyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vocabularyPopupService: VocabularyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.vocabularyPopupService
                .open(VocabularyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
