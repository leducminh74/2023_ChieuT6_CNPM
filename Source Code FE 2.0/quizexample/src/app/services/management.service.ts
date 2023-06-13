import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class ManagementService {

  constructor(private http:HttpClient) { }

  numberOfTests(year:number,qid:number){
    return this.http.get(`${baseUrl}/management/numberOfTests?year=${year}&qid=${qid}`)
  }

  accountStatistics(){
    return this.http.get(`${baseUrl}/management/user-statistics`)
  }

  totalNumberOfTest(year:number,qid:number){
    return this.http.get(`${baseUrl}/management/total-numberOfTest-ofYear?year=${year}&qid=${qid}`)
  }

  countTotalUser(){
    return this.http.get(`${baseUrl}/management/countTotalUser`)
  }
}
