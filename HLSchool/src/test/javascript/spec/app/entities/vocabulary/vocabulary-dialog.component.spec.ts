/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { VocabularyDialogComponent } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary-dialog.component';
import { VocabularyService } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary.service';
import { Vocabulary } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary.model';

describe('Component Tests', () => {

    describe('Vocabulary Management Dialog Component', () => {
        let comp: VocabularyDialogComponent;
        let fixture: ComponentFixture<VocabularyDialogComponent>;
        let service: VocabularyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [VocabularyDialogComponent],
                providers: [
                    VocabularyService
                ]
            })
            .overrideTemplate(VocabularyDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VocabularyDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VocabularyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Vocabulary(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.vocabulary = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'vocabularyListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Vocabulary();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.vocabulary = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'vocabularyListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
