import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { GradeResponse } from '../model/GradeResponse';
import { LoginResponse, SignUpModel, signUpResponse } from '../model/Login';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  baseUrl: string  = "";
  constructor(private http: HttpClient) { 
    this.baseUrl = environment.baseUrl;
  }

  login(email: string, password: string) {
    const headers = new HttpHeaders()
    .set("email", email)
    .set("password",password)

    return this.http.get<LoginResponse>(this.baseUrl+"authentication/login", {headers: headers});
  }
  
  findGrade(base64SImage:string){
    const headers =  new HttpHeaders();
    let body = base64SImage;
    return this.http.post<GradeResponse>(this.baseUrl+"grade/process", body, {headers: headers});
  }

  signup(signup: SignUpModel) {
    const headers =  new HttpHeaders();
    let body = signup;
    return this.http.post<signUpResponse>(this.baseUrl+"authentication/signup", body, {headers: headers} );
  }
}
