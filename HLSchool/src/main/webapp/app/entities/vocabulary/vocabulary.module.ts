import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import {
    VocabularyService,
    VocabularyPopupService,
    VocabularyComponent,
    VocabularyDetailComponent,
    VocabularyDialogComponent,
    VocabularyPopupComponent,
    VocabularyDeletePopupComponent,
    VocabularyDeleteDialogComponent,
    vocabularyRoute,
    vocabularyPopupRoute,
    VocabularyResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...vocabularyRoute,
    ...vocabularyPopupRoute,
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        VocabularyComponent,
        VocabularyDetailComponent,
        VocabularyDialogComponent,
        VocabularyDeleteDialogComponent,
        VocabularyPopupComponent,
        VocabularyDeletePopupComponent,
    ],
    entryComponents: [
        VocabularyComponent,
        VocabularyDialogComponent,
        VocabularyPopupComponent,
        VocabularyDeleteDialogComponent,
        VocabularyDeletePopupComponent,
    ],
    providers: [
        VocabularyService,
        VocabularyPopupService,
        VocabularyResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolVocabularyModule {}
