import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SubLesson } from './sub-lesson.model';
import { SubLessonPopupService } from './sub-lesson-popup.service';
import { SubLessonService } from './sub-lesson.service';

@Component({
    selector: 'jhi-sub-lesson-delete-dialog',
    templateUrl: './sub-lesson-delete-dialog.component.html'
})
export class SubLessonDeleteDialogComponent {

    subLesson: SubLesson;

    constructor(
        private subLessonService: SubLessonService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.subLessonService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'subLessonListModification',
                content: 'Deleted an subLesson'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sub-lesson-delete-popup',
    template: ''
})
export class SubLessonDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private subLessonPopupService: SubLessonPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.subLessonPopupService
                .open(SubLessonDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
