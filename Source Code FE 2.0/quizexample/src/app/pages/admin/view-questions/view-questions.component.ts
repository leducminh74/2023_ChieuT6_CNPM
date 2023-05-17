import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Route} from "@angular/router";
import {QuestionService} from "../../../services/question.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-view-questions',
  templateUrl: './view-questions.component.html',
  styleUrls: ['./view-questions.component.css']
})
export class ViewQuestionsComponent implements OnInit {

  qId:any;
  qTitle:any;
  questions:any;

  constructor(private route:ActivatedRoute,private questionService:QuestionService) { }

  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid']
    this.qTitle = this.route.snapshot.params['title']
    this.questionService.getQuestionOfQuiz(this.qId).subscribe((data:any) =>{
      console.log(data)
      this.questions = data
    },error => {
      console.log(error)
    })
  }

  deleteQuestion(qid:any){
    Swal.fire({
      icon:'info',
      showCancelButton:true,
      cancelButtonText:'Khoan đã',
      confirmButtonText:'Chắc chắn',
      title:'bạn có chắc chắn muốn xóa câu hỏi này?',
    }).then((result)=>{
      if(result.isConfirmed){
        this.questionService.deleteQuestion(qid).subscribe(res=>{
          Swal.fire('Success','Xóa câu hỏi thành công','success')
          this.questions = this.questions.filter((q:any) => q.quesId != qid)
        },error => {
          Swal.fire('Error','Xảy ra lỗi trong quá trình xóa câu hỏi','error')
          console.log(error)

        })
      }
    })
  }

}
