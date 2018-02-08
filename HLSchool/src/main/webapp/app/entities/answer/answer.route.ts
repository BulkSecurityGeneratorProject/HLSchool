import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { AnswerComponent } from './answer.component';
import { AnswerDetailComponent } from './answer-detail.component';
import { AnswerPopupComponent } from './answer-dialog.component';
import { AnswerDeletePopupComponent } from './answer-delete-dialog.component';

@Injectable()
export class AnswerResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const answerRoute: Routes = [
    {
        path: 'answer',
        component: AnswerComponent,
        resolve: {
            'pagingParams': AnswerResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.answer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'answer/:id',
        component: AnswerDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.answer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const answerPopupRoute: Routes = [
    {
        path: 'answer-new',
        component: AnswerPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.answer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'answer/:id/edit',
        component: AnswerPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.answer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'answer/:id/delete',
        component: AnswerDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.answer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
