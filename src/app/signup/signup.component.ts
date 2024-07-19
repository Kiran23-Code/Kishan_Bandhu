import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SignUpModel, signUpResponse } from '../model/Login';
import { RestService } from '../service/rest.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  email: string = "";
  first_name: string = "";
  last_name: string = "";
  phone_number: string = "";
  password: string = "";
  confirm_password: string = "";


  constructor(private service: RestService,
    private snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }

  signup() {
    if(this.email && this.first_name && this.phone_number && this.password && this.confirm_password) {
      this.snackbar.open('Please enter all values', 'close', {duration: 5000});
    } else {
      if(this.password === this.confirm_password) {
        let signup: SignUpModel = {
          email: this.email,
          first_name: this.first_name,
          last_name: this.last_name,
          password: this.password,
          phone_number: this.phone_number,
          enrolled_date: ""
        }
        this.service.signup(signup).subscribe((response: signUpResponse) => {
          if(response && response.code == 200) {
            this.snackbar.open('Signup successfull. Please login', 'close', {duration: 5000});
          } else {
            this.snackbar.open('Something went wrong!! Please try again later', 'close', {duration: 5000});
          }
        });
      } else {
        this.snackbar.open('Passwords do not match', 'close', {duration: 5000});
      }
    }
  }

 
}
