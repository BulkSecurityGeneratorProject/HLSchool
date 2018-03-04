import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Lesson } from './lesson.model';
import { Course } from './../course/course.model';
import { LessonService } from './lesson.service';
import { AccountService } from '../../shared/auth/account.service';
import { TranslateService } from '@ngx-translate/core';
import { ConfigService } from './config.service';
import { Config } from './config.model';

@Component({
    selector: 'jhi-client-lesson',
    templateUrl: './lesson.component.html',
    styleUrls: [
        'lesson.scss'
    ]
})
export class ClientLessonComponent implements OnInit, OnDestroy {
    course: Course;
    lessons: Lesson[];
    account: any;
    configs: Config[];
    level = '';
    i = 2;
    constructor(
        private router: Router,
        private storeService: StoreService,
        private lessonService: LessonService,
        private principal: Principal,
        private accountService: AccountService,
        private configService: ConfigService,
        private translateService: TranslateService
    ) {
    }
    ngOnInit() {
        this.course = this.storeService.course;
        this.loadAll();
        this.loadAccount();
    }

    ngOnDestroy() {
    }
    loadAll() {
        this.lessonService.getLessonsByCourseId(this.course.id, {
            page: 0,
            size: 50,
            sort: null}).subscribe(
            (res: ResponseWrapper) => this.onLoadSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadError(res.json)
        );
    }
    loadAccount() {
        this.accountService.get().subscribe(
            (res) => {
                this.account = res;
                this.thenLoadAccount();
            }, null)
    }
    thenLoadAccount() {
        this.configService.query({
            page: 0,
            size: 10,
            sort: null}).subscribe(
                (res) => {
                    this.configs = res.json;
                    console.log(this.configs);
                    this.configs.every((element, index) => {
                        if ( parseInt(element.value, 10) >= this.account.point) {
                            this.level = element.key;
                            return false;
                        }
                        return true;
                    });

                },
                null
            )

    }

    private onLoadSuccess(data, headers) {
        // this.page = pagingParams.page;
        this.lessons = data;
    }
    private onLoadError(error) {
    }
    onClickLesson(lesson) {
        this.storeService.lesson = lesson;
        this.router.navigateByUrl('/client/sub-lesson');
    }
}
