import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import {
    LessonService,
    LessonPopupService,
    LessonComponent,
    LessonDetailComponent,
    LessonDialogComponent,
    LessonPopupComponent,
    LessonDeletePopupComponent,
    LessonDeleteDialogComponent,
    lessonRoute,
    lessonPopupRoute,
    LessonResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...lessonRoute,
    ...lessonPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LessonComponent,
        LessonDetailComponent,
        LessonDialogComponent,
        LessonDeleteDialogComponent,
        LessonPopupComponent,
        LessonDeletePopupComponent,
    ],
    entryComponents: [
        LessonComponent,
        LessonDialogComponent,
        LessonPopupComponent,
        LessonDeleteDialogComponent,
        LessonDeletePopupComponent,
    ],
    providers: [
        LessonService,
        LessonPopupService,
        LessonResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolLessonModule {}
