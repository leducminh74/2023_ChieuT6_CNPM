import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../../services/login.service";
import {UserService} from "../../../services/user.service";
import Swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-update-profile-user',
  templateUrl: './update-profile-user.component.html',
  styleUrls: ['./update-profile-user.component.css']
})
export class UpdateProfileUserComponent implements OnInit {
  user:any = null;
  constructor(private loginService:LoginService,private userService:UserService,private router:Router) { }

  ngOnInit(): void {
    this.user = this.loginService.getUser();
  }

  updateUser(){
    console.log(this.user)
    this.userService.updateUser(this.user.id,this.user).subscribe((data:any)=>{
      this.loginService.getCurrentUser().subscribe((user:any) => {
        this.loginService.setUser(user)
      })
      Swal.fire('Success','Cập nhật thông tin thành công','success').then((result) =>{
        this.router.navigate(['user-dashboard/profile/user'])
      })

    },error => {
      Swal.fire('Error','Cập nhật thất bại','error')
    })
  }

}
