/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { HlSchoolTestModule } from '../../../test.module';
import { SubLessonDetailComponent } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson-detail.component';
import { SubLessonService } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson.service';
import { SubLesson } from '../../../../../../main/webapp/app/entities/sub-lesson/sub-lesson.model';

describe('Component Tests', () => {

    describe('SubLesson Management Detail Component', () => {
        let comp: SubLessonDetailComponent;
        let fixture: ComponentFixture<SubLessonDetailComponent>;
        let service: SubLessonService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [SubLessonDetailComponent],
                providers: [
                    SubLessonService
                ]
            })
            .overrideTemplate(SubLessonDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubLessonDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubLessonService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new SubLesson(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.subLesson).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
