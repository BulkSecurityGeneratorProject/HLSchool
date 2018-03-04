import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SubLessonLogComponent } from './sub-lesson-log.component';
import { SubLessonLogDetailComponent } from './sub-lesson-log-detail.component';
import { SubLessonLogPopupComponent } from './sub-lesson-log-dialog.component';
import { SubLessonLogDeletePopupComponent } from './sub-lesson-log-delete-dialog.component';

@Injectable()
export class SubLessonLogResolvePagingParams implements Resolve<any> {

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

export const subLessonLogRoute: Routes = [
    {
        path: 'sub-lesson-log',
        component: SubLessonLogComponent,
        resolve: {
            'pagingParams': SubLessonLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLessonLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sub-lesson-log/:id',
        component: SubLessonLogDetailComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLessonLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const subLessonLogPopupRoute: Routes = [
    {
        path: 'sub-lesson-log-new',
        component: SubLessonLogPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLessonLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sub-lesson-log/:id/edit',
        component: SubLessonLogPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLessonLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sub-lesson-log/:id/delete',
        component: SubLessonLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.subLessonLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
