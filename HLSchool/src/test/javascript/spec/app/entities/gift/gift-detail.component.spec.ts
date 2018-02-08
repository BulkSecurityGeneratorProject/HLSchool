/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { HlSchoolTestModule } from '../../../test.module';
import { GiftDetailComponent } from '../../../../../../main/webapp/app/entities/gift/gift-detail.component';
import { GiftService } from '../../../../../../main/webapp/app/entities/gift/gift.service';
import { Gift } from '../../../../../../main/webapp/app/entities/gift/gift.model';

describe('Component Tests', () => {

    describe('Gift Management Detail Component', () => {
        let comp: GiftDetailComponent;
        let fixture: ComponentFixture<GiftDetailComponent>;
        let service: GiftService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [GiftDetailComponent],
                providers: [
                    GiftService
                ]
            })
            .overrideTemplate(GiftDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Gift(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.gift).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
