import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Lesson } from './lesson.model';
import { Course } from './../course/course.model';
import { LessonService } from './lesson.service';

@Component({
    selector: 'jhi-client-lesson',
    templateUrl: './lesson.component.html',
    styleUrls: [
        'lesson.scss'
    ]
})
export class ClientLessonComponent implements OnInit, OnDestroy {
    course: Course;
    constructor(
        private router: Router,
        private storeService: StoreService,
        private lessonService: LessonService
    ) {
    }
    ngOnInit() {
        this.course = this.storeService.course;
    }

    ngOnDestroy() {
    }

}
