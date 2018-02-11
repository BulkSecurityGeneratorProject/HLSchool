import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-client-course',
    templateUrl: './course.component.html',
    styleUrls: [
        'course.scss'
    ]
})
export class ClientCourseComponent implements OnInit, OnDestroy {

    constructor(
        private router: Router
    ) {
    }
    ngOnInit() {
    }

    ngOnDestroy() {
    }

    goToShop() {
    }
}
