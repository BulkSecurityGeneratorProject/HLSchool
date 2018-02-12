import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ClientSubLessonComponent } from './sub-lesson.component';

export const subLessonRoute: Routes = [
    {
        path: 'client/sub-lesson',
        component: ClientSubLessonComponent,
        resolve: {
        },
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'client/sub-lesson/:id',
        component: ClientSubLessonComponent,
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
