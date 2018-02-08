import { BaseEntity } from './../../shared';

export class Answer implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public result?: boolean,
        public questionId?: number,
        public vocabularyId?: number,
    ) {
        this.result = false;
    }
}
