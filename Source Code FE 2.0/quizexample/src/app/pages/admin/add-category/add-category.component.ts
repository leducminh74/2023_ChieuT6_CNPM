import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {CategoryService} from "../../../services/category.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  category = {
    title:'',
    description:''
  }

  constructor(private snake:MatSnackBar,private categoryService:CategoryService) { }

  ngOnInit(): void {
  }

  formSubmit(){
    if(this.category.title.trim() == '' || this.category.title == null){
      this.snake.open('Vui lòng nhập tiêu đề','ok',{
        duration:3000
      })
      return
    }else if(this.category.description.trim() == '' || this.category.description == null){
      this.snake.open('Vui lòng nhập mô tả','ok',{
        duration:3000
      })
      return
    }

    this.categoryService.addCategory(this.category).subscribe((data:any) =>{
      this.category.title = ''
      this.category.description = ''
      Swal.fire('Success !!','Thêm môn thi thành công','success')
    },error => {
      console.log(error)
      Swal.fire('Error !!','Thêm môn thi thất bại! Có vẻ như đã xảy ra lỗi gì đó.','error')
    })
  }

}
