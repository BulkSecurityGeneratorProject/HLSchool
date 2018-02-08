import { BaseEntity } from './../../shared';

export class UserLog implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public complete?: boolean,
        public point?: number,
    ) {
        this.complete = false;
    }
}
