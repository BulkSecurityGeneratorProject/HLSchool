import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { playRoute } from './play.route';
import { ClientPlayComponent} from './play.component';
import { QuestionService } from './question.service';
import { AnswerService } from './answer.service';

const ENTITY_STATES = [
    ...playRoute
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ClientPlayComponent
    ],
    entryComponents: [
        ClientPlayComponent,
    ],
    providers: [
        QuestionService,
        AnswerService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientPlayModule {}
