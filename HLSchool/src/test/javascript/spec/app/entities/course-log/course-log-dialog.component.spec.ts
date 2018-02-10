/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { HlSchoolTestModule } from '../../../test.module';
import { CourseLogDialogComponent } from '../../../../../../main/webapp/app/entities/course-log/course-log-dialog.component';
import { CourseLogService } from '../../../../../../main/webapp/app/entities/course-log/course-log.service';
import { CourseLog } from '../../../../../../main/webapp/app/entities/course-log/course-log.model';
import { UserService } from '../../../../../../main/webapp/app/shared';
import { CourseService } from '../../../../../../main/webapp/app/entities/course';

describe('Component Tests', () => {

    describe('CourseLog Management Dialog Component', () => {
        let comp: CourseLogDialogComponent;
        let fixture: ComponentFixture<CourseLogDialogComponent>;
        let service: CourseLogService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [CourseLogDialogComponent],
                providers: [
                    UserService,
                    CourseService,
                    CourseLogService
                ]
            })
            .overrideTemplate(CourseLogDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CourseLogDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CourseLogService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CourseLog(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.courseLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'courseLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CourseLog();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.courseLog = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'courseLogListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
