import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { InventorySupplierDetailsComponent } from "../list/inventory-supplier-details.component";
import { InventorySupplierDetailsDetailComponent } from "../detail/inventory-supplier-details-detail.component";
import { InventorySupplierDetailsUpdateComponent } from "../update/inventory-supplier-details-update.component";
import { InventorySupplierDetailsRoutingResolveService } from "./inventory-supplier-details-routing-resolve.service";

const inventorySupplierDetailsRoute: Routes = [
  {
    path: "",
    component: InventorySupplierDetailsComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: InventorySupplierDetailsDetailComponent,
    resolve: {
      inventorySupplierDetails: InventorySupplierDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: InventorySupplierDetailsUpdateComponent,
    resolve: {
      inventorySupplierDetails: InventorySupplierDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: InventorySupplierDetailsUpdateComponent,
    resolve: {
      inventorySupplierDetails: InventorySupplierDetailsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(inventorySupplierDetailsRoute)],
  exports: [RouterModule],
})
export class InventorySupplierDetailsRoutingModule {}
