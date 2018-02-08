import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ClientShopComponent } from './shop.component';

export const shopRoute: Routes = [
    {
        path: 'client/shop',
        component: ClientShopComponent,
        resolve: {
        },
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'client/shop/:id',
        component: ClientShopComponent,
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
