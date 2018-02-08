import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import {
    AnswerService,
    AnswerPopupService,
    AnswerComponent,
    AnswerDetailComponent,
    AnswerDialogComponent,
    AnswerPopupComponent,
    AnswerDeletePopupComponent,
    AnswerDeleteDialogComponent,
    answerRoute,
    answerPopupRoute,
    AnswerResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...answerRoute,
    ...answerPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AnswerComponent,
        AnswerDetailComponent,
        AnswerDialogComponent,
        AnswerDeleteDialogComponent,
        AnswerPopupComponent,
        AnswerDeletePopupComponent,
    ],
    entryComponents: [
        AnswerComponent,
        AnswerDialogComponent,
        AnswerPopupComponent,
        AnswerDeleteDialogComponent,
        AnswerDeletePopupComponent,
    ],
    providers: [
        AnswerService,
        AnswerPopupService,
        AnswerResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolAnswerModule {}
