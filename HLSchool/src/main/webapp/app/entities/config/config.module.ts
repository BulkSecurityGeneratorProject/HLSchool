import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import {
    ConfigService,
    ConfigPopupService,
    ConfigComponent,
    ConfigDetailComponent,
    ConfigDialogComponent,
    ConfigPopupComponent,
    ConfigDeletePopupComponent,
    ConfigDeleteDialogComponent,
    configRoute,
    configPopupRoute,
    ConfigResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...configRoute,
    ...configPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConfigComponent,
        ConfigDetailComponent,
        ConfigDialogComponent,
        ConfigDeleteDialogComponent,
        ConfigPopupComponent,
        ConfigDeletePopupComponent,
    ],
    entryComponents: [
        ConfigComponent,
        ConfigDialogComponent,
        ConfigPopupComponent,
        ConfigDeleteDialogComponent,
        ConfigDeletePopupComponent,
    ],
    providers: [
        ConfigService,
        ConfigPopupService,
        ConfigResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolConfigModule {}
