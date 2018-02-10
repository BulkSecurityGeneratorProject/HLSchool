/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { HlSchoolTestModule } from '../../../test.module';
import { SubLessonLogDetailComponent } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log-detail.component';
import { SubLessonLogService } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log.service';
import { SubLessonLog } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log.model';

describe('Component Tests', () => {

    describe('SubLessonLog Management Detail Component', () => {
        let comp: SubLessonLogDetailComponent;
        let fixture: ComponentFixture<SubLessonLogDetailComponent>;
        let service: SubLessonLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [SubLessonLogDetailComponent],
                providers: [
                    SubLessonLogService
                ]
            })
            .overrideTemplate(SubLessonLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubLessonLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubLessonLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new SubLessonLog(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.subLessonLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
