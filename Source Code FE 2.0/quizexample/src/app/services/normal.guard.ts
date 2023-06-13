import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {LoginService} from "./login.service";
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class NormalGuard implements CanActivate {
  constructor(private loginService:LoginService,private router:Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.loginService.isLoggedIn() && this.loginService.getAllRole() == 'NORMAL'){
      return true;
    }else{
      if(this.loginService.isLoggedIn()){
        Swal.fire('Xác nhận','Vui lòng tạo tài khoản mới để sử dụng với vai trò USER!!','info').then((result) =>{
          this.router.navigate(['admin'])
        })
      }else{
        this.router.navigate(['login'])
      }
      return false;
    }

  }

}
