import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { AnnexureQuestionsComponent } from "../list/annexure-questions.component";
import { AnnexureQuestionsDetailComponent } from "../detail/annexure-questions-detail.component";
import { AnnexureQuestionsUpdateComponent } from "../update/annexure-questions-update.component";
import { AnnexureQuestionsRoutingResolveService } from "./annexure-questions-routing-resolve.service";

const annexureQuestionsRoute: Routes = [
  {
    path: "",
    component: AnnexureQuestionsComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: AnnexureQuestionsDetailComponent,
    resolve: {
      annexureQuestions: AnnexureQuestionsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: AnnexureQuestionsUpdateComponent,
    resolve: {
      annexureQuestions: AnnexureQuestionsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: AnnexureQuestionsUpdateComponent,
    resolve: {
      annexureQuestions: AnnexureQuestionsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(annexureQuestionsRoute)],
  exports: [RouterModule],
})
export class AnnexureQuestionsRoutingModule {}
