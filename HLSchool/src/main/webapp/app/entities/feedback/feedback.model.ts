import { BaseEntity } from './../../shared';

export class Feedback implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public content?: string,
        public rawData?: any,
        public questionId?: number,
        public userId?: number,
    ) {
    }
}
