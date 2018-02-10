import { BaseEntity } from './../../shared';

export class Room implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public level?: number,
        public title?: string,
        public rawData?: any,
    ) {
    }
}
