/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { CourseLogComponent } from '../../../../../../main/webapp/app/entities/course-log/course-log.component';
import { CourseLogService } from '../../../../../../main/webapp/app/entities/course-log/course-log.service';
import { CourseLog } from '../../../../../../main/webapp/app/entities/course-log/course-log.model';

describe('Component Tests', () => {

    describe('CourseLog Management Component', () => {
        let comp: CourseLogComponent;
        let fixture: ComponentFixture<CourseLogComponent>;
        let service: CourseLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [CourseLogComponent],
                providers: [
                    CourseLogService
                ]
            })
            .overrideTemplate(CourseLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CourseLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CourseLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new CourseLog(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.courseLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
