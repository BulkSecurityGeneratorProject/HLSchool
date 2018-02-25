import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../../shared';
import { courseRoute } from './course.route';
import { ClientCourseComponent } from './course.component';
import { ClientCourseDetailComponent } from './course-detail.component';
import { CourseService } from './course.service';
import { CourseLogService } from './course-log.service';

const ENTITY_STATES = [
    ...courseRoute
];

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ClientCourseComponent,
        ClientCourseDetailComponent
    ],
    entryComponents: [
        ClientCourseComponent,
        ClientCourseDetailComponent
    ],
    providers: [
        CourseService,
        CourseLogService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientCourseModule {}
