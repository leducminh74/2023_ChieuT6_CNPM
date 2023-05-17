import { Component, OnInit } from '@angular/core';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {ActivatedRoute, Router} from "@angular/router";
import {QuestionService} from "../../../services/question.service";
import Swal from "sweetalert2";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.css']
})
export class EditQuestionComponent implements OnInit {
  public Editor = ClassicEditor;
  quizId:any;
  qId:any = 0;
  qTitle:any;
  question:any;
  constructor(private route:ActivatedRoute,private questionService:QuestionService,private snack:MatSnackBar,private router:Router) { }

  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid']
    this.qTitle = this.route.snapshot.params['title']
    this.quizId = this.route.snapshot.params['quizId']
    this.questionService.getQuestion(this.qId).subscribe((data:any)=>{
      this.question = data
      console.log(this.question)
    },error => {
      Swal.fire('Error','Xảy ra lỗi khi load dữ liệu!!','error')
      console.log(error)
    })
  }

  public editQuestion(){
    if(this.question.option1.trim() == '' || this.question.option1 == null){
      return
    }
    if(this.question.option2.trim() == '' || this.question.option2 == null){
      return
    }
    if(this.question.answer.trim() == '' || this.question.answer == null){
      return
    }

    this.questionService.updateQuestion(this.question).subscribe((data) =>{
      Swal.fire('Success','Cập nhật thành công','success').then(result =>{
        this.router.navigate(['/admin/view-questions/'+this.quizId+'/'+this.qTitle])
      })
    },error => {
      Swal.fire('Error','Xảy ra lỗi trong quá trình cập nhật!!','error')
      console.log(error)
    })



  }

}
