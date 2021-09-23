import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { InventoryReportComponent } from "../list/inventory-report.component";
import { InventoryReportDetailComponent } from "../detail/inventory-report-detail.component";
import { InventoryReportUpdateComponent } from "../update/inventory-report-update.component";
import { InventoryReportRoutingResolveService } from "./inventory-report-routing-resolve.service";

const inventoryReportRoute: Routes = [
  {
    path: "",
    component: InventoryReportComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: InventoryReportDetailComponent,
    resolve: {
      inventoryReport: InventoryReportRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: InventoryReportUpdateComponent,
    resolve: {
      inventoryReport: InventoryReportRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: InventoryReportUpdateComponent,
    resolve: {
      inventoryReport: InventoryReportRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(inventoryReportRoute)],
  exports: [RouterModule],
})
export class InventoryReportRoutingModule {}
