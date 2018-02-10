import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { HlSchoolAdminModule } from '../../admin/admin.module';
import {
    UserLogService,
    UserLogPopupService,
    UserLogComponent,
    UserLogDetailComponent,
    UserLogDialogComponent,
    UserLogPopupComponent,
    UserLogDeletePopupComponent,
    UserLogDeleteDialogComponent,
    userLogRoute,
    userLogPopupRoute,
    UserLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...userLogRoute,
    ...userLogPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        HlSchoolAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UserLogComponent,
        UserLogDetailComponent,
        UserLogDialogComponent,
        UserLogDeleteDialogComponent,
        UserLogPopupComponent,
        UserLogDeletePopupComponent,
    ],
    entryComponents: [
        UserLogComponent,
        UserLogDialogComponent,
        UserLogPopupComponent,
        UserLogDeleteDialogComponent,
        UserLogDeletePopupComponent,
    ],
    providers: [
        UserLogService,
        UserLogPopupService,
        UserLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolUserLogModule {}
