/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { GiftComponent } from '../../../../../../main/webapp/app/entities/gift/gift.component';
import { GiftService } from '../../../../../../main/webapp/app/entities/gift/gift.service';
import { Gift } from '../../../../../../main/webapp/app/entities/gift/gift.model';

describe('Component Tests', () => {

    describe('Gift Management Component', () => {
        let comp: GiftComponent;
        let fixture: ComponentFixture<GiftComponent>;
        let service: GiftService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [GiftComponent],
                providers: [
                    GiftService
                ]
            })
            .overrideTemplate(GiftComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Gift(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.gifts[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
