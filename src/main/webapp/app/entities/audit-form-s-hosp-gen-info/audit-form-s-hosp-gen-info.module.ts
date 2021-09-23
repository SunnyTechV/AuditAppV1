import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { AuditFormSHospGenInfoComponent } from "./list/audit-form-s-hosp-gen-info.component";
import { AuditFormSHospGenInfoDetailComponent } from "./detail/audit-form-s-hosp-gen-info-detail.component";
import { AuditFormSHospGenInfoUpdateComponent } from "./update/audit-form-s-hosp-gen-info-update.component";
import { AuditFormSHospGenInfoDeleteDialogComponent } from "./delete/audit-form-s-hosp-gen-info-delete-dialog.component";
import { AuditFormSHospGenInfoRoutingModule } from "./route/audit-form-s-hosp-gen-info-routing.module";

@NgModule({
  imports: [SharedModule, AuditFormSHospGenInfoRoutingModule],
  declarations: [
    AuditFormSHospGenInfoComponent,
    AuditFormSHospGenInfoDetailComponent,
    AuditFormSHospGenInfoUpdateComponent,
    AuditFormSHospGenInfoDeleteDialogComponent,
  ],
  entryComponents: [AuditFormSHospGenInfoDeleteDialogComponent],
})
export class AuditFormSHospGenInfoModule {}
