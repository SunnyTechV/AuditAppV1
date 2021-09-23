import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { InventorySupplierDetailsComponent } from "./list/inventory-supplier-details.component";
import { InventorySupplierDetailsDetailComponent } from "./detail/inventory-supplier-details-detail.component";
import { InventorySupplierDetailsUpdateComponent } from "./update/inventory-supplier-details-update.component";
import { InventorySupplierDetailsDeleteDialogComponent } from "./delete/inventory-supplier-details-delete-dialog.component";
import { InventorySupplierDetailsRoutingModule } from "./route/inventory-supplier-details-routing.module";

@NgModule({
  imports: [SharedModule, InventorySupplierDetailsRoutingModule],
  declarations: [
    InventorySupplierDetailsComponent,
    InventorySupplierDetailsDetailComponent,
    InventorySupplierDetailsUpdateComponent,
    InventorySupplierDetailsDeleteDialogComponent,
  ],
  entryComponents: [InventorySupplierDetailsDeleteDialogComponent],
})
export class InventorySupplierDetailsModule {}
