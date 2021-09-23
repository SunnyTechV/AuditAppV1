import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { InventoryReportComponent } from "./list/inventory-report.component";
import { InventoryReportDetailComponent } from "./detail/inventory-report-detail.component";
import { InventoryReportUpdateComponent } from "./update/inventory-report-update.component";
import { InventoryReportDeleteDialogComponent } from "./delete/inventory-report-delete-dialog.component";
import { InventoryReportRoutingModule } from "./route/inventory-report-routing.module";

@NgModule({
  imports: [SharedModule, InventoryReportRoutingModule],
  declarations: [
    InventoryReportComponent,
    InventoryReportDetailComponent,
    InventoryReportUpdateComponent,
    InventoryReportDeleteDialogComponent,
  ],
  entryComponents: [InventoryReportDeleteDialogComponent],
})
export class InventoryReportModule {}
