import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SampleNeo4JNoCacheSharedModule } from 'app/shared/shared.module';

import { DocsComponent } from './docs.component';

import { docsRoute } from './docs.route';

@NgModule({
  imports: [SampleNeo4JNoCacheSharedModule, RouterModule.forChild([docsRoute])],
  declarations: [DocsComponent]
})
export class DocsModule {}
