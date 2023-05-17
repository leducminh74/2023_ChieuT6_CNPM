import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../../services/quiz.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes:any = []

  constructor(private quizService:QuizService) { }

  ngOnInit(): void {

    this.quizService.quizzes().subscribe((data:any) =>{
      this.quizzes = data
      console.log(this.quizzes)
    },error => {
      console.log(error)
      Swal.fire('Error!','Có lỗi xảy ra trong quá trình load dữ liệu!!','error')
    })
  }

  deleteQuiz(qId:any){
    Swal.fire({
      icon:'info',
      title:"Bạn có chắc chắn muốn xóa đề thi này?",
      confirmButtonText:'Chắc chắn',
      showCancelButton:true,
      cancelButtonText:'Khoan đã',
    }).then((result) =>{
      if(result.isConfirmed){
        this.quizService.deleteQuiz(qId).subscribe( data =>{
          this.quizzes = this.quizzes.filter((quiz:any) => quiz.qId != qId)
          Swal.fire('Success','Xóa thành công!','success')

        },error => {
          Swal.fire('Error','Xảy ra lỗi trong quá trình xóa!','error')
        })
      }
    })

  }

}


