/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { SubLessonLogComponent } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log.component';
import { SubLessonLogService } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log.service';
import { SubLessonLog } from '../../../../../../main/webapp/app/entities/sub-lesson-log/sub-lesson-log.model';

describe('Component Tests', () => {

    describe('SubLessonLog Management Component', () => {
        let comp: SubLessonLogComponent;
        let fixture: ComponentFixture<SubLessonLogComponent>;
        let service: SubLessonLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [SubLessonLogComponent],
                providers: [
                    SubLessonLogService
                ]
            })
            .overrideTemplate(SubLessonLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubLessonLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubLessonLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new SubLessonLog(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.subLessonLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
