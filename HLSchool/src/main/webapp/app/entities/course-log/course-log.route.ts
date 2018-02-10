import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { CourseLogComponent } from './course-log.component';
import { CourseLogDetailComponent } from './course-log-detail.component';
import { CourseLogPopupComponent } from './course-log-dialog.component';
import { CourseLogDeletePopupComponent } from './course-log-delete-dialog.component';

@Injectable()
export class CourseLogResolvePagingParams implements Resolve<any> {

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

export const courseLogRoute: Routes = [
    {
        path: 'course-log',
        component: CourseLogComponent,
        resolve: {
            'pagingParams': CourseLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.courseLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'course-log/:id',
        component: CourseLogDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.courseLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const courseLogPopupRoute: Routes = [
    {
        path: 'course-log-new',
        component: CourseLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.courseLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'course-log/:id/edit',
        component: CourseLogPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.courseLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'course-log/:id/delete',
        component: CourseLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.courseLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
