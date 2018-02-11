import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

// import { HlSchoolConfigModule } from './config/config.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */
import { ClientCourseModule } from './course/course.module';
import { ClientShopModule } from './shop/shop.module';
import { ClientLessonModule } from './lesson/lesson.module';

@NgModule({
    imports: [
        ClientCourseModule,
        ClientShopModule,
        ClientLessonModule
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolClientModule {}
