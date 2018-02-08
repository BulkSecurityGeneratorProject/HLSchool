/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { GiftLogDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/gift-log/gift-log-delete-dialog.component';
import { GiftLogService } from '../../../../../../main/webapp/app/entities/gift-log/gift-log.service';

describe('Component Tests', () => {

    describe('GiftLog Management Delete Component', () => {
        let comp: GiftLogDeleteDialogComponent;
        let fixture: ComponentFixture<GiftLogDeleteDialogComponent>;
        let service: GiftLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [GiftLogDeleteDialogComponent],
                providers: [
                    GiftLogService
                ]
            })
            .overrideTemplate(GiftLogDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftLogDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftLogService);
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
