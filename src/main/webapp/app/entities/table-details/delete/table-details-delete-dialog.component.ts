import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { ITableDetails } from "../table-details.model";
import { TableDetailsService } from "../service/table-details.service";

@Component({
  templateUrl: "./table-details-delete-dialog.component.html",
})
export class TableDetailsDeleteDialogComponent {
  tableDetails?: ITableDetails;

  constructor(
    protected tableDetailsService: TableDetailsService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tableDetailsService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
