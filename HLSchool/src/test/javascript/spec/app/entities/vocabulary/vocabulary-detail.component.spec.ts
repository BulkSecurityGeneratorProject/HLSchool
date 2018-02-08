/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { HlSchoolTestModule } from '../../../test.module';
import { VocabularyDetailComponent } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary-detail.component';
import { VocabularyService } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary.service';
import { Vocabulary } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary.model';

describe('Component Tests', () => {

    describe('Vocabulary Management Detail Component', () => {
        let comp: VocabularyDetailComponent;
        let fixture: ComponentFixture<VocabularyDetailComponent>;
        let service: VocabularyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [VocabularyDetailComponent],
                providers: [
                    VocabularyService
                ]
            })
            .overrideTemplate(VocabularyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VocabularyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VocabularyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Vocabulary(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.vocabulary).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
