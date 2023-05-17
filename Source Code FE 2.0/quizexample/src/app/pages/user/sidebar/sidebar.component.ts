import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../../../services/category.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-sidebar-user',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  categories:any;

  constructor(private categoryService:CategoryService,private snack:MatSnackBar) { }

  ngOnInit(): void {
    this.categoryService.categories().subscribe((data:any)=>{
      this.categories = data;
      console.log(this.categories)
    },error => {
      console.log(error)
      this.snack.open('Lỗi khi load dữ liệu từ máy chủ!!','ok',{
        duration:3000
      })
    })
  }

}
