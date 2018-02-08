import { BaseEntity } from './../../shared';

export class GiftLog implements BaseEntity {
    constructor(
        public id?: number,
        public createDate?: any,
        public userId?: number,
        public giftId?: number,
    ) {
    }
}
