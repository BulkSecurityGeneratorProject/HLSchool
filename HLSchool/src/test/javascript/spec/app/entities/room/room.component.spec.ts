/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { HlSchoolTestModule } from '../../../test.module';
import { RoomComponent } from '../../../../../../main/webapp/app/entities/room/room.component';
import { RoomService } from '../../../../../../main/webapp/app/entities/room/room.service';
import { Room } from '../../../../../../main/webapp/app/entities/room/room.model';

describe('Component Tests', () => {

    describe('Room Management Component', () => {
        let comp: RoomComponent;
        let fixture: ComponentFixture<RoomComponent>;
        let service: RoomService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [HlSchoolTestModule],
                declarations: [RoomComponent],
                providers: [
                    RoomService
                ]
            })
            .overrideTemplate(RoomComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RoomComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RoomService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Room(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.rooms[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
