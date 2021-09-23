import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { IInventorySupplierDetails } from "../inventory-supplier-details.model";
import { InventorySupplierDetailsService } from "../service/inventory-supplier-details.service";

@Component({
  templateUrl: "./inventory-supplier-details-delete-dialog.component.html",
})
export class InventorySupplierDetailsDeleteDialogComponent {
  inventorySupplierDetails?: IInventorySupplierDetails;

  constructor(
    protected inventorySupplierDetailsService: InventorySupplierDetailsService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.inventorySupplierDetailsService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
