import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SampleNeo4JNoCacheSharedModule } from 'app/shared/shared.module';
import { LabelComponent } from './label.component';
import { LabelDetailComponent } from './label-detail.component';
import { labelRoute } from './label.route';

@NgModule({
  imports: [SampleNeo4JNoCacheSharedModule, RouterModule.forChild(labelRoute)],
  declarations: [LabelComponent, LabelDetailComponent]
})
export class SampleNeo4JNoCacheLabelModule {}
