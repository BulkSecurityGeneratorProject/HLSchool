/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { ConfigComponent } from '../../../../../../main/webapp/app/entities/config/config.component';
import { ConfigService } from '../../../../../../main/webapp/app/entities/config/config.service';
import { Config } from '../../../../../../main/webapp/app/entities/config/config.model';

describe('Component Tests', () => {

    describe('Config Management Component', () => {
        let comp: ConfigComponent;
        let fixture: ComponentFixture<ConfigComponent>;
        let service: ConfigService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [ConfigComponent],
                providers: [
                    ConfigService
                ]
            })
            .overrideTemplate(ConfigComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfigComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Config(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.configs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
