import { BaseEntity } from './../../shared';

export const enum QuestionType {
    'TRANSLATION',
    'LISTENING',
    'SELECTION',
    'SPEECH'
}

export const enum QuestionSubType {
    'SELECTION_IMAGE',
    'SELECTION_TEXT',
    'SELECTION_SPEECH'
}

export class Question implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public questionType?: QuestionType,
        public questionSubType?: QuestionSubType,
        public contenten?: string,
        public contentvi?: string,
        public imageContentType?: string,
        public image?: any,
        public resourceContentType?: string,
        public resource?: any,
        public rawData?: any,
        public subLessonId?: number,
    ) {
    }
}
