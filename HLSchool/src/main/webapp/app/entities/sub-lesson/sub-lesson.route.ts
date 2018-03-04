import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SubLessonComponent } from './sub-lesson.component';
import { SubLessonDetailComponent } from './sub-lesson-detail.component';
import { SubLessonPopupComponent } from './sub-lesson-dialog.component';
import { SubLessonDeletePopupComponent } from './sub-lesson-delete-dialog.component';

@Injectable()
export class SubLessonResolvePagingParams implements Resolve<any> {

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

export const subLessonRoute: Routes = [
    {
        path: 'sub-lesson',
        component: SubLessonComponent,
        resolve: {
            'pagingParams': SubLessonResolvePagingParams
        },
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLesson.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sub-lesson/:id',
        component: SubLessonDetailComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLesson.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const subLessonPopupRoute: Routes = [
    {
        path: 'sub-lesson-new',
        component: SubLessonPopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLesson.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sub-lesson/:id/edit',
        component: SubLessonPopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLesson.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sub-lesson/:id/delete',
        component: SubLessonDeletePopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLesson.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
