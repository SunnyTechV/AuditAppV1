import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { IAuditPatientMonitoringForm } from "../audit-patient-monitoring-form.model";
import { AuditPatientMonitoringFormService } from "../service/audit-patient-monitoring-form.service";

@Component({
  templateUrl: "./audit-patient-monitoring-form-delete-dialog.component.html",
})
export class AuditPatientMonitoringFormDeleteDialogComponent {
  auditPatientMonitoringForm?: IAuditPatientMonitoringForm;

  constructor(
    protected auditPatientMonitoringFormService: AuditPatientMonitoringFormService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.auditPatientMonitoringFormService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
