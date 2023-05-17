import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuizService} from "../../../services/quiz.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {

  catId:any;
  quizzes:any;

  constructor(private route:ActivatedRoute,private quizService:QuizService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params:any)=>{
      this.catId = params.catId;
      if(this.catId == 0){
        this.quizService.getActiveQuizzes().subscribe((data:any) =>{
          this.quizzes = data;
          console.log(this.quizzes)
        },error => {
          console.log(error)
          Swal.fire('Error','Xảy ra lỗi khi tải tất cả đề thi!!','error')
        })
      } else {
        this.quizService.getActiveQuizzesOfCategory(this.catId).subscribe((data:any)=>{
          this.quizzes = data;
        },error => {
          console.log(error)
          Swal.fire('Error','Xảy ra lỗi khi tải đề thi!!','error')
        })
      }
    })



  }

}
