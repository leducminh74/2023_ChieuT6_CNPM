import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(public loginService:LoginService ) { }

  ngOnInit(): void {
  }

  logout(){
    this.loginService.logout()
    window.location.reload()
  }

}
