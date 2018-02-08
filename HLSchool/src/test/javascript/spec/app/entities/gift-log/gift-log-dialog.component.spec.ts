/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { GiftLogDialogComponent } from '../../../../../../main/webapp/app/entities/gift-log/gift-log-dialog.component';
import { GiftLogService } from '../../../../../../main/webapp/app/entities/gift-log/gift-log.service';
import { GiftLog } from '../../../../../../main/webapp/app/entities/gift-log/gift-log.model';
import { UserService } from '../../../../../../main/webapp/app/shared';
import { GiftService } from '../../../../../../main/webapp/app/entities/gift';

describe('Component Tests', () => {

    describe('GiftLog Management Dialog Component', () => {
        let comp: GiftLogDialogComponent;
        let fixture: ComponentFixture<GiftLogDialogComponent>;
        let service: GiftLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [GiftLogDialogComponent],
                providers: [
                    UserService,
                    GiftService,
                    GiftLogService
                ]
            })
            .overrideTemplate(GiftLogDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftLogDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftLogService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new GiftLog(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.giftLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'giftLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new GiftLog();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.giftLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'giftLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
