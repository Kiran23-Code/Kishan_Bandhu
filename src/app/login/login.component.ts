import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginResponse, SignUpModel, signUpResponse } from '../model/Login';
import { RestService } from '../service/rest.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string = "";
  password: string = "";
  sliderId: number = 1;

  email: string = "";
  first_name: string = "";
  last_name: string = "";
  phone_number: string = "";
  password_signup: string = "";
  confirm_password: string = "";

  constructor(private router: Router,
    private service: RestService,
    private snackbar: MatSnackBar,
    private datePipe: DatePipe) { }

  ngOnInit(): void {
  }

  slideClicked(id: number){
    this.sliderId = id;
  }

  login() {
    console.log('username: '+this.username);
    console.log('password: '+this.password);
    this.service.login(this.username, this.password).subscribe((response:LoginResponse) => {
      if(response && response.code === 200){
        this.router.navigate(['/home'], { replaceUrl: true });
      } else {
        this.snackbar.open(response.message, 'close', {duration:5000});

      }
    });
    
  }

  onForgetPwd(){
    this.router.navigate(['/Forget'], { replaceUrl: true });

  }

  signup() {
    if(this.email && this.first_name && this.phone_number && this.password_signup && this.confirm_password) {
      if(this.password_signup === this.confirm_password) {
        var date = new Date();
        var dateStr = this.datePipe.transform(date,"yyyy-MM-dd")?.toString();
        var finalDate: string = dateStr ? dateStr : 'yyyy-mm-dd';
        let signup: SignUpModel = {
          email: this.email,
          first_name: this.first_name,
          last_name: this.last_name,
          password: this.password_signup,
          phone_number: this.phone_number,
          enrolled_date: finalDate
        }
        this.service.signup(signup).subscribe((response: signUpResponse) => {
          if(response && response.code == 200) {
            this.snackbar.open('Signup successfull. Please login', 'close', {duration: 5000});
            this.clearFields();
          } else {
            this.snackbar.open('Something went wrong!! Please try again later', 'close', {duration: 5000});
          }
        });
      } else {
        this.snackbar.open('Passwords do not match', 'close', {duration: 5000});
      }
      
    } else {
      this.snackbar.open('Please enter all values', 'close', {duration: 5000});
    }
  }

  clearFields() {
    this.email = '';
    this.first_name = '';
    this.last_name = '';
    this.password_signup = '';
    this.confirm_password = '';
    this.phone_number = '';
  }
}
