/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { HlSchoolTestModule } from '../../../test.module';
import { GiftLogDetailComponent } from '../../../../../../main/webapp/app/entities/gift-log/gift-log-detail.component';
import { GiftLogService } from '../../../../../../main/webapp/app/entities/gift-log/gift-log.service';
import { GiftLog } from '../../../../../../main/webapp/app/entities/gift-log/gift-log.model';

describe('Component Tests', () => {

    describe('GiftLog Management Detail Component', () => {
        let comp: GiftLogDetailComponent;
        let fixture: ComponentFixture<GiftLogDetailComponent>;
        let service: GiftLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [GiftLogDetailComponent],
                providers: [
                    GiftLogService
                ]
            })
            .overrideTemplate(GiftLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new GiftLog(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.giftLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
