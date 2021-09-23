import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { UserRouteAccessService } from "app/core/auth/user-route-access.service";
import { AuditPatientMonitoringFormComponent } from "../list/audit-patient-monitoring-form.component";
import { AuditPatientMonitoringFormDetailComponent } from "../detail/audit-patient-monitoring-form-detail.component";
import { AuditPatientMonitoringFormUpdateComponent } from "../update/audit-patient-monitoring-form-update.component";
import { AuditPatientMonitoringFormRoutingResolveService } from "./audit-patient-monitoring-form-routing-resolve.service";

const auditPatientMonitoringFormRoute: Routes = [
  {
    path: "",
    component: AuditPatientMonitoringFormComponent,
    data: {
      defaultSort: "id,asc",
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/view",
    component: AuditPatientMonitoringFormDetailComponent,
    resolve: {
      auditPatientMonitoringForm:
        AuditPatientMonitoringFormRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: "new",
    component: AuditPatientMonitoringFormUpdateComponent,
    resolve: {
      auditPatientMonitoringForm:
        AuditPatientMonitoringFormRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ":id/edit",
    component: AuditPatientMonitoringFormUpdateComponent,
    resolve: {
      auditPatientMonitoringForm:
        AuditPatientMonitoringFormRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(auditPatientMonitoringFormRoute)],
  exports: [RouterModule],
})
export class AuditPatientMonitoringFormRoutingModule {}
