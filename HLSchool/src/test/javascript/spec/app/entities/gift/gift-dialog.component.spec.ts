/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { GiftDialogComponent } from '../../../../../../main/webapp/app/entities/gift/gift-dialog.component';
import { GiftService } from '../../../../../../main/webapp/app/entities/gift/gift.service';
import { Gift } from '../../../../../../main/webapp/app/entities/gift/gift.model';

describe('Component Tests', () => {

    describe('Gift Management Dialog Component', () => {
        let comp: GiftDialogComponent;
        let fixture: ComponentFixture<GiftDialogComponent>;
        let service: GiftService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [GiftDialogComponent],
                providers: [
                    GiftService
                ]
            })
            .overrideTemplate(GiftDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Gift(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.gift = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'giftListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Gift();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.gift = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'giftListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
