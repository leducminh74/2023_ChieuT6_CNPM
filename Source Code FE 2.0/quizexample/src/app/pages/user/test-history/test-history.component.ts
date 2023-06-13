import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource, MatTableModule} from "@angular/material/table";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {TestService} from "../../../services/test.service";
import {LoginService} from "../../../services/login.service";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-test-history',
  templateUrl: './test-history.component.html',
  styleUrls: ['./test-history.component.css']
})
export class TestHistoryComponent implements OnInit  {

  listTestByUser = []
  user:any = null

  displayedColumns: string[] = ['categoryTitle','quizTitle','quizNumberOfQuestion','numberOfQuestionAttempted' , 'numberOfQuestionCorrect', 'marks','examTime','action'];
  dataSource = new MatTableDataSource<any>();

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(MatSort,{ static: true }) sort!: MatSort;

  constructor(private testService:TestService, private loginService:LoginService) { }



  ngOnInit(): void {

    this.user = this.loginService.getUser();
    this.testService.getListTestByUser(this.user.id).subscribe((data:any) =>{
      this.listTestByUser = data
      this.dataSource = new MatTableDataSource<any>(this.listTestByUser);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      console.log(this.dataSource)
      console.log(this.listTestByUser)
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

  public formattedTimer(timer:any){
    let mm = Math.floor(timer/60)
    let ss = timer - mm*60
    if(mm == 0){
      return`${ss} giây`;
    }
    return`${mm} phút : ${ss} giây`;
  }

}


