import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { GiftLogComponent } from './gift-log.component';
import { GiftLogDetailComponent } from './gift-log-detail.component';
import { GiftLogPopupComponent } from './gift-log-dialog.component';
import { GiftLogDeletePopupComponent } from './gift-log-delete-dialog.component';

@Injectable()
export class GiftLogResolvePagingParams implements Resolve<any> {

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

export const giftLogRoute: Routes = [
    {
        path: 'gift-log',
        component: GiftLogComponent,
        resolve: {
            'pagingParams': GiftLogResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.giftLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'gift-log/:id',
        component: GiftLogDetailComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.giftLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const giftLogPopupRoute: Routes = [
    {
        path: 'gift-log-new',
        component: GiftLogPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.giftLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'gift-log/:id/edit',
        component: GiftLogPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.giftLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'gift-log/:id/delete',
        component: GiftLogDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.giftLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
