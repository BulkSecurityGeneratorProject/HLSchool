import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { Lesson } from './../lesson/lesson.model';
import { SubLesson } from './sub-lesson.model';
import { SubLessonService } from './sub-lesson.service';
import { AccountService } from '../../shared/auth/account.service';
import { ConfigService } from './config.service';
import { Config } from './config.model';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'jhi-client-sub-lesson',
    templateUrl: './sub-lesson.component.html',
    styleUrls: [
        'sub-lesson.scss'
    ]
})
export class ClientSubLessonComponent implements OnInit, OnDestroy {
    lesson: Lesson;
    subLessons: SubLesson[];
    account: any;
    level = '';
    configs: Config[];
    i = 2;
    constructor(
        private router: Router,
        private storeService: StoreService,
        private subLessonService: SubLessonService,
        private accountService: AccountService,
        private configService: ConfigService,
        private translateService: TranslateService
    ) {
    }
    ngOnInit() {
        this.lesson = this.storeService.lesson;
        this.loadAll();
        this.loadAccount();
    }
    ngOnDestroy() {
    }
    loadAll() {
        console.log(this.lesson.id);
        this.subLessonService.getSubLessonsByLessonId(this.lesson.id, {
            page: 0,
            size: 50,
            sort: null}).subscribe(
            (res: ResponseWrapper) => this.onLoadSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadError(res.json)
        );
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
    loadAccount() {
        this.accountService.get().subscribe(
            (res) => {
                this.account = res;
                this.thenLoadAccount();
            }, null)
    }
    private onLoadSuccess(data, headers) {
        // this.page = pagingParams.page;
        this.subLessons = data;
    }
    private onLoadError(error) {
    }
    private onClickSubLesson(subLesson) {
        this.storeService.subLesson = subLesson;
        this.router.navigateByUrl('/client/play');
    }
}
