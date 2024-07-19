import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MarketViewComponent } from './market-view/market-view.component';
import { NewsComponent } from './news/news.component';
import { HelpComponent } from './help/help.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { SignupComponent } from './signup/signup.component';
import { ForgetComponent } from './forget/forget.component';
import { GradeComponent } from './grade/grade.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
  {path:'', component:LoginComponent, pathMatch:'full'},
  { path: 'home', component: HomeComponent},
  {path:'market-view', component:MarketViewComponent},
  { path:'news', component:NewsComponent},
  {path:'help', component:HelpComponent},
  {path:'about', component: AboutComponent},
  {path:'Signup',component: SignupComponent},
  {path:'Forget',component: ForgetComponent},
  {path:'grade',component: GradeComponent},
  {path:'logout',component: LogoutComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
