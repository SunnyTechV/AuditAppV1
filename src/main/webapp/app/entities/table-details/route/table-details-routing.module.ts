import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { TableDetailsComponent } from "../list/table-details.component";
import { TableDetailsDetailComponent } from "../detail/table-details-detail.component";
import { TableDetailsUpdateComponent } from "../update/table-details-update.component";
import { TableDetailsRoutingResolveService } from "./table-details-routing-resolve.service";

const tableDetailsRoute: Routes = [
  {
    path: "",
    component: TableDetailsComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: TableDetailsDetailComponent,
    resolve: {
      tableDetails: TableDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: TableDetailsUpdateComponent,
    resolve: {
      tableDetails: TableDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: TableDetailsUpdateComponent,
    resolve: {
      tableDetails: TableDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tableDetailsRoute)],
  exports: [RouterModule],
})
export class TableDetailsRoutingModule {}
