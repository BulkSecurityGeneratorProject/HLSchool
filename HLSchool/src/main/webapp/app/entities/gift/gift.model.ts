import { BaseEntity } from './../../shared';

export class Gift implements BaseEntity {
    constructor(
        public id?: number,
        public price?: number,
        public imageContentType?: string,
        public image?: any,
        public content?: string,
        public createDate?: any,
    ) {
    }
}
