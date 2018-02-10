import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { HlSchoolAdminModule } from '../../admin/admin.module';
import {
    SubLessonLogService,
    SubLessonLogPopupService,
    SubLessonLogComponent,
    SubLessonLogDetailComponent,
    SubLessonLogDialogComponent,
    SubLessonLogPopupComponent,
    SubLessonLogDeletePopupComponent,
    SubLessonLogDeleteDialogComponent,
    subLessonLogRoute,
    subLessonLogPopupRoute,
    SubLessonLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...subLessonLogRoute,
    ...subLessonLogPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        HlSchoolAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SubLessonLogComponent,
        SubLessonLogDetailComponent,
        SubLessonLogDialogComponent,
        SubLessonLogDeleteDialogComponent,
        SubLessonLogPopupComponent,
        SubLessonLogDeletePopupComponent,
    ],
    entryComponents: [
        SubLessonLogComponent,
        SubLessonLogDialogComponent,
        SubLessonLogPopupComponent,
        SubLessonLogDeleteDialogComponent,
        SubLessonLogDeletePopupComponent,
    ],
    providers: [
        SubLessonLogService,
        SubLessonLogPopupService,
        SubLessonLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolSubLessonLogModule {}
