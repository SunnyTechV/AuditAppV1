import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { IOxygenConsumptionData } from "../oxygen-consumption-data.model";
import { OxygenConsumptionDataService } from "../service/oxygen-consumption-data.service";

@Component({
  templateUrl: "./oxygen-consumption-data-delete-dialog.component.html",
})
export class OxygenConsumptionDataDeleteDialogComponent {
  oxygenConsumptionData?: IOxygenConsumptionData;

  constructor(
    protected oxygenConsumptionDataService: OxygenConsumptionDataService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.oxygenConsumptionDataService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
