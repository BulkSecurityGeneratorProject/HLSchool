import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { HlSchoolAdminModule } from '../../admin/admin.module';
import {
    CourseLogService,
    CourseLogPopupService,
    CourseLogComponent,
    CourseLogDetailComponent,
    CourseLogDialogComponent,
    CourseLogPopupComponent,
    CourseLogDeletePopupComponent,
    CourseLogDeleteDialogComponent,
    courseLogRoute,
    courseLogPopupRoute,
    CourseLogResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...courseLogRoute,
    ...courseLogPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        HlSchoolAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CourseLogComponent,
        CourseLogDetailComponent,
        CourseLogDialogComponent,
        CourseLogDeleteDialogComponent,
        CourseLogPopupComponent,
        CourseLogDeletePopupComponent,
    ],
    entryComponents: [
        CourseLogComponent,
        CourseLogDialogComponent,
        CourseLogPopupComponent,
        CourseLogDeleteDialogComponent,
        CourseLogDeletePopupComponent,
    ],
    providers: [
        CourseLogService,
        CourseLogPopupService,
        CourseLogResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolCourseLogModule {}
