import { BaseEntity } from './../shared';

export class Post implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public contenten?: any,
        public contentvi?: any,
        public createDate?: any,
        public lastModifier?: any,
        public activated?: boolean,
        public rawData?: any,
        public userId?: number,
        public comments?: BaseEntity[],
    ) {
        this.activated = false;
    }
}
