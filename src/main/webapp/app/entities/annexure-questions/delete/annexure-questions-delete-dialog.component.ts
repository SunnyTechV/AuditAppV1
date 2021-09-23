import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { IAnnexureQuestions } from "../annexure-questions.model";
import { AnnexureQuestionsService } from "../service/annexure-questions.service";

@Component({
  templateUrl: "./annexure-questions-delete-dialog.component.html",
})
export class AnnexureQuestionsDeleteDialogComponent {
  annexureQuestions?: IAnnexureQuestions;

  constructor(
    protected annexureQuestionsService: AnnexureQuestionsService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.annexureQuestionsService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
