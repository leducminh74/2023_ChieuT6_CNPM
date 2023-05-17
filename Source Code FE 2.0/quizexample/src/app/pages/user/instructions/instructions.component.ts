import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {QuizService} from "../../../services/quiz.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {

  qid:any;
  quiz:any;


  constructor(private route:ActivatedRoute,private quizService:QuizService,private router:Router) { }

  ngOnInit(): void {

    this.qid = this.route.snapshot.params['qid']

    this.quizService.getQuiz(this.qid).subscribe((data) =>{
      this.quiz = data
      console.log(this.quiz)
    },error => {
      console.log(error)
      Swal.fire('Error','Xảy ra lỗi khi load dữ liệu từ máy chủ!!','error')
    })
  }

  startQuiz(){
    Swal.fire({
      title:'Bạn có muốn bắt đầu bài thi?',
      showCancelButton:true,
      confirmButtonText:'Bắt đầu ngay',
      denyButtonText:'Hủy bỏ',
      icon:'info'
    }).then((result)=>{
      if(result.isConfirmed){
        this.router.navigate(['/start/' + this.qid])
      }else if(result.isDenied){
        return;
      }
    })
  }

}
