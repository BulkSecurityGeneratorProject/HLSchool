import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SubLessonLog } from './sub-lesson-log.model';
import { SubLessonLogPopupService } from './sub-lesson-log-popup.service';
import { SubLessonLogService } from './sub-lesson-log.service';

@Component({
    selector: 'jhi-sub-lesson-log-delete-dialog',
    templateUrl: './sub-lesson-log-delete-dialog.component.html'
})
export class SubLessonLogDeleteDialogComponent {

    subLessonLog: SubLessonLog;

    constructor(
        private subLessonLogService: SubLessonLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.subLessonLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'subLessonLogListModification',
                content: 'Deleted an subLessonLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sub-lesson-log-delete-popup',
    template: ''
})
export class SubLessonLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private subLessonLogPopupService: SubLessonLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.subLessonLogPopupService
                .open(SubLessonLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
