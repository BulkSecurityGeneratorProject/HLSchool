import { BaseEntity } from './../../shared';

export class SubLessonLog implements BaseEntity {
    constructor(
        public id?: number,
        public complete?: boolean,
        public rawData?: any,
        public userId?: number,
        public subLessonId?: number,
    ) {
        this.complete = false;
    }
}
