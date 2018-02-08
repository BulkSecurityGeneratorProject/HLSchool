/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { HlSchoolTestModule } from '../../../test.module';
import { UserLogDetailComponent } from '../../../../../../main/webapp/app/entities/user-log/user-log-detail.component';
import { UserLogService } from '../../../../../../main/webapp/app/entities/user-log/user-log.service';
import { UserLog } from '../../../../../../main/webapp/app/entities/user-log/user-log.model';

describe('Component Tests', () => {

    describe('UserLog Management Detail Component', () => {
        let comp: UserLogDetailComponent;
        let fixture: ComponentFixture<UserLogDetailComponent>;
        let service: UserLogService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [UserLogDetailComponent],
                providers: [
                    UserLogService
                ]
            })
            .overrideTemplate(UserLogDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserLogDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserLogService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new UserLog(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.userLog).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
