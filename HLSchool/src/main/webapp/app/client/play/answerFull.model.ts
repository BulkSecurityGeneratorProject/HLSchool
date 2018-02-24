import { BaseEntity } from './../../shared';

export class AnswerFull implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public result?: boolean,
        public rawData?: any,
        public questionId?: number,
        public vocabularyId?: number,
        public question?: any,
        public vocabulary?: any
    ) {
        this.result = false;
    }
}
