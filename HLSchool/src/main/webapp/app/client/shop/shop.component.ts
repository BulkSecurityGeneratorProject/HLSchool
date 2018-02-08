import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-client-shop',
    templateUrl: './shop.component.html',
    styleUrls: [
        'shop.scss'
    ]
})
export class ClientShopComponent implements OnInit, OnDestroy {

    constructor(
        private router: Router
    ) {
    }
    ngOnInit() {
    }

    ngOnDestroy() {
    }
}
