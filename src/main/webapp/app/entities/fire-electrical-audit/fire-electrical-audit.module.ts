import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { FireElectricalAuditComponent } from "./list/fire-electrical-audit.component";
import { FireElectricalAuditDetailComponent } from "./detail/fire-electrical-audit-detail.component";
import { FireElectricalAuditUpdateComponent } from "./update/fire-electrical-audit-update.component";
import { FireElectricalAuditDeleteDialogComponent } from "./delete/fire-electrical-audit-delete-dialog.component";
import { FireElectricalAuditRoutingModule } from "./route/fire-electrical-audit-routing.module";

@NgModule({
  imports: [SharedModule, FireElectricalAuditRoutingModule],
  declarations: [
    FireElectricalAuditComponent,
    FireElectricalAuditDetailComponent,
    FireElectricalAuditUpdateComponent,
    FireElectricalAuditDeleteDialogComponent,
  ],
  entryComponents: [FireElectricalAuditDeleteDialogComponent],
})
export class FireElectricalAuditModule {}
