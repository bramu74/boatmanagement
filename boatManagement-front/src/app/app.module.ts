import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddBoatComponent } from './components/add-boat/add-boat.component';
import { BoatDetailsComponent } from './components/boat-details/boat-details.component';
import { BoatsListComponent } from './components/boats-list/boats-list.component';
import { LoginComponent } from './components/login/login.component';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {HttpInterceptorService} from "./services/http-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    AddBoatComponent,
    BoatDetailsComponent,
    BoatsListComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
