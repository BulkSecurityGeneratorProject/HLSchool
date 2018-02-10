/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { HlSchoolTestModule } from '../../../test.module';
import { CourseLogDetailComponent } from '../../../../../../main/webapp/app/entities/course-log/course-log-detail.component';
import { CourseLogService } from '../../../../../../main/webapp/app/entities/course-log/course-log.service';
import { CourseLog } from '../../../../../../main/webapp/app/entities/course-log/course-log.model';

describe('Component Tests', () => {

    describe('CourseLog Management Detail Component', () => {
        let comp: CourseLogDetailComponent;
        let fixture: ComponentFixture<CourseLogDetailComponent>;
        let service: CourseLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [CourseLogDetailComponent],
                providers: [
                    CourseLogService
                ]
            })
            .overrideTemplate(CourseLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CourseLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CourseLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new CourseLog(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.courseLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
