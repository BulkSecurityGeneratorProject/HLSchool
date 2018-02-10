import { BaseEntity } from './../../shared';

export class Gift implements BaseEntity {
    constructor(
        public id?: number,
        public price?: number,
        public imageContentType?: string,
        public image?: any,
        public contenten?: any,
        public contentvi?: any,
        public createDate?: any,
        public rawData?: any,
    ) {
    }
}
