import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { lessonRoute } from './lesson.route';
import { ClientLessonComponent} from './lesson.component';
import { LessonService } from './lesson.service';
import { ConfigService } from './config.service';

const ENTITY_STATES = [
    ...lessonRoute
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ClientLessonComponent
    ],
    entryComponents: [
        ClientLessonComponent,
    ],
    providers: [
        LessonService,
        ConfigService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientLessonModule {}
