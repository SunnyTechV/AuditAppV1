import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { IAuditFormSHospGenInfo } from "../audit-form-s-hosp-gen-info.model";
import { AuditFormSHospGenInfoService } from "../service/audit-form-s-hosp-gen-info.service";

@Component({
  templateUrl: "./audit-form-s-hosp-gen-info-delete-dialog.component.html",
})
export class AuditFormSHospGenInfoDeleteDialogComponent {
  auditFormSHospGenInfo?: IAuditFormSHospGenInfo;

  constructor(
    protected auditFormSHospGenInfoService: AuditFormSHospGenInfoService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.auditFormSHospGenInfoService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
