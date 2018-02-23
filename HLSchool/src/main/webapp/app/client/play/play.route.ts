import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ClientPlayComponent } from './play.component';

export const playRoute: Routes = [
    {
        path: 'client/play',
        component: ClientPlayComponent,
        resolve: {
        },
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'client/play/:id',
        component: ClientPlayComponent,
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
