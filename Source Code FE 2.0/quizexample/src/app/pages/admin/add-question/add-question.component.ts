import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {QuestionService} from "../../../services/question.service";
import Swal from "sweetalert2";
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

  public Editor = ClassicEditor;
  qId:any;
  qTitle:any;
  question = {
    quiz:{
      qId:''
    },
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:''
  }


  constructor(private route:ActivatedRoute,private questionService:QuestionService,private router:Router) { }

  ngOnInit(): void {
    this.qId = this.route.snapshot.params['qid']
    this.qTitle = this.route.snapshot.params['title']
    this.question.quiz.qId = this.qId

  }

  formSubmit(){
    if(this.question.content.trim() == '' || this.question.content == null){
      return
    }
    if(this.question.option1.trim() == '' || this.question.option1 == null){
      return
    }
    if(this.question.option2.trim() == '' || this.question.option2 == null){
      return
    }
    if(this.question.answer.trim() == '' || this.question.answer == null){
      return
    }

    this.questionService.addQuestion(this.question).subscribe((dataa:any)=>{
      Swal.fire('Success','Thêm câu hỏi thành công','success')
      this.question = {
        quiz:{
          qId:''
        },
        content:'',
        option1:'',
        option2:'',
        option3:'',
        option4:'',
        answer:''
      }
      this.router.navigate(['/admin/view-questions/' +this.qId+'/'+this.qTitle])
    },error => {
      Swal.fire('Error','Xảy ra lỗi trong quá trình thêm câu hỏi!!','error')

    })

  }

}
