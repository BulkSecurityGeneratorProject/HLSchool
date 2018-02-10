import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { HlSchoolAdminModule } from '../../admin/admin.module';
import {
    LessonLogService,
    LessonLogPopupService,
    LessonLogComponent,
    LessonLogDetailComponent,
    LessonLogDialogComponent,
    LessonLogPopupComponent,
    LessonLogDeletePopupComponent,
    LessonLogDeleteDialogComponent,
    lessonLogRoute,
    lessonLogPopupRoute,
    LessonLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...lessonLogRoute,
    ...lessonLogPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        HlSchoolAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LessonLogComponent,
        LessonLogDetailComponent,
        LessonLogDialogComponent,
        LessonLogDeleteDialogComponent,
        LessonLogPopupComponent,
        LessonLogDeletePopupComponent,
    ],
    entryComponents: [
        LessonLogComponent,
        LessonLogDialogComponent,
        LessonLogPopupComponent,
        LessonLogDeleteDialogComponent,
        LessonLogDeletePopupComponent,
    ],
    providers: [
        LessonLogService,
        LessonLogPopupService,
        LessonLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolLessonLogModule {}
