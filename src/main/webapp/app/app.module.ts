import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MyskoolinSharedModule } from 'app/shared/shared.module';
import { MyskoolinCoreModule } from 'app/core/core.module';
import { MyskoolinAppRoutingModule } from './app-routing.module';
import { MyskoolinHomeModule } from './home/home.module';
import { MyskoolinEntityModule } from './entities/entity.module';
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
    MyskoolinSharedModule,
    MyskoolinCoreModule,
    MyskoolinHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MyskoolinEntityModule,
    MyskoolinAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class MyskoolinAppModule {}
