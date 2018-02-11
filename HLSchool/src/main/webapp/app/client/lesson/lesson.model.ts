import { BaseEntity } from './../../shared';

export class Lesson implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public activated?: boolean,
        public title?: string,
        public level?: number,
        public contenten?: any,
        public contentvi?: any,
        public imageContentType?: string,
        public image?: any,
        public rawData?: any,
        public courseId?: number,
    ) {
        this.activated = false;
    }
}
