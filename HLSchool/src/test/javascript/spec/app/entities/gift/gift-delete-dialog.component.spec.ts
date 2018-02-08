/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { GiftDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/gift/gift-delete-dialog.component';
import { GiftService } from '../../../../../../main/webapp/app/entities/gift/gift.service';

describe('Component Tests', () => {

    describe('Gift Management Delete Component', () => {
        let comp: GiftDeleteDialogComponent;
        let fixture: ComponentFixture<GiftDeleteDialogComponent>;
        let service: GiftService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [GiftDeleteDialogComponent],
                providers: [
                    GiftService
                ]
            })
            .overrideTemplate(GiftDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftService);
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
