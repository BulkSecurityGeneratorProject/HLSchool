import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { LessonComponent } from './lesson.component';
import { LessonDetailComponent } from './lesson-detail.component';
import { LessonPopupComponent } from './lesson-dialog.component';
import { LessonDeletePopupComponent } from './lesson-delete-dialog.component';

@Injectable()
export class LessonResolvePagingParams implements Resolve<any> {

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

export const lessonRoute: Routes = [
    {
        path: 'lesson',
        component: LessonComponent,
        resolve: {
            'pagingParams': LessonResolvePagingParams
        },
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.lesson.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'lesson/:id',
        component: LessonDetailComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.lesson.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const lessonPopupRoute: Routes = [
    {
        path: 'lesson-new',
        component: LessonPopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.lesson.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'lesson/:id/edit',
        component: LessonPopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.lesson.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'lesson/:id/delete',
        component: LessonDeletePopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.lesson.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
