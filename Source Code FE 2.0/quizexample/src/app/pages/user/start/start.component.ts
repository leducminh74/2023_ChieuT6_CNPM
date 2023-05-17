import { Component, OnInit } from '@angular/core';
import {LocationStrategy} from "@angular/common";
import {ActivatedRoute, Route} from "@angular/router";
import {QuestionService} from "../../../services/question.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  qid:any;
  questions:any ;

  marksGot = 0;
  correctAnswer = 0;
  attempted = 0;
  maxMarks = 0
  totalQuestion = 0;

  isSubmit = false;

  timer:any

  constructor(private locationSt:LocationStrategy,private route:ActivatedRoute,private questionService:QuestionService) { }

  ngOnInit(): void {
    this.prevenBackButton();
    this.qid = this.route.snapshot.params['qid']
    this.loadQuestion();
  }

  prevenBackButton(){
    history.pushState(null,'',location.href);
    this.locationSt.onPopState(()=>{
      history.pushState(null,'',location.href)
    })
  }

  loadQuestion() {
    this.questionService.getQuestionOfQuizForTest(this.qid).subscribe((data:any) =>{
      this.questions = data;
      this.totalQuestion = this.questions.length
      this.timer = this.questions.length * 2 * 60

      console.log(this.questions)
      this.startTimer();
    },error => {
      console.log(error)
      Swal.fire('Error','Xảy ra lỗi khi tải câu hỏi!!','error')
    })
  }

  submitQuiz(){
    Swal.fire({
      title:'Bạn muốn nộp bài thi?',
      showCancelButton:true,
      confirmButtonText:'Nộp',
      denyButtonText:'Hủy bỏ',
      icon:'info'
    }).then((result)=>{
      if(result.isConfirmed){
        this.evalQuiz()
      }
    })

  }

  startTimer(){
    let t = window.setInterval(()=>{
      if(this.timer <= 0){
        this.evalQuiz()
        clearInterval(t);
      }else{
        this.timer--;
      }
    },1000)
  }

  getFormattedTimer(){
    let mm = Math.floor(this.timer/60)
    let ss = this.timer - mm*60
    return`${mm} phút : ${ss} giây`;
  }

  private evalQuiz() {

    this.questionService.evalQuiz(this.questions).subscribe((data:any) =>{
      this.marksGot = data.marksGot
      this.attempted = data.attempted
      this.correctAnswer = data.correctAnswer
      this.maxMarks = data.maxMarks
      this.totalQuestion = data.totalQuestion
      this.isSubmit = true;
      console.log(data)
    },error => {
      console.log(error)
    })


      // this.isSubmit = true;
      // this.questions.forEach((q:any)=>{
      //   if(q.givenAnswer == q.answer){
      //     this.correctAnswer++;
      //     let markSingle = this.questions[0].quiz.maxMarks/this.questions.length;
      //     this.marksGot += markSingle;
      //   }
      //   if(q.givenAnswer.trim() != ''){
      //     this.attempted++;
      //   }
      // })
      //
      // console.log(this.marksGot)
      // console.log(this.attempted)
      // console.log(this.correctAnswer)
      // this.router.navigate(['/start/' + this.qid])
    }


    printPage(){
      window.print()
    }
}
