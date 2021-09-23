import { Component } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { IAnnexureAnswers } from "../annexure-answers.model";
import { AnnexureAnswersService } from "../service/annexure-answers.service";

@Component({
  templateUrl: "./annexure-answers-delete-dialog.component.html",
})
export class AnnexureAnswersDeleteDialogComponent {
  annexureAnswers?: IAnnexureAnswers;

  constructor(
    protected annexureAnswersService: AnnexureAnswersService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.annexureAnswersService.delete(id).subscribe(() => {
      this.activeModal.close("deleted");
    });
  }
}
