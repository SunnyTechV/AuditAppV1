import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { AuditPatientMonitoringFormComponent } from "./list/audit-patient-monitoring-form.component";
import { AuditPatientMonitoringFormDetailComponent } from "./detail/audit-patient-monitoring-form-detail.component";
import { AuditPatientMonitoringFormUpdateComponent } from "./update/audit-patient-monitoring-form-update.component";
import { AuditPatientMonitoringFormDeleteDialogComponent } from "./delete/audit-patient-monitoring-form-delete-dialog.component";
import { AuditPatientMonitoringFormRoutingModule } from "./route/audit-patient-monitoring-form-routing.module";

@NgModule({
  imports: [SharedModule, AuditPatientMonitoringFormRoutingModule],
  declarations: [
    AuditPatientMonitoringFormComponent,
    AuditPatientMonitoringFormDetailComponent,
    AuditPatientMonitoringFormUpdateComponent,
    AuditPatientMonitoringFormDeleteDialogComponent,
  ],
  entryComponents: [AuditPatientMonitoringFormDeleteDialogComponent],
})
export class AuditPatientMonitoringFormModule {}
