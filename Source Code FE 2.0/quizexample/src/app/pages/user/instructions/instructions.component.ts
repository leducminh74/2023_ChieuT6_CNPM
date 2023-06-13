import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {QuizService} from "../../../services/quiz.service";
import Swal from "sweetalert2";
import {TestService} from "../../../services/test.service";
import {UserService} from "../../../services/user.service";
import {LoginService} from "../../../services/login.service";

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {

  qid:any;
  quiz:any;
  user:any = null;

  test =
    {
      "user" : {
      },
      "quiz" : {

      }
    }




  constructor(private route:ActivatedRoute,private quizService:QuizService,private router:Router,private testService:TestService,private loginService:LoginService) { }

  ngOnInit(): void {
    this.qid = this.route.snapshot.params['qid']
    this.user = this.loginService.getUser();

    this.quizService.getQuiz(this.qid).subscribe((data) =>{
      this.quiz = data
      this.test =
        {
          "user" : {
            "id":this.user.id
          },
          "quiz": {
            "qId":this.qid
          }
        }


      console.log(this.quiz)
    },error => {
      console.log(error)
      Swal.fire('Error','Xảy ra lỗi khi load dữ liệu từ máy chủ!!','error')
    })
  }

  startQuiz(){
    console.log(this.test)
    Swal.fire({
      title:'Bạn có muốn bắt đầu bài thi?',
      showCancelButton:true,
      confirmButtonText:'Bắt đầu ngay',
      denyButtonText:'Hủy bỏ',
      icon:'info'
    }).then((result)=>{
      if(result.isConfirmed){
        this.testService.startTest(this.test).subscribe((data:any)=>{
          console.log(data)
          this.router.navigate(['/start/' + this.qid + '/'+ data.id])
        },error => {
          console.log(error)
        })
      }else if(result.isDenied){
        return;
      }
    })
  }

}
