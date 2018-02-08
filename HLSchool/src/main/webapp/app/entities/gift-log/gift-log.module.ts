import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { HlSchoolAdminModule } from '../../admin/admin.module';
import {
    GiftLogService,
    GiftLogPopupService,
    GiftLogComponent,
    GiftLogDetailComponent,
    GiftLogDialogComponent,
    GiftLogPopupComponent,
    GiftLogDeletePopupComponent,
    GiftLogDeleteDialogComponent,
    giftLogRoute,
    giftLogPopupRoute,
    GiftLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...giftLogRoute,
    ...giftLogPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        HlSchoolAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        GiftLogComponent,
        GiftLogDetailComponent,
        GiftLogDialogComponent,
        GiftLogDeleteDialogComponent,
        GiftLogPopupComponent,
        GiftLogDeletePopupComponent,
    ],
    entryComponents: [
        GiftLogComponent,
        GiftLogDialogComponent,
        GiftLogPopupComponent,
        GiftLogDeleteDialogComponent,
        GiftLogDeletePopupComponent,
    ],
    providers: [
        GiftLogService,
        GiftLogPopupService,
        GiftLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolGiftLogModule {}
