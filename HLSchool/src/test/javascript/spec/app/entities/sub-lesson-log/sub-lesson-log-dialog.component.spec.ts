/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { SubLessonLogDialogComponent } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log-dialog.component';
import { SubLessonLogService } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log.service';
import { SubLessonLog } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log.model';
import { UserService } from '../../../../../../main/webapp/app/shared';
import { SubLessonService } from '../../../../../../main/webapp/app/entities/sub-lesson';

describe('Component Tests', () => {

    describe('SubLessonLog Management Dialog Component', () => {
        let comp: SubLessonLogDialogComponent;
        let fixture: ComponentFixture<SubLessonLogDialogComponent>;
        let service: SubLessonLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [SubLessonLogDialogComponent],
                providers: [
                    UserService,
                    SubLessonService,
                    SubLessonLogService
                ]
            })
            .overrideTemplate(SubLessonLogDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubLessonLogDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubLessonLogService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SubLessonLog(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.subLessonLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'subLessonLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SubLessonLog();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.subLessonLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'subLessonLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
