import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CourseLog } from './course-log.model';
import { CourseLogPopupService } from './course-log-popup.service';
import { CourseLogService } from './course-log.service';

@Component({
    selector: 'jhi-course-log-delete-dialog',
    templateUrl: './course-log-delete-dialog.component.html'
})
export class CourseLogDeleteDialogComponent {

    courseLog: CourseLog;

    constructor(
        private courseLogService: CourseLogService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.courseLogService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'courseLogListModification',
                content: 'Deleted an courseLog'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-course-log-delete-popup',
    template: ''
})
export class CourseLogDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private courseLogPopupService: CourseLogPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.courseLogPopupService
                .open(CourseLogDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
