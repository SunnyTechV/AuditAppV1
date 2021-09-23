import { NgModule } from "@angular/core";
import { SharedModule } from "app/shared/shared.module";
import { AnnexureQuestionsComponent } from "./list/annexure-questions.component";
import { AnnexureQuestionsDetailComponent } from "./detail/annexure-questions-detail.component";
import { AnnexureQuestionsUpdateComponent } from "./update/annexure-questions-update.component";
import { AnnexureQuestionsDeleteDialogComponent } from "./delete/annexure-questions-delete-dialog.component";
import { AnnexureQuestionsRoutingModule } from "./route/annexure-questions-routing.module";

@NgModule({
  imports: [SharedModule, AnnexureQuestionsRoutingModule],
  declarations: [
    AnnexureQuestionsComponent,
    AnnexureQuestionsDetailComponent,
    AnnexureQuestionsUpdateComponent,
    AnnexureQuestionsDeleteDialogComponent,
  ],
  entryComponents: [AnnexureQuestionsDeleteDialogComponent],
})
export class AnnexureQuestionsModule {}
