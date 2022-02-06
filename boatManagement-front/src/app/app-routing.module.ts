import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BoatsListComponent } from './components/boats-list/boats-list.component';
import { BoatDetailsComponent } from './components/boat-details/boat-details.component';
import { AddBoatComponent } from './components/add-boat/add-boat.component';
import {LoginComponent} from "./components/login/login.component";


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'boats', component: BoatsListComponent },
  { path: 'boats/:id', component: BoatDetailsComponent },
  { path: 'add', component: AddBoatComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
