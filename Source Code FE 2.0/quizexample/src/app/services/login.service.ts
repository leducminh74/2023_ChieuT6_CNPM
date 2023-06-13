import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  public generateToken(loginData:any){
    return this.http.post(`${baseUrl}/generate-token`,loginData)
  }

  public getCurrentUser(){
    return this.http.get(`${baseUrl}/current-user`)
  }

  public loginUser(token:any){
    localStorage.setItem('token',token)
    return true;
  }

  public isLoggedIn(){
    let token = localStorage.getItem('token');
    if(token == undefined || token == '' || token == null){
      return false;
    }
    return true;
  }

  public logout(){
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    return true;
  }

  public getToken(){
    return localStorage.getItem('token');
  }

  public setUser(user:any){
    localStorage.setItem('user',JSON.stringify(user));
  }

  public getUser(){
    let userStr = localStorage.getItem('user');
    if(userStr != null){
      return JSON.parse(userStr);
    }else{
      this.logout()
      return null;
    }
  }

  public getUserRole(){
    let user = this.getUser();
    return user.authorities[0].authority;
  }

  public getAllRole(){
    let result = ''
    let user = this.getUser();
    if(this.isLoggedIn()){
      for (let role of user.authorities) {
        result += role.authority + ','
      }
    }
    return result.substring(0,result.length - 1)
  }

}
