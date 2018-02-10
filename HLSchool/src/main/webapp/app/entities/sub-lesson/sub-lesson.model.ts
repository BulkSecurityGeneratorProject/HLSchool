import { BaseEntity } from './../../shared';

export class SubLesson implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public title?: string,
        public contenten?: any,
        public contentvi?: any,
        public rawData?: any,
    ) {
    }
}
