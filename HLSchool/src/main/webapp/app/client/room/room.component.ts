import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Room } from './room.model';
import { RoomService } from './room.service';

@Component({
    selector: 'jhi-client-room',
    templateUrl: './room.component.html',
    styleUrls: [
        'room.scss'
    ]
})
export class ClientRoomComponent implements OnInit, OnDestroy {
    rooms: Room[];
    constructor(
        private router: Router,
        private roomService: RoomService,
        private jhiAlertService: JhiAlertService,
        private storeService: StoreService
    ) {
    }
    ngOnInit() {
        this.loadAll();
    }

    ngOnDestroy() {
    }

    loadAll() {
        this.roomService.query({
            page: 0,
            size: 10,
            sort: null}).subscribe(
            (res: ResponseWrapper) => this.onLoadSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadError(res.json)
        );
    }
    onLoadSuccess(data, headers) {
        this.rooms = data;
    }

    onLoadError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
