import { BaseEntity } from './../../shared';

export class Comment implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public content?: any,
        public rawData?: any,
        public postId?: number,
        public userId?: number,
    ) {
    }
}
