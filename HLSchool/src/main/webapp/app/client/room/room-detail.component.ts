import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';
import { Message } from './../../shared/tracker/Message.model';
import { Room } from './room.model';
import { RoomService } from './room.service';
import { Account, Principal } from '../../shared';
import { JhiTrackerService } from './../../shared/tracker/tracker.service';

@Component({
    selector: 'jhi-room-detail',
    templateUrl: './room-detail.component.html',
    styleUrls: [
        'room-detail.scss'
    ]
})
export class ClientRoomDetailComponent implements OnInit, OnDestroy {
    account: Account;
    room: Room;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    message: string;

    constructor(
        private principal: Principal,
        private eventManager: JhiEventManager,
        private roomService: RoomService,
        private route: ActivatedRoute,
        private router: Router,
        public trackerService: JhiTrackerService
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRooms();
    }

    load(id) {
        this.roomService.find(id).subscribe((room) => {
            this.room = room;
            this.thenLoad();
        });
    }
    thenLoad() {
        const that = this;
        this.trackerService.subscribeMessage(this.room.id, (data) => {
            const message: Message = JSON.parse(data.body);
            message.time = that.getShortTime();

            that.trackerService.messages.push(message);
            // that.customerUserService.findByLogin(message.sender).subscribe((customerUser) => {
            //     message.image = customerUser.icon;
            //     message.iconContentType = customerUser.iconContentType;
            //     that.trackerService.messages.push(message);

            //     this.scrollEnd();

            // });
        });
    }
    scrollEnd() {
        const scrollElement = document.getElementsByClassName('panel-body')[0];
        setTimeout(function() {
            scrollElement.scrollTop = scrollElement.scrollHeight;
        }, 300);
    }
    getShortTime() {
        const d = new Date();
        const hh = d.getHours() > 10 ? d.getHours() : '0' + d.getHours();
        const mm = d.getMinutes() > 10 ? d.getMinutes() : '0' + d.getMinutes();
        const ss = d.getSeconds() > 10 ? d.getSeconds() : '0' + d.getSeconds();
        return hh + ':' + mm + ':' + ss;
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRooms() {
        this.eventSubscriber = this.eventManager.subscribe(
            'roomListModification',
            (response) => this.load(this.room.id)
        );
    }

    sendMessage() {
        this.trackerService.sendMessage(this.message, this.room.id);
        this.message = '';
    }

    onOut() {
        this.trackerService.unsubscribeMessage();
        this.router.navigateByUrl('/app/room');
    }
}
