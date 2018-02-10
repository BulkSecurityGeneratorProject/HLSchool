import { BaseEntity } from './../../shared';

export class LessonLog implements BaseEntity {
    constructor(
        public id?: number,
        public complete?: boolean,
        public rawData?: any,
        public userId?: number,
        public lessonId?: number,
    ) {
        this.complete = false;
    }
}
