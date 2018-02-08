import { BaseEntity } from './../../shared';

export const enum QuestionType {
    'TRANSLATION',
    'LISTENING',
    'SELECTION',
    'SPEECH'
}

export class Question implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public questionType?: QuestionType,
        public contenten?: string,
        public contentvi?: string,
        public imageContentType?: string,
        public image?: any,
        public resourceContentType?: string,
        public resource?: any,
        public lessonId?: number,
    ) {
    }
}
