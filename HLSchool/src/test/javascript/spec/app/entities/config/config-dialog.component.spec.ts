/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { ConfigDialogComponent } from '../../../../../../main/webapp/app/entities/config/config-dialog.component';
import { ConfigService } from '../../../../../../main/webapp/app/entities/config/config.service';
import { Config } from '../../../../../../main/webapp/app/entities/config/config.model';

describe('Component Tests', () => {

    describe('Config Management Dialog Component', () => {
        let comp: ConfigDialogComponent;
        let fixture: ComponentFixture<ConfigDialogComponent>;
        let service: ConfigService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [ConfigDialogComponent],
                providers: [
                    ConfigService
                ]
            })
            .overrideTemplate(ConfigDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfigDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Config(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.config = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'configListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Config();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.config = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'configListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
