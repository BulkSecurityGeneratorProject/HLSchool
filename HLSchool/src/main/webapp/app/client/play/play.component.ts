import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
// import { Course } from './course.model';
// import { CourseService } from './course.service';

@Component({
    selector: 'jhi-client-play',
    templateUrl: './play.component.html',
    styleUrls: [
        'play.scss'
    ]
})
export class ClientPlayComponent implements OnInit, OnDestroy {
    constructor(
    ) {
    }
    ngOnInit() {
    }

    ngOnDestroy() {
    }
}
