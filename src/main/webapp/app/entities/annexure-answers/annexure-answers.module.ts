import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { AnnexureAnswersComponent } from "./list/annexure-answers.component";
import { AnnexureAnswersDetailComponent } from "./detail/annexure-answers-detail.component";
import { AnnexureAnswersUpdateComponent } from "./update/annexure-answers-update.component";
import { AnnexureAnswersDeleteDialogComponent } from "./delete/annexure-answers-delete-dialog.component";
import { AnnexureAnswersRoutingModule } from "./route/annexure-answers-routing.module";

@NgModule({
  imports: [SharedModule, AnnexureAnswersRoutingModule],
  declarations: [
    AnnexureAnswersComponent,
    AnnexureAnswersDetailComponent,
    AnnexureAnswersUpdateComponent,
    AnnexureAnswersDeleteDialogComponent,
  ],
  entryComponents: [AnnexureAnswersDeleteDialogComponent],
})
export class AnnexureAnswersModule {}
