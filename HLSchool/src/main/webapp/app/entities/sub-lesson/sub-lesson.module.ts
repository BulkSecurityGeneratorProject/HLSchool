import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import {
    SubLessonService,
    SubLessonPopupService,
    SubLessonComponent,
    SubLessonDetailComponent,
    SubLessonDialogComponent,
    SubLessonPopupComponent,
    SubLessonDeletePopupComponent,
    SubLessonDeleteDialogComponent,
    subLessonRoute,
    subLessonPopupRoute,
    SubLessonResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...subLessonRoute,
    ...subLessonPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SubLessonComponent,
        SubLessonDetailComponent,
        SubLessonDialogComponent,
        SubLessonDeleteDialogComponent,
        SubLessonPopupComponent,
        SubLessonDeletePopupComponent,
    ],
    entryComponents: [
        SubLessonComponent,
        SubLessonDialogComponent,
        SubLessonPopupComponent,
        SubLessonDeleteDialogComponent,
        SubLessonDeletePopupComponent,
    ],
    providers: [
        SubLessonService,
        SubLessonPopupService,
        SubLessonResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolSubLessonModule {}
