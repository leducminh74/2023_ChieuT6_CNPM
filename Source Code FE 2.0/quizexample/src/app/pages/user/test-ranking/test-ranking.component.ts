import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {TestService} from "../../../services/test.service";
import {QuizService} from "../../../services/quiz.service";
import {LoginService} from "../../../services/login.service";

@Component({
  selector: 'app-test-ranking',
  templateUrl: './test-ranking.component.html',
  styleUrls: ['./test-ranking.component.css']
})
export class TestRankingComponent implements OnInit {

  listTestByQuiz = []
  listQuiz:any
  quizId:any = null;
  user:any = null;

  displayedColumns: string[] = ['ranking','user','marks','examTime' , 'status'];
  dataSource = new MatTableDataSource<any>();

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private testService:TestService, private quizService:QuizService,private loginService:LoginService) { }

  ngOnInit(): void {

    this.user = this.loginService.getUser()

    this.quizService.quizzes().subscribe((data:any) =>{
      this.listQuiz = data
    },error => {
      console.log(error)
    })

    if(this.quizId){
      this.onLoadListTestByQuiz()
    }

  }

  onLoadListTestByQuiz(){
    this.testService.getTestByQuizOrderByMarks(this.quizId).subscribe((data:any) =>{
      this.listTestByQuiz = data
      this.dataSource = new MatTableDataSource<any>(this.listTestByQuiz);
      this.dataSource.paginator = this.paginator;
      console.log(this.dataSource)
      console.log(this.listTestByQuiz)
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

  selectedQuiz(event:any){
    this.quizId = event.value
    this.onLoadListTestByQuiz()

  }

}
