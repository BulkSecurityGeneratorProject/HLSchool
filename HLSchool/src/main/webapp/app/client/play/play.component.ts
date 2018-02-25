import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper, StoreService } from '../../shared';
import { SubLesson } from '../sub-lesson/sub-lesson.model';
import { Question } from './question.model';
import { QuestionFull } from './questionFull.model';
import { QuestionService } from './question.service';
import { Answer } from './answer.model';
import { AnswerService } from './answer.service';
import { AnswerFull } from './answerFull.model';
import { TranslateService } from '@ngx-translate/core';
import { UserLogService } from './user-log.service';
import { UserLog } from './user-log.model';
// import { CourseService } from './course.service';

@Component({
    selector: 'jhi-client-play',
    templateUrl: './play.component.html',
    styleUrls: [
        'play.scss'
    ]
})
export class ClientPlayComponent implements OnInit, OnDestroy {
    subLesson: SubLesson;
    answers: AnswerFull[];
    questionFulls: QuestionFull[];
    result;
    complete = false;
    i = 0;
    trueAnswers = 0;
    constructor(
        private router: Router,
        private storeService: StoreService,
        private questionService: QuestionService,
        private answerService: AnswerService,
        private translateService: TranslateService,
        private userLogService: UserLogService
    ) {
    }
    ngOnInit() {
        this.subLesson = this.storeService.subLesson;
        this.loadAllQuestion();
    }
    thenLoadQuestion() {
        console.log('oki');
        this.loadAllAnwserFullInfoByQuestionId(this.questionFulls[0].id);
    }
    ngOnDestroy() {
    }
    loadAllQuestion() {
        this.questionService.queryFullInfoBySubLesson(this.subLesson.id, {
            page: 0,
            size: 20,
            sort: ''}).subscribe(
            (res: ResponseWrapper) => this.onLoadQuestionSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadQuestionError(res.json)
        );
    }

    loadAllAnwserFullInfoByQuestionId(id) {
        console.log(id);
        this.answerService.queryFullInfoByQuestionId(id, {
            page: 0,
            size: 20,
            sort: ''}).subscribe(
            (res: ResponseWrapper) => this.onLoadAnswerSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadAnswerError(res.json)
        );
    }

    private onLoadQuestionSuccess(data, headers) {
        this.questionFulls = data;
        console.log(data);
        this.thenLoadQuestion();
    }
    private onLoadQuestionError(error) {
        console.log('error', error)
    }

    private onLoadAnswerSuccess(data, headers) {
        this.answers = data;
        console.log(data);
    }
    private onLoadAnswerError(error) {
        console.log('error', error)
    }

    next() {
        if (this.complete) {
            this.router.navigateByUrl('/client/sub-lesson');
        }
        if (this.result === true || this.result === false) {
            if (this.result) {
                this.trueAnswers ++;
            }
            this.i ++;
            if (this.questionFulls.length <= this.i) {
                console.log('complete');
                this.complete = true;
                const userLog: UserLog = new UserLog();
                userLog.point = this.trueAnswers * 100;
                userLog.complete = this.trueAnswers === this.questionFulls.length;
                this.userLogService.create(userLog)
                    .subscribe((res: UserLog) => console.log(res), (res: Response) => console.log(res));
            } else {
                this.loadAllAnwserFullInfoByQuestionId(this.questionFulls[this.i].id);
            }
            this.result = null;
        } else {
            this.result = this.result.result;
        }
    }
}
