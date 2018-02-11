import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HlSchoolSharedModule } from '../shared';

import { HOME_ROUTE, HomeComponent } from './';
import { Post } from './post.model';
import { PostService } from './post.service';

@NgModule({
    imports: [
        HlSchoolSharedModule,
        RouterModule.forChild([ HOME_ROUTE ])
    ],
    declarations: [
        HomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
        PostService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HlSchoolHomeModule {}
