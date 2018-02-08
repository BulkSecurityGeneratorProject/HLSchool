/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { UserLogComponent } from '../../../../../../main/webapp/app/entities/user-log/user-log.component';
import { UserLogService } from '../../../../../../main/webapp/app/entities/user-log/user-log.service';
import { UserLog } from '../../../../../../main/webapp/app/entities/user-log/user-log.model';

describe('Component Tests', () => {

    describe('UserLog Management Component', () => {
        let comp: UserLogComponent;
        let fixture: ComponentFixture<UserLogComponent>;
        let service: UserLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [UserLogComponent],
                providers: [
                    UserLogService
                ]
            })
            .overrideTemplate(UserLogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new UserLog(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.userLogs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
