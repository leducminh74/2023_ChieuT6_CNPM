import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {QuizService} from "../../../services/quiz.service";
import {CategoryService} from "../../../services/category.service";
import Swal from "sweetalert2";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {
  qId:any = 0;
  quiz:any;
  categories:any
  constructor(private categoryService:CategoryService,private route:ActivatedRoute,private quizService:QuizService,private router:Router,private snack:MatSnackBar) { }

  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid'];
    this.quizService.getQuiz(this.qId).subscribe((data:any) =>{
      this.quiz = data;
      console.log(this.quiz)
    },error => {
      console.log(error)
    });
    this.categoryService.categories().subscribe((data:any) =>{
      this.categories = data
      console.log(this.categories)
    },error => {
      console.log(error)
      Swal.fire('Error','Xảy ra lỗi khi load dữ liệu!!','error')
    })
  }

  public updateData(){

    if(this.quiz.title.trim() == '' || this.quiz.title == null){
      this.snack.open('Vui lòng nhập tên đề thi!!','ok',{
        duration:3000
      })
      return
    }else if(this.quiz.description.trim() == '' || this.quiz.description == null){
      this.snack.open('Vui lòng nhập mô tả!!','ok',{
        duration:3000
      })
      return
    }else if(this.quiz.maxMarks.trim() == '' || this.quiz.maxMarks == null){
      this.snack.open('Vui lòng nhập điểm tối đa!!','ok',{
        duration:3000
      })
      return
    }else if(this.quiz.numberOfQuestions.trim() == '' || this.quiz.numberOfQuestions == null){
      this.snack.open('Vui lòng nhập số lượng câu hỏi!!','ok',{
        duration:3000
      })
      return
    }else if(this.quiz.category.id == ''|| this.quiz.description == null){
      this.snack.open('Vui lòng chọn Môn!!','ok',{
        duration:3000
      })
      return
    }

    this.quizService.updateQuiz(this.quiz).subscribe((data) =>{
      Swal.fire('Success','Cập nhật thành công','success').then(result =>{
        this.router.navigate(['/admin/quizzes'])
      })

    },error => {
      Swal.fire('Error','Xảy ra lỗi trong quá trình cập nhật!!','error')
      console.log(error)

    })

  }

}
