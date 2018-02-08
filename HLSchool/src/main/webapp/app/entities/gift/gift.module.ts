import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import {
    GiftService,
    GiftPopupService,
    GiftComponent,
    GiftDetailComponent,
    GiftDialogComponent,
    GiftPopupComponent,
    GiftDeletePopupComponent,
    GiftDeleteDialogComponent,
    giftRoute,
    giftPopupRoute,
    GiftResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...giftRoute,
    ...giftPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        GiftComponent,
        GiftDetailComponent,
        GiftDialogComponent,
        GiftDeleteDialogComponent,
        GiftPopupComponent,
        GiftDeletePopupComponent,
    ],
    entryComponents: [
        GiftComponent,
        GiftDialogComponent,
        GiftPopupComponent,
        GiftDeleteDialogComponent,
        GiftDeletePopupComponent,
    ],
    providers: [
        GiftService,
        GiftPopupService,
        GiftResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolGiftModule {}
