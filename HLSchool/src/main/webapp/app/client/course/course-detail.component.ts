import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Course } from './course.model';
import { CourseService } from './course.service';
import { TranslateService } from '@ngx-translate/core';
import { CourseLog } from './course-log.model';
import { CourseLogService } from './course-log.service';

@Component({
    selector: 'jhi-client-course-detail',
    templateUrl: './course-detail.component.html',
    styleUrls: [
        'course.scss'
    ]
})
export class ClientCourseDetailComponent implements OnInit, OnDestroy {
    course: Course;
    constructor(
        private router: Router,
        private courseService: CourseService,
        private jhiAlertService: JhiAlertService,
        private storeService: StoreService,
        private route: ActivatedRoute,
        private translateService: TranslateService,
        private courseLogService: CourseLogService
    ) {
    }
    ngOnInit() {
        this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
    }

    ngOnDestroy() {

    }
    load(id) {
        this.courseService.find(id).subscribe((course) => {
            this.course = course;
        });
    }
    buy() {
        console.log('Bạn cần trả ' + this.course.coin + ' coin');
        const courseLog = new CourseLog;
        courseLog.complete = false;
        courseLog.courseId = this.course.id;
        this.courseLogService.create(courseLog).subscribe((res: CourseLog) => this.onSaveCourseLogSuccess(res), (res: Response) => this.onSaveCourseLogError(res));
    }
    private onSaveCourseLogSuccess(result: CourseLog) {
        this.router.navigateByUrl('client/course');
    }

    private onSaveCourseLogError(error: any) {
        this.jhiAlertService.error('Bạn không đủ coin', null, null);
    }
}
