import { BaseEntity } from './../../shared';

export class UserLog implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public complete?: boolean,
        public point?: number,
        public rawData?: any,
        public userId?: number,
    ) {
        this.complete = false;
    }
}
