import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ngx-webstorage';

import { HlSchoolSharedModule, UserRouteAccessService } from './shared';
import { HlSchoolAppRoutingModule} from './app-routing.module';
import { HlSchoolHomeModule } from './home/home.module';
import { HlSchoolAdminModule } from './admin/admin.module';
import { HlSchoolAccountModule } from './account/account.module';
import { HlSchoolEntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';
import { HlSchoolClientModule } from './client/client.module';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        HlSchoolAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        HlSchoolSharedModule,
        HlSchoolHomeModule,
        HlSchoolAdminModule,
        HlSchoolAccountModule,
        HlSchoolEntityModule,
        HlSchoolClientModule
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class HlSchoolAppModule {}
