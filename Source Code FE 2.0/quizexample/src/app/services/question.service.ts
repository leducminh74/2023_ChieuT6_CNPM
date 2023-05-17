import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import baseUrl from "./helper";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http:HttpClient) { }

  public getQuestionOfQuiz(qid:any){
    return this.http.get(`${baseUrl}/question/quiz/all/${qid}`)
  }

  public getQuestionOfQuizForTest(qid:any){
    return this.http.get(`${baseUrl}/question/quiz/${qid}`)
  }

  public addQuestion(question:any){
    return this.http.post(`${baseUrl}/question/`,question)
  }

  public deleteQuestion(questionId:any){
    return this.http.delete(`${baseUrl}/question/${questionId}`)
  }

  public getQuestion(questionId:any){
    return this.http.get(`${baseUrl}/question/${questionId}`)
  }

  public updateQuestion(question:any){
    return this.http.put(`${baseUrl}/question/`,question)
  }

  public evalQuiz(question:any){
    return this.http.post(`${baseUrl}/question/eval-quiz`,question);
  }

}
