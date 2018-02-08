import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { shopRoute } from './shop.route';
import { ClientShopComponent} from './shop.component';

const ENTITY_STATES = [
    ...shopRoute
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ClientShopComponent
    ],
    entryComponents: [
        ClientShopComponent,
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientShopModule {}
