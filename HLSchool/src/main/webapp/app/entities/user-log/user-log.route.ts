import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { UserLogComponent } from './user-log.component';
import { UserLogDetailComponent } from './user-log-detail.component';
import { UserLogPopupComponent } from './user-log-dialog.component';
import { UserLogDeletePopupComponent } from './user-log-delete-dialog.component';

@Injectable()
export class UserLogResolvePagingParams implements Resolve<any> {

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

export const userLogRoute: Routes = [
    {
        path: 'user-log',
        component: UserLogComponent,
        resolve: {
            'pagingParams': UserLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.userLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-log/:id',
        component: UserLogDetailComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.userLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userLogPopupRoute: Routes = [
    {
        path: 'user-log-new',
        component: UserLogPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.userLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-log/:id/edit',
        component: UserLogPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.userLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-log/:id/delete',
        component: UserLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.userLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
