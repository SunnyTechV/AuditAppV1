import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { OxygenConsumptionDataComponent } from "../list/oxygen-consumption-data.component";
import { OxygenConsumptionDataDetailComponent } from "../detail/oxygen-consumption-data-detail.component";
import { OxygenConsumptionDataUpdateComponent } from "../update/oxygen-consumption-data-update.component";
import { OxygenConsumptionDataRoutingResolveService } from "./oxygen-consumption-data-routing-resolve.service";

const oxygenConsumptionDataRoute: Routes = [
  {
    path: "",
    component: OxygenConsumptionDataComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: OxygenConsumptionDataDetailComponent,
    resolve: {
      oxygenConsumptionData: OxygenConsumptionDataRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: OxygenConsumptionDataUpdateComponent,
    resolve: {
      oxygenConsumptionData: OxygenConsumptionDataRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: OxygenConsumptionDataUpdateComponent,
    resolve: {
      oxygenConsumptionData: OxygenConsumptionDataRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(oxygenConsumptionDataRoute)],
  exports: [RouterModule],
})
export class OxygenConsumptionDataRoutingModule {}
