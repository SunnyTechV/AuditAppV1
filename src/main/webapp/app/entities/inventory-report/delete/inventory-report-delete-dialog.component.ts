import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { IInventoryReport } from "../inventory-report.model";
import { InventoryReportService } from "../service/inventory-report.service";

@Component({
  templateUrl: "./inventory-report-delete-dialog.component.html",
})
export class InventoryReportDeleteDialogComponent {
  inventoryReport?: IInventoryReport;

  constructor(
    protected inventoryReportService: InventoryReportService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.inventoryReportService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
