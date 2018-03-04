import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { GiftComponent } from './gift.component';
import { GiftDetailComponent } from './gift-detail.component';
import { GiftPopupComponent } from './gift-dialog.component';
import { GiftDeletePopupComponent } from './gift-delete-dialog.component';

@Injectable()
export class GiftResolvePagingParams implements Resolve<any> {

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

export const giftRoute: Routes = [
    {
        path: 'gift',
        component: GiftComponent,
        resolve: {
            'pagingParams': GiftResolvePagingParams
        },
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.gift.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'gift/:id',
        component: GiftDetailComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.gift.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const giftPopupRoute: Routes = [
    {
        path: 'gift-new',
        component: GiftPopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.gift.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'gift/:id/edit',
        component: GiftPopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.gift.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'gift/:id/delete',
        component: GiftDeletePopupComponent,
        data: {
            authorities: ['ROLL_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'hlSchoolApp.gift.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
