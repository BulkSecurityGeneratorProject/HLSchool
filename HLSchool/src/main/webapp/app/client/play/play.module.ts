import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { playRoute } from './play.route';
import { ClientPlayComponent} from './play.component';

const ENTITY_STATES = [
    ...playRoute
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ClientPlayComponent
    ],
    entryComponents: [
        ClientPlayComponent,
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientPlayModule {}
