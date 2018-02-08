/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { HlSchoolTestModule } from '../../../test.module';
import { ConfigDetailComponent } from '../../../../../../main/webapp/app/entities/config/config-detail.component';
import { ConfigService } from '../../../../../../main/webapp/app/entities/config/config.service';
import { Config } from '../../../../../../main/webapp/app/entities/config/config.model';

describe('Component Tests', () => {

    describe('Config Management Detail Component', () => {
        let comp: ConfigDetailComponent;
        let fixture: ComponentFixture<ConfigDetailComponent>;
        let service: ConfigService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [ConfigDetailComponent],
                providers: [
                    ConfigService
                ]
            })
            .overrideTemplate(ConfigDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfigDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Config(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.config).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
