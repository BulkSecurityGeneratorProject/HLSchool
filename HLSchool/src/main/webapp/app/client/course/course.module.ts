import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { courseRoute } from './course.route';
import { ClientCourseComponent} from './course.component';
import { CourseService } from './course.service';

const ENTITY_STATES = [
    ...courseRoute
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ClientCourseComponent
    ],
    entryComponents: [
        ClientCourseComponent,
    ],
    providers: [
        CourseService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientCourseModule {}
