import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SampleNeo4JNoCacheSharedModule } from 'app/shared/shared.module';

import { ConfigurationComponent } from './configuration.component';

import { configurationRoute } from './configuration.route';

@NgModule({
  imports: [SampleNeo4JNoCacheSharedModule, RouterModule.forChild([configurationRoute])],
  declarations: [ConfigurationComponent]
})
export class ConfigurationModule {}
