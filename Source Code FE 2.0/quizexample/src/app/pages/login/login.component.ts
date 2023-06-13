import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
      username:'',
      password:''
  }

  constructor(private snack:MatSnackBar, private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {

    if(this.loginService.isLoggedIn()){
      let userRole = this.loginService.getUserRole()
      if(userRole == 'ADMIN'){
        Swal.fire('Bạn đã đăng nhập?','Vui lòng đăng xuất trước khi đăng nhập tài khoản khác!!','info').then(result =>{
          this.router.navigate(['/admin'])
        })
      }else if(userRole == 'NORMAL'){
        Swal.fire('Bạn đã đăng nhập?','Vui lòng đăng xuất trước khi đăng nhập tài khoản khác!!','info').then(result =>{
          this.router.navigate(['user-dashboard/0'])
        })
      }
    }
  }


  formSubmit(){
    if(this.loginData.username.trim() == '' || this.loginData.username == null ){
      this.snack.open('Vui lòng nhập tài khoản!!','ok',{
        duration:3000,
      });
      return;
    }else if(this.loginData.password.trim() == '' || this.loginData.password == null){
      this.snack.open('Vui lòng nhập mật khẩu!!','ok',{
        duration:3000,
      });
      return;
    }

    this.loginService.generateToken(this.loginData).subscribe((data:any) =>{
      this.loginService.loginUser(data.token);
      this.loginService.getCurrentUser().subscribe((user:any) =>{
        this.loginService.setUser(user)
          if(this.loginService.getUserRole() == 'ADMIN'){
            this.router.navigate(['admin'])
            this.loginService.loginStatusSubject.next(true)
          }else if(this.loginService.getUserRole() =='NORMAL'){
            this.router.navigate(['user-dashboard/0'])
            this.loginService.loginStatusSubject.next(true)
          }else{
            this.loginService.logout()
          }

      })
    },error => {
      console.log(error)
      this.snack.open('Thông tin đăng nhập không chính xác.Vui lòng thử lại!!','ok',{
        duration:3000
      })
    })


  }
}
