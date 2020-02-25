import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SampleNeo4JNoCacheSharedModule } from 'app/shared/shared.module';

import { AuditsComponent } from './audits.component';

import { auditsRoute } from './audits.route';

@NgModule({
  imports: [SampleNeo4JNoCacheSharedModule, RouterModule.forChild([auditsRoute])],
  declarations: [AuditsComponent]
})
export class AuditsModule {}
