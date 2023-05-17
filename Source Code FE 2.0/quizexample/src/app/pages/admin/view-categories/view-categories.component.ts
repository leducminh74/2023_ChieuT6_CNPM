import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../../../services/category.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent implements OnInit {

  categories:any = []

  constructor(private categoryService:CategoryService) { }

  ngOnInit(): void {
    this.categoryService.categories().subscribe((data:any) =>{
      this.categories = data
      console.log(this.categories)
    },error => {
      console.log(error)
      Swal.fire('Error','Lỗi khi load dữ liệu','error')
    })
  }

}
