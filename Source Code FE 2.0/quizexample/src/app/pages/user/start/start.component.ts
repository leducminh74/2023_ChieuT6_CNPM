import { Component, OnInit } from '@angular/core';
import {LocationStrategy} from "@angular/common";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {QuestionService} from "../../../services/question.service";
import Swal from "sweetalert2";
import {TestService} from "../../../services/test.service";
import {LoginService} from "../../../services/login.service";

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  qid = 0;
  tid = 0;
  questions:any ;
  user:any = null;
  numberOfTest = 0;
  marksGot = 0;
  correctAnswer = 0;
  attempted = 0;
  maxMarks = 0
  totalQuestion = 0;
  test =
    {
      "id": 0,
      "user":{
      },
      "quiz":{
      },
      "numberOfQuestionAttempted":0,
      "numberOfQuestionCorrect":0,
      "marks":0
    }

  isSubmit = false;
  isShowAnswerCorrect = false;
  timer:any

  constructor(private locationSt:LocationStrategy,private route:ActivatedRoute,private questionService:QuestionService,private testService:TestService,private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {
    this.prevenBackButton();
    this.qid = this.route.snapshot.params['qid']
    this.tid = this.route.snapshot.params['tid']
    this.user = this.loginService.getUser()
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
      this.numberOfTest+=1;
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
    //error khi thi lai sau khi het thoi gian
    if(this.numberOfTest > 1){
      this.startTimer()
      return;
    }
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

      this.test =
        {
          "id": this.tid,
          "user":{
          },
          "quiz":{
          },
          "numberOfQuestionAttempted":data.attempted,
          "numberOfQuestionCorrect":data.correctAnswer,
          "marks":data.marksGot
        }

      console.log(this.test)
      this.testService.endTest(this.test).subscribe((data:any) => {
        console.log(data)
      },error => {
        console.log(error)
      })
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

    showAnswerCorrect(){
      this.isShowAnswerCorrect = true;
      console.log(this.questions)
    }

    private startTest(){
      this.test =
        {
          "id": 0,
          "user":{
            "id": this.user.id
          },
          "quiz":{
            "qId": this.qid
          },
          "numberOfQuestionAttempted":0,
          "numberOfQuestionCorrect":0,
          "marks":0
        }
      this.testService.startTest(this.test).subscribe((data:any)=>{
        this.tid = data.id
        this.router.navigate(['/start/' + this.qid + '/'+ data.id])
      },error => {
        console.log(error)
      })

    }

    startAgain(){
      Swal.fire({
        title:'Bạn muốn thi lại?',
        showCancelButton:true,
        confirmButtonText:'Thi lại',
        denyButtonText:'Hủy bỏ',
        icon:'info'
      }).then((result)=>{
        if(result.isConfirmed){
          this.isSubmit = false
          this.isShowAnswerCorrect = false
          this.startTest()
          this.ngOnInit()

        }
      })
    }

    viewResult(){
      this.isShowAnswerCorrect = false;
    }
}
