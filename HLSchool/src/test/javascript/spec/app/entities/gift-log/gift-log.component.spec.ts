/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { GiftLogComponent } from '../../../../../../main/webapp/app/entities/gift-log/gift-log.component';
import { GiftLogService } from '../../../../../../main/webapp/app/entities/gift-log/gift-log.service';
import { GiftLog } from '../../../../../../main/webapp/app/entities/gift-log/gift-log.model';

describe('Component Tests', () => {

    describe('GiftLog Management Component', () => {
        let comp: GiftLogComponent;
        let fixture: ComponentFixture<GiftLogComponent>;
        let service: GiftLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [GiftLogComponent],
                providers: [
                    GiftLogService
                ]
            })
            .overrideTemplate(GiftLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new GiftLog(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.giftLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
