import { Component, OnInit } from '@angular/core';
import {ChartConfiguration, ChartData, ChartEvent, ChartType} from "chart.js";
import {ManagementService} from "../../../services/management.service";
import {ngxCsv} from "ngx-csv";
import {QuizService} from "../../../services/quiz.service";
import _default from "chart.js/dist/core/core.interaction";
import dataset = _default.modes.dataset;

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  totalUser = 0;
  listQuiz:any = null;
  quizName = '';
  totalNumberOfTests = 0;
  yearSelected = 2023;
  qid = 0;
  barChartData !: ChartData<'bar'>
  pieChartData !: ChartData<'pie', number[], string | string[]>

  constructor(private managementService:ManagementService, private quizService:QuizService) {
  }

  ngOnInit(): void {
    this.quizService.quizzes().subscribe((data:any) =>{
      this.listQuiz = data
    },error => {
      console.log(error)
    })

    this.managementService.countTotalUser().subscribe((data:any) =>{
      this.totalUser = data
      console.log(data)
    },error => {
      console.log(error)
    })

    this.onloadNumberOfTests()

    this.onLoadPieChartUser()

    console.log(this.pieChartData)

    this.onLoadTotalNumberOfTest()
  }

  public onLoadPieChartUser(){
    this.managementService.accountStatistics().subscribe((data:any)=>{
      this.pieChartData = {
        labels:['Tài khoản bị vô hiệu hóa ','Tài khoản hoạt động'],
        datasets:[{
          data:Object.values(data),
          backgroundColor: ['#FF6384', '#36A2EB' , 'grey'],
          hoverBackgroundColor: ['#FF6384', '#36A2EB' , 'grey']
        }]
      }
    })
  }

  public onloadNumberOfTests(){
    this.managementService.numberOfTests(this.yearSelected,this.qid).subscribe((data:any) =>{
      this.barChartData = {
        labels:['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7','Tháng 8','Tháng 9','Tháng 10','Tháng 11','Tháng 12'],
        datasets:[
          {
            label:'Lượt thi',
            data:Object.values(data)
          }
        ]
      }
    })
  }

  public onLoadTotalNumberOfTest(){
    this.managementService.totalNumberOfTest(this.yearSelected,this.qid).subscribe((data:any) =>{
      this.totalNumberOfTests = data
    },error => {
      console.log(error)
    })
  }

  public barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    scales: {
      x: {
      },
      y: {
        min: 0,
      }
    },
    plugins: {
      title:{
        display:true,
        text:'Biểu đồ lượt thi'
      },
      legend: {
        display: true,

      }
    }
  };
  public barChartType: ChartType = 'bar';
  public barChartPlugins = [
  ];

  // events
  public chartClicked({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    console.log(event, active);
  }

  onLoadYear(event:any){
    this.yearSelected = event.value
    this.onloadNumberOfTests()
    this.onLoadTotalNumberOfTest()

  }

  onLoadQuiz(event:any){
    this.qid = event.value
    this.quizService.getQuiz(this.qid).subscribe((data:any) =>{
      this.quizName = data.title
    })
    this.onloadNumberOfTests()
    this.onLoadTotalNumberOfTest()
  }

  download(){
    var data =
      [
        {
          month:Object.values(this.barChartData.datasets[0]),
          year:this.yearSelected,
          total:this.totalNumberOfTests
        }

      ]
    var options = {
      fieldSeparator: ',',
      quoteStrings: '"',
      decimalseparator: '.',
      showLabels: true,
      showTitle: true,
      title: this.qid == 0 ? 'Chi tiết lượt thi tất cả các đề năm '+this.yearSelected:'Chi tiết lượt thi đề '+this.quizName + ' năm ' + this.yearSelected,
      useBom: true,
      noDownload: false,
      headers: ["","Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12","Năm","Tổng cộng"]
    };

    new ngxCsv(data, "lượt thi "+this.yearSelected, options);
  }

  // Pie
  public pieChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    maintainAspectRatio:false,
    plugins: {
      title:{
        display:true,
        text:'Biểu đồ thống kê tài khoản'
      },
      legend: {
        display: true,
        position: 'top',
      },
    }
  };

  public pieChartType: ChartType = 'pie';

  public pieChartPlugins = [
  ];


}

