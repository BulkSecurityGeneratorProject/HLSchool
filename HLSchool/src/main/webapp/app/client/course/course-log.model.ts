import { BaseEntity } from './../../shared';

export class CourseLog implements BaseEntity {
    constructor(
        public id?: number,
        public complete?: boolean,
        public rawData?: any,
        public userId?: number,
        public courseId?: number,
    ) {
        this.complete = false;
    }
}
