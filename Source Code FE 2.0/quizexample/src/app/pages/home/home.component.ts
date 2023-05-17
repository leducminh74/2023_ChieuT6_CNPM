import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {
    let userRole = this.loginService.getUserRole()
    console.log(userRole)
    if(userRole == 'ADMIN'){
      this.router.navigate(['admin'])
    }else if(userRole == 'NORMAL'){
      this.router.navigate(['user-dashboard/0'])
    }
  }

}
