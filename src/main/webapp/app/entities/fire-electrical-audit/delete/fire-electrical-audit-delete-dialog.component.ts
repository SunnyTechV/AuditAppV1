import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { IFireElectricalAudit } from "../fire-electrical-audit.model";
import { FireElectricalAuditService } from "../service/fire-electrical-audit.service";

@Component({
  templateUrl: "./fire-electrical-audit-delete-dialog.component.html",
})
export class FireElectricalAuditDeleteDialogComponent {
  fireElectricalAudit?: IFireElectricalAudit;

  constructor(
    protected fireElectricalAuditService: FireElectricalAuditService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fireElectricalAuditService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
