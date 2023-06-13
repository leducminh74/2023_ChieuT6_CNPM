import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http:HttpClient
  ) { }

//  getAllUser
  public getAllUser(){
    return this.http.get(`${baseUrl}/user/getAllUser`);
  }

//  add user
  public addUser(user:any){
    return this.http.post(`${baseUrl}/user/`,user);
  }

//  update user
  public updateUser(id:number, user:any){
    return this.http.put(`${baseUrl}/user/${id}`,user);
  }

// disable user
  public disableUser(id:number){
    return this.http.get(`${baseUrl}/user/disable/${id}`);
  }

//  enable user
  public enableUser(id:number){
    return this.http.get(`${baseUrl}/user/enable/${id}`);
  }

//  add role admin for user
  public addAdminRole(id:number){
    return this.http.get(`${baseUrl}/user/addAdmin/${id}`);
  }




}
