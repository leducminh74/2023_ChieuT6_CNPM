import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import {LoginComponent} from "./pages/login/login.component";
import {HomeComponent} from "./pages/home/home.component";
import {DashboardComponent} from "./pages/admin/dashboard/dashboard.component";
import {UserDashboardComponent} from "./pages/user/user-dashboard/user-dashboard.component";
import {AdminGuard} from "./services/admin.guard";
import {NormalGuard} from "./services/normal.guard";
import {ProfileComponent} from "./pages/profile/profile.component";
import {WelcomeComponent} from "./pages/admin/welcome/welcome.component";
import {ViewCategoriesComponent} from "./pages/admin/view-categories/view-categories.component";
import {AddCategoryComponent} from "./pages/admin/add-category/add-category.component";
import {ViewQuizzesComponent} from "./pages/admin/view-quizzes/view-quizzes.component";
import {AddQuizComponent} from "./pages/admin/add-quiz/add-quiz.component";
import {UpdateQuizComponent} from "./pages/admin/update-quiz/update-quiz.component";
import {ViewQuestionsComponent} from "./pages/admin/view-questions/view-questions.component";
import {AddQuestionComponent} from "./pages/admin/add-question/add-question.component";
import {EditQuestionComponent} from "./pages/admin/edit-question/edit-question.component";
import {LoadQuizComponent} from "./pages/user/load-quiz/load-quiz.component";
import {InstructionsComponent} from "./pages/user/instructions/instructions.component";
import {StartComponent} from "./pages/user/start/start.component";
import {LoginGuard} from "./services/login.guard";
import {ProfileUserComponent} from "./pages/user/profile-user/profile-user.component";
import {UpdateProfileUserComponent} from "./pages/user/update-profile-user/update-profile-user.component";
import {UpdateProfileAdminComponent} from "./pages/admin/update-profile-admin/update-profile-admin.component";
import {TestHistoryComponent} from "./pages/user/test-history/test-history.component";
import {TestRankingComponent} from "./pages/user/test-ranking/test-ranking.component";
import {UserManagementComponent} from "./pages/admin/user-management/user-management.component";

const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    pathMatch: 'full',
    canActivate:[LoginGuard]
  },
  {
    path:'signup',
    component: SignupComponent,
    pathMatch:'full'
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path:'admin',
    component: DashboardComponent,
    canActivate:[AdminGuard],
    children:[
      {
        path:'',
        component: WelcomeComponent
      },
      {
        path:'profile',
        component: ProfileComponent
      },{
        path: 'profile/update',
        component: UpdateProfileAdminComponent
      },{
        path:'categories',
        component:ViewCategoriesComponent
      },{
        path:'add-category',
        component:AddCategoryComponent
      },{
        path:'quizzes',
        component:ViewQuizzesComponent
      },{
        path:'add-quiz',
        component:AddQuizComponent
      },{
        path:'quiz/:qid',
        component:UpdateQuizComponent
      },{
        path:'view-questions/:qid/:title',
        component:ViewQuestionsComponent
      },{
        path:'add-question/:qid/:title',
        component:AddQuestionComponent
      },{
        path:'quiz/:quizId/:title/question/:qid',
        component:EditQuestionComponent
      },{
        path: 'user-management',
        component: UserManagementComponent
      }
    ],
  },
  {
    path:'user-dashboard',
    component:UserDashboardComponent,
    canActivate:[NormalGuard],
    children:[
      {
        path: ':catId',
        component: LoadQuizComponent
      },{
        path: 'instructions/:qid',
        component:InstructionsComponent
      },{
        path: 'profile/user',
        component: ProfileUserComponent,
      },
      {
        path: 'profile/update-profile',
        component:UpdateProfileUserComponent
      },{
        path: 'test/history',
        component: TestHistoryComponent
      },{
        path: 'test/rank',
        component: TestRankingComponent
      }
    ]
  },
  {
    path: 'start/:qid/:tid',
    component: StartComponent,
    canActivate:[NormalGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
