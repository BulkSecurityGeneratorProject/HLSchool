/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { VocabularyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary-delete-dialog.component';
import { VocabularyService } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary.service';

describe('Component Tests', () => {

    describe('Vocabulary Management Delete Component', () => {
        let comp: VocabularyDeleteDialogComponent;
        let fixture: ComponentFixture<VocabularyDeleteDialogComponent>;
        let service: VocabularyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [VocabularyDeleteDialogComponent],
                providers: [
                    VocabularyService
                ]
            })
            .overrideTemplate(VocabularyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VocabularyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VocabularyService);
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
