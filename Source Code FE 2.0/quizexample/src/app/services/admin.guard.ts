import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {LoginService} from "./login.service";
import Swal from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  constructor(private loginService:LoginService,private router: Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if(this.loginService.isLoggedIn() && (this.loginService.getAllRole() == 'ADMIN' || this.loginService.getAllRole() == 'ADMIN,NORMAL' || this.loginService.getAllRole() == 'NORMAL,ADMIN')){
      return true;
    }else{
      if(this.loginService.isLoggedIn()){
        Swal.fire('Xác nhận','Bạn k phải là ADMIN?','info').then((result) =>{
          this.router.navigate([''])
        })
      }else{
        this.router.navigate(['login'])
      }
      return false
    }



  }

}
