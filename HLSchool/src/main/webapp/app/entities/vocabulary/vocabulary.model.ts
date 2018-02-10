import { BaseEntity } from './../../shared';

export class Vocabulary implements BaseEntity {
    constructor(
        public id?: number,
        public japanese?: string,
        public english?: string,
        public vietnamese?: string,
        public imageContentType?: string,
        public image?: any,
        public audioContentType?: string,
        public audio?: any,
        public rawData?: any,
    ) {
    }
}
