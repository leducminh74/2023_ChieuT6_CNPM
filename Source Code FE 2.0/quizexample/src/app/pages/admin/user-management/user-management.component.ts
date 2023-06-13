import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {UserService} from "../../../services/user.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {

  listUser = []

  displayedColumns: string[] = ['id','fullName','username','email','phone','role' , 'status','action'];
  dataSource = new MatTableDataSource<any>();

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort,{ static: true }) sort!: MatSort;

  constructor(private userService:UserService) { }

  ngOnInit(): void {

    this.userService.getAllUser().subscribe((data:any) =>{
      this.listUser = data
      this.dataSource = new MatTableDataSource<any>(this.listUser);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      console.log(this.listUser)
    },error => {
      console.log(error)
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  toString(listRole: [{authority:''}]){
    let result = ''
    for (let role of listRole) {
      result += role.authority + ', '
    }
    return result.substring(0,result.length - 2)
  }

  isExistRoleAdmin(listRole: [{authority:''}]){
    for (let role of listRole) {
      if (role.authority+'' ==  'ADMIN'){
        return true;
      }
    }
    return false
  }

  disableUser(id:number){
    Swal.fire({
      icon:'info',
      title:'Bạn có chắc chắn muốn vô hiệu hóa tài khoản này?',
      showCancelButton:true,
      cancelButtonText:'Khoan đã',
      confirmButtonText:'Chắc chắn'
    }).then((result) =>{
      if(result.isConfirmed){
        this.userService.disableUser(id).subscribe((data:any) =>{
          this.ngOnInit()
          Swal.fire('Success','Vô hiệu hóa tài khoản thành công','success')
        },error => {
          Swal.fire('Error','Xảy ra lỗi trong quá trình thực hiện. Vui lòng thử lại sau!!','error')
        })
      }
    })

  }

  enableUser(id:number){
    Swal.fire({
      icon:'info',
      title:'Bạn có chắc chắn muốn kích hoạt tài khoản này?',
      showCancelButton:true,
      cancelButtonText:'Khoan đã',
      confirmButtonText:'Chắc chắn'
    }).then((result) =>{
      if(result.isConfirmed){
        this.userService.enableUser(id).subscribe((data:any) =>{
          this.ngOnInit()
          Swal.fire('Success','Kích hoạt tài khoản thành công','success')
        },error => {
          Swal.fire('Error','Xảy ra lỗi trong quá trình thực hiện. Vui lòng thử lại sau!!','error')
        })
      }
    })

  }

  addRoleAdmin(id:number){
    Swal.fire({
      icon:'info',
      title:'Bạn có chắc chắn muốn thêm role ADMIN cho tài khoản này?',
      showCancelButton:true,
      cancelButtonText:'Khoan đã',
      confirmButtonText:'Chắc chắn'
    }).then((result) =>{
      if(result.isConfirmed){
        this.userService.addAdminRole(id).subscribe((data:any) =>{
          this.ngOnInit()
          Swal.fire('Success','Thêm role ADMIN cho tài khoản thành công','success')
        },error => {
          Swal.fire('Error','Xảy ra lỗi trong quá trình thực hiện. Vui lòng thử lại sau!!','error')
        })
      }
    })

  }


}
