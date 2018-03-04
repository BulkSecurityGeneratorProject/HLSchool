import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { subLessonRoute } from './sub-lesson.route';
import { ClientSubLessonComponent} from './sub-lesson.component';
import { SubLessonService } from './sub-lesson.service';
import { ConfigService } from './config.service';

const ENTITY_STATES = [
    ...subLessonRoute
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ClientSubLessonComponent
    ],
    entryComponents: [
        ClientSubLessonComponent,
    ],
    providers: [
        SubLessonService,
        ConfigService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientSubLessonModule {}
