import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { OxygenConsumptionDataComponent } from "./list/oxygen-consumption-data.component";
import { OxygenConsumptionDataDetailComponent } from "./detail/oxygen-consumption-data-detail.component";
import { OxygenConsumptionDataUpdateComponent } from "./update/oxygen-consumption-data-update.component";
import { OxygenConsumptionDataDeleteDialogComponent } from "./delete/oxygen-consumption-data-delete-dialog.component";
import { OxygenConsumptionDataRoutingModule } from "./route/oxygen-consumption-data-routing.module";

@NgModule({
  imports: [SharedModule, OxygenConsumptionDataRoutingModule],
  declarations: [
    OxygenConsumptionDataComponent,
    OxygenConsumptionDataDetailComponent,
    OxygenConsumptionDataUpdateComponent,
    OxygenConsumptionDataDeleteDialogComponent,
  ],
  entryComponents: [OxygenConsumptionDataDeleteDialogComponent],
})
export class OxygenConsumptionDataModule {}
