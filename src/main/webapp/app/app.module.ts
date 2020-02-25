import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { SampleNeo4JNoCacheSharedModule } from 'app/shared/shared.module';
import { SampleNeo4JNoCacheCoreModule } from 'app/core/core.module';
import { SampleNeo4JNoCacheAppRoutingModule } from './app-routing.module';
import { SampleNeo4JNoCacheHomeModule } from './home/home.module';
import { SampleNeo4JNoCacheEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    SampleNeo4JNoCacheSharedModule,
    SampleNeo4JNoCacheCoreModule,
    SampleNeo4JNoCacheHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SampleNeo4JNoCacheEntityModule,
    SampleNeo4JNoCacheAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class SampleNeo4JNoCacheAppModule {}
