import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SampleNeo4JNoCacheSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [SampleNeo4JNoCacheSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class SampleNeo4JNoCacheHomeModule {}
