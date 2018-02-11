import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { roomRoute } from './room.route';
import { ClientRoomComponent} from './room.component';
import { ClientRoomDetailComponent} from './room-detail.component';
import { RoomService } from './room.service';

const ENTITY_STATES = [
    ...roomRoute
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ClientRoomComponent,
        ClientRoomDetailComponent
    ],
    entryComponents: [
        ClientRoomComponent,
        ClientRoomDetailComponent
    ],
    providers: [
        RoomService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientRoomModule {}
