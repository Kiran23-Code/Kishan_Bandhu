import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  navigationId: number = 1;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  navigateToMarketView() {
    this.navigationId = 3;
    // this.router.navigate(['/market-view']);
  }
  navigateTohelppage(){
    // this.router.navigate(['/help']);
  }
  navigateToAboutpage(){
    this.navigationId = 2;
    // this.router.navigate(['/about'])
  }
  navigateToGrade(){
    this.navigationId = 4;
    // this.router.navigate(['/grade'])
  }
  navigateToHomepage(){
    this.navigationId = 1;
    // this.router.navigate(['/home'])
  }
  navigateToLogout(){
    this.navigationId = 5;
  }

  navigateToHome() {
    this.router.navigate([''])
  }
 }
