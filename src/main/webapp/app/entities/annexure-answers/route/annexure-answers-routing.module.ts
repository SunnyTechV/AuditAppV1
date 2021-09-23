import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { AnnexureAnswersComponent } from "../list/annexure-answers.component";
import { AnnexureAnswersDetailComponent } from "../detail/annexure-answers-detail.component";
import { AnnexureAnswersUpdateComponent } from "../update/annexure-answers-update.component";
import { AnnexureAnswersRoutingResolveService } from "./annexure-answers-routing-resolve.service";

const annexureAnswersRoute: Routes = [
  {
    path: "",
    component: AnnexureAnswersComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: AnnexureAnswersDetailComponent,
    resolve: {
      annexureAnswers: AnnexureAnswersRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: AnnexureAnswersUpdateComponent,
    resolve: {
      annexureAnswers: AnnexureAnswersRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: AnnexureAnswersUpdateComponent,
    resolve: {
      annexureAnswers: AnnexureAnswersRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(annexureAnswersRoute)],
  exports: [RouterModule],
})
export class AnnexureAnswersRoutingModule {}
