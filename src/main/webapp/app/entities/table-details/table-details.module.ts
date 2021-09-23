import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { TableDetailsComponent } from "./list/table-details.component";
import { TableDetailsDetailComponent } from "./detail/table-details-detail.component";
import { TableDetailsUpdateComponent } from "./update/table-details-update.component";
import { TableDetailsDeleteDialogComponent } from "./delete/table-details-delete-dialog.component";
import { TableDetailsRoutingModule } from "./route/table-details-routing.module";

@NgModule({
  imports: [SharedModule, TableDetailsRoutingModule],
  declarations: [
    TableDetailsComponent,
    TableDetailsDetailComponent,
    TableDetailsUpdateComponent,
    TableDetailsDeleteDialogComponent,
  ],
  entryComponents: [TableDetailsDeleteDialogComponent],
})
export class TableDetailsModule {}
