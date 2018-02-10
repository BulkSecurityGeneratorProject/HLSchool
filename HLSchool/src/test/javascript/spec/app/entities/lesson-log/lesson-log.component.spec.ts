/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { LessonLogComponent } from '../../../../../../main/webapp/app/entities/lesson-log/lesson-log.component';
import { LessonLogService } from '../../../../../../main/webapp/app/entities/lesson-log/lesson-log.service';
import { LessonLog } from '../../../../../../main/webapp/app/entities/lesson-log/lesson-log.model';

describe('Component Tests', () => {

    describe('LessonLog Management Component', () => {
        let comp: LessonLogComponent;
        let fixture: ComponentFixture<LessonLogComponent>;
        let service: LessonLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [LessonLogComponent],
                providers: [
                    LessonLogService
                ]
            })
            .overrideTemplate(LessonLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LessonLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LessonLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new LessonLog(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.lessonLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
