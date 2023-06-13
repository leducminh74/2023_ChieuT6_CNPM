import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http:HttpClient) { }

  public getListTestByUser(userId:number){
    return this.http.get(`${baseUrl}/test/?userId=${userId}`)
  }

  public startTest(test:any){
    return this.http.post(`${baseUrl}/test/`,test)
  }

  public endTest(test:any){
    return this.http.put(`${baseUrl}/test/`,test)
  }

  public getTestByQuizOrderByMarks(qid:any){
    return this.http.get(`${baseUrl}/test/${qid}`)
  }

}
