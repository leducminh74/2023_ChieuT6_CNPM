import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../../../services/category.service";
import Swal from "sweetalert2";
import {MatSnackBar} from "@angular/material/snack-bar";
import {QuizService} from "../../../services/quiz.service";

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {

  categories:any = []

  quizData = {
    title:'',
    description:'',
    maxMarks:'',
    numberOfQuestions:'',
    active:true,
    category:{
      id:''
    }
  }
  constructor(private categoryService:CategoryService,private snack:MatSnackBar,private quizService:QuizService) { }

  ngOnInit(): void {
    this.categoryService.categories().subscribe((data:any) =>{
      this.categories = data
      console.log(this.categories)
    },error => {
      console.log(error)
      Swal.fire('Error','Xảy ra lỗi khi load dữ liệu!!','error')
    })
  }

  addQuiz(){
    if(this.quizData.title.trim() == '' || this.quizData.title == null){
      this.snack.open('Vui lòng nhập tên đề thi!!','ok',{
        duration:3000
      })
      return
    }else if(this.quizData.description.trim() == '' || this.quizData.description == null){
      this.snack.open('Vui lòng nhập mô tả!!','ok',{
        duration:3000
      })
      return
    }else if(this.quizData.maxMarks.trim() == '' || this.quizData.maxMarks == null){
      this.snack.open('Vui lòng nhập điểm tối đa!!','ok',{
        duration:3000
      })
      return
    }else if(this.quizData.numberOfQuestions.trim() == '' || this.quizData.numberOfQuestions == null){
      this.snack.open('Vui lòng nhập số lượng câu hỏi!!','ok',{
        duration:3000
      })
      return
    }else if(this.quizData.category.id == ''|| this.quizData.description == null){
      this.snack.open('Vui lòng chọn Môn!!','ok',{
        duration:3000
      })
      return
    }

    this.quizService.addQuiz(this.quizData).subscribe((data) =>{
      Swal.fire('Success','Thêm đề thi thành công','success')
      this.quizData = {
        title:'',
        description:'',
        maxMarks:'',
        numberOfQuestions:'',
        active:true,
        category:{
          id:''
        }
      }
    },error => {
      console.log(error)
      Swal.fire('Error!!','Xảy ra lỗi khi thêm đè thi','error')
    })

  }

}
