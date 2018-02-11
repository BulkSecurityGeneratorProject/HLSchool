import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ClientCourseComponent } from './course.component';

export const courseRoute: Routes = [
    {
        path: 'client/course',
        component: ClientCourseComponent,
        resolve: {
        },
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'client/course/:id',
        component: ClientCourseComponent,
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
