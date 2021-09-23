import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { FireElectricalAuditComponent } from "../list/fire-electrical-audit.component";
import { FireElectricalAuditDetailComponent } from "../detail/fire-electrical-audit-detail.component";
import { FireElectricalAuditUpdateComponent } from "../update/fire-electrical-audit-update.component";
import { FireElectricalAuditRoutingResolveService } from "./fire-electrical-audit-routing-resolve.service";

const fireElectricalAuditRoute: Routes = [
  {
    path: "",
    component: FireElectricalAuditComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: FireElectricalAuditDetailComponent,
    resolve: {
      fireElectricalAudit: FireElectricalAuditRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: FireElectricalAuditUpdateComponent,
    resolve: {
      fireElectricalAudit: FireElectricalAuditRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: FireElectricalAuditUpdateComponent,
    resolve: {
      fireElectricalAudit: FireElectricalAuditRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(fireElectricalAuditRoute)],
  exports: [RouterModule],
})
export class FireElectricalAuditRoutingModule {}
