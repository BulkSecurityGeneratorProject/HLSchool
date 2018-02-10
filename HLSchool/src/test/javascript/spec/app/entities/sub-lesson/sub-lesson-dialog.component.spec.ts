/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { SubLessonDialogComponent } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson-dialog.component';
import { SubLessonService } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson.service';
import { SubLesson } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson.model';

describe('Component Tests', () => {

    describe('SubLesson Management Dialog Component', () => {
        let comp: SubLessonDialogComponent;
        let fixture: ComponentFixture<SubLessonDialogComponent>;
        let service: SubLessonService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [SubLessonDialogComponent],
                providers: [
                    SubLessonService
                ]
            })
            .overrideTemplate(SubLessonDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubLessonDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubLessonService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SubLesson(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.subLesson = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'subLessonListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SubLesson();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.subLesson = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'subLessonListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
