import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { UserService } from '../../shared/user/user.service';

@Component({
    selector: 'jhi-client-shop',
    templateUrl: './shop.component.html',
    styleUrls: [
        'shop.scss'
    ]
})
export class ClientShopComponent implements OnInit, OnDestroy {

    constructor(
        private router: Router,
        private userService: UserService,
        private jhiAlertService: JhiAlertService
    ) {
    }
    ngOnInit() {
    }

    ngOnDestroy() {
    }

    plusCoin(coin: number) {
        this.userService.plusCoin(coin).subscribe(
            (res) => {
                this.jhiAlertService.success('success.buy.success', null)
            },
            (res) => {
                this.jhiAlertService.error('error.internalServerError', null, null);
            }
        )
    }
}
