import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../../services/login.service";

@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrls: ['./profile-user.component.css']
})
export class ProfileUserComponent implements OnInit {
  user:any = null;
  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
    this.user = this.loginService.getUser();
  }

  toString(listRole: [{authority:''}]){
    let result = ''
    for (let role of listRole) {
      result += role.authority + ', '
    }
    return result.substring(0,result.length - 2)
  }
}
