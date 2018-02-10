/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { SubLessonLogDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log-delete-dialog.component';
import { SubLessonLogService } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log.service';

describe('Component Tests', () => {

    describe('SubLessonLog Management Delete Component', () => {
        let comp: SubLessonLogDeleteDialogComponent;
        let fixture: ComponentFixture<SubLessonLogDeleteDialogComponent>;
        let service: SubLessonLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [SubLessonLogDeleteDialogComponent],
                providers: [
                    SubLessonLogService
                ]
            })
            .overrideTemplate(SubLessonLogDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubLessonLogDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubLessonLogService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
