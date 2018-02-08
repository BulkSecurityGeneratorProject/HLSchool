import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { HlSchoolAdminModule } from '../../admin/admin.module';
import {
    CommentService,
    CommentPopupService,
    CommentComponent,
    CommentDetailComponent,
    CommentDialogComponent,
    CommentPopupComponent,
    CommentDeletePopupComponent,
    CommentDeleteDialogComponent,
    commentRoute,
    commentPopupRoute,
    CommentResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...commentRoute,
    ...commentPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        HlSchoolAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CommentComponent,
        CommentDetailComponent,
        CommentDialogComponent,
        CommentDeleteDialogComponent,
        CommentPopupComponent,
        CommentDeletePopupComponent,
    ],
    entryComponents: [
        CommentComponent,
        CommentDialogComponent,
        CommentPopupComponent,
        CommentDeleteDialogComponent,
        CommentDeletePopupComponent,
    ],
    providers: [
        CommentService,
        CommentPopupService,
        CommentResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolCommentModule {}
