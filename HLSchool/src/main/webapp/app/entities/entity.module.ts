import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HlSchoolConfigModule } from './config/config.module';
import { HlSchoolVocabularyModule } from './vocabulary/vocabulary.module';
import { HlSchoolPostModule } from './post/post.module';
import { HlSchoolCommentModule } from './comment/comment.module';
import { HlSchoolRoomModule } from './room/room.module';
import { HlSchoolCourseModule } from './course/course.module';
import { HlSchoolLessonModule } from './lesson/lesson.module';
import { HlSchoolFeedbackModule } from './feedback/feedback.module';
import { HlSchoolQuestionModule } from './question/question.module';
import { HlSchoolAnswerModule } from './answer/answer.module';
import { HlSchoolUserLogModule } from './user-log/user-log.module';
import { HlSchoolGiftLogModule } from './gift-log/gift-log.module';
import { HlSchoolGiftModule } from './gift/gift.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        HlSchoolConfigModule,
        HlSchoolVocabularyModule,
        HlSchoolPostModule,
        HlSchoolCommentModule,
        HlSchoolRoomModule,
        HlSchoolCourseModule,
        HlSchoolLessonModule,
        HlSchoolFeedbackModule,
        HlSchoolQuestionModule,
        HlSchoolAnswerModule,
        HlSchoolUserLogModule,
        HlSchoolGiftLogModule,
        HlSchoolGiftModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolEntityModule {}
