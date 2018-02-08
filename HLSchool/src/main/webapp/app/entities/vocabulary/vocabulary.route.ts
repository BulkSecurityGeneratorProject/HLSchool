import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { VocabularyComponent } from './vocabulary.component';
import { VocabularyDetailComponent } from './vocabulary-detail.component';
import { VocabularyPopupComponent } from './vocabulary-dialog.component';
import { VocabularyDeletePopupComponent } from './vocabulary-delete-dialog.component';

@Injectable()
export class VocabularyResolvePagingParams implements Resolve<any> {

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

export const vocabularyRoute: Routes = [
    {
        path: 'vocabulary',
        component: VocabularyComponent,
        resolve: {
            'pagingParams': VocabularyResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.vocabulary.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'vocabulary/:id',
        component: VocabularyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.vocabulary.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vocabularyPopupRoute: Routes = [
    {
        path: 'vocabulary-new',
        component: VocabularyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.vocabulary.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'vocabulary/:id/edit',
        component: VocabularyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.vocabulary.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'vocabulary/:id/delete',
        component: VocabularyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hlSchoolApp.vocabulary.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
