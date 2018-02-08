/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { VocabularyComponent } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary.component';
import { VocabularyService } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary.service';
import { Vocabulary } from '../../../../../../main/webapp/app/entities/vocabulary/vocabulary.model';

describe('Component Tests', () => {

    describe('Vocabulary Management Component', () => {
        let comp: VocabularyComponent;
        let fixture: ComponentFixture<VocabularyComponent>;
        let service: VocabularyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [VocabularyComponent],
                providers: [
                    VocabularyService
                ]
            })
            .overrideTemplate(VocabularyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VocabularyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VocabularyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Vocabulary(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.vocabularies[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
