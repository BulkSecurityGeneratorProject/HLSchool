import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ClientLessonComponent } from './lesson.component';

export const lessonRoute: Routes = [
    {
        path: 'client/lesson',
        component: ClientLessonComponent,
        resolve: {
        },
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'client/lesson/:id',
        component: ClientLessonComponent,
        data: {
            authorities: [],
            pageTitle: 'hlSchoolApp.config.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
