import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SampleNeo4JNoCacheSharedModule } from 'app/shared/shared.module';

import { MetricsComponent } from './metrics.component';

import { metricsRoute } from './metrics.route';

@NgModule({
  imports: [SampleNeo4JNoCacheSharedModule, RouterModule.forChild([metricsRoute])],
  declarations: [MetricsComponent]
})
export class MetricsModule {}
