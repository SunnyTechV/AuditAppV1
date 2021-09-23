import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { AuditFormSHospGenInfoComponent } from "../list/audit-form-s-hosp-gen-info.component";
import { AuditFormSHospGenInfoDetailComponent } from "../detail/audit-form-s-hosp-gen-info-detail.component";
import { AuditFormSHospGenInfoUpdateComponent } from "../update/audit-form-s-hosp-gen-info-update.component";
import { AuditFormSHospGenInfoRoutingResolveService } from "./audit-form-s-hosp-gen-info-routing-resolve.service";

const auditFormSHospGenInfoRoute: Routes = [
  {
    path: "",
    component: AuditFormSHospGenInfoComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: AuditFormSHospGenInfoDetailComponent,
    resolve: {
      auditFormSHospGenInfo: AuditFormSHospGenInfoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: AuditFormSHospGenInfoUpdateComponent,
    resolve: {
      auditFormSHospGenInfo: AuditFormSHospGenInfoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: AuditFormSHospGenInfoUpdateComponent,
    resolve: {
      auditFormSHospGenInfo: AuditFormSHospGenInfoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(auditFormSHospGenInfoRoute)],
  exports: [RouterModule],
})
export class AuditFormSHospGenInfoRoutingModule {}
