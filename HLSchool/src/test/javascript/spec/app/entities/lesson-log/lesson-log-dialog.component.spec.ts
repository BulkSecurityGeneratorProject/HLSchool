/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { LessonLogDialogComponent } from '../../../../../../main/webapp/app/entities/lesson-log/lesson-log-dialog.component';
import { LessonLogService } from '../../../../../../main/webapp/app/entities/lesson-log/lesson-log.service';
import { LessonLog } from '../../../../../../main/webapp/app/entities/lesson-log/lesson-log.model';
import { UserService } from '../../../../../../main/webapp/app/shared';
import { LessonService } from '../../../../../../main/webapp/app/entities/lesson';

describe('Component Tests', () => {

    describe('LessonLog Management Dialog Component', () => {
        let comp: LessonLogDialogComponent;
        let fixture: ComponentFixture<LessonLogDialogComponent>;
        let service: LessonLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [LessonLogDialogComponent],
                providers: [
                    UserService,
                    LessonService,
                    LessonLogService
                ]
            })
            .overrideTemplate(LessonLogDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LessonLogDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LessonLogService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LessonLog(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.lessonLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'lessonLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LessonLog();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.lessonLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'lessonLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
