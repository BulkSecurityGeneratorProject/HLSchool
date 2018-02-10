/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { SubLessonComponent } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson.component';
import { SubLessonService } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson.service';
import { SubLesson } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson.model';

describe('Component Tests', () => {

    describe('SubLesson Management Component', () => {
        let comp: SubLessonComponent;
        let fixture: ComponentFixture<SubLessonComponent>;
        let service: SubLessonService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [SubLessonComponent],
                providers: [
                    SubLessonService
                ]
            })
            .overrideTemplate(SubLessonComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubLessonComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubLessonService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new SubLesson(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.subLessons[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
