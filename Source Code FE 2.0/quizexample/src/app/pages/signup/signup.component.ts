import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import Swal from 'sweetalert2'
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService,private snack:MatSnackBar, private loginService:LoginService, private router:Router
  ) { }

  public user = {
    username:'',
    password:'',
    firstName:'',
    lastName:'',
    email:'',
    phone:''
  };

  ngOnInit(): void {
    if(this.loginService.isLoggedIn()){
      let userRole = this.loginService.getUserRole()
      if(userRole == 'ADMIN'){
        Swal.fire('Bạn đã đăng nhập?','Vui lòng đăng xuất trước khi đăng ký tài khoản khác!!','info').then(result =>{
          this.router.navigate(['/admin'])
        })
      }else if(userRole == 'NORMAL'){
        Swal.fire('Bạn đã đăng nhập?','Vui lòng đăng xuất trước khi đăng ký tài khoản khác!!','info').then(result =>{
          this.router.navigate(['user-dashboard/0'])
        })
      }
    }

  }

  formSubmit(){
    console.log(this.user)
    if(this.user.username.trim() == '' || this.user.username == null){
      this.snack.open("Vui lòng nhập tài khoản!!",'ok',{
        duration:3000
      })
      return;
    }else if(this.user.password.trim() == '' || this.user.password == null){
      this.snack.open("Vui lòng nhập mật khẩu!!",'ok',{
        duration:3000
      })
      return;
    }else if(this.user.firstName.trim() == '' || this.user.firstName == null){
      this.snack.open("Vui lòng nhập họ!!",'ok',{
        duration:3000
      })
      return;
    }else if(this.user.lastName.trim() == '' || this.user.lastName == null){
      this.snack.open("Vui lòng nhập tên!!",'ok',{
        duration:3000
      })
      return;
    }else if(this.user.email.trim() == '' || this.user.email == null){
      this.snack.open("Vui lòng nhập email!!",'ok',{
        duration:3000
      })
      return;
    }else if(this.user.phone.length == 0 || this.user.phone == null){
      this.snack.open("Vui lòng nhập số điện thoại!!",'ok',{
        duration:3000
      })
      return;
    }else{
      this.userService.addUser(this.user).subscribe((data) =>{
        console.log(data);
        Swal.fire('Successfully','Đăng ký thành công','success').then(result =>{
          this.router.navigate(['/login'])
        })

      },(error) => {
        console.log(error)
        Swal.fire('Error','Username is already exist','error')

      })
    }


  }

}
