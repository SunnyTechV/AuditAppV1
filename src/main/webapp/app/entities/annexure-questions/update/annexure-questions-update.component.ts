import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize } from "rxjs/operators";

import {
  IAnnexureQuestions,
  AnnexureQuestions,
} from "../annexure-questions.model";
import { AnnexureQuestionsService } from "../service/annexure-questions.service";

@Component({
  selector: "jhi-annexure-questions-update",
  templateUrl: "./annexure-questions-update.component.html",
})
export class AnnexureQuestionsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    formName: [],
    type: [],
    subType: [],
    description: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    createdDate: [],
    createdBy: [],
    lastModified: [],
    lastModifiedBy: [],
  });

  constructor(
    protected annexureQuestionsService: AnnexureQuestionsService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ annexureQuestions }) => {
      this.updateForm(annexureQuestions);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const annexureQuestions = this.createFromForm();
    if (annexureQuestions.id !== undefined) {
      this.subscribeToSaveResponse(
        this.annexureQuestionsService.update(annexureQuestions)
      );
    } else {
      this.subscribeToSaveResponse(
        this.annexureQuestionsService.create(annexureQuestions)
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IAnnexureQuestions>>
  ): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(annexureQuestions: IAnnexureQuestions): void {
    this.editForm.patchValue({
      id: annexureQuestions.id,
      formName: annexureQuestions.formName,
      type: annexureQuestions.type,
      subType: annexureQuestions.subType,
      description: annexureQuestions.description,
      freeField1: annexureQuestions.freeField1,
      freeField2: annexureQuestions.freeField2,
      freeField3: annexureQuestions.freeField3,
      freeField4: annexureQuestions.freeField4,
      createdDate: annexureQuestions.createdDate,
      createdBy: annexureQuestions.createdBy,
      lastModified: annexureQuestions.lastModified,
      lastModifiedBy: annexureQuestions.lastModifiedBy,
    });
  }

  protected createFromForm(): IAnnexureQuestions {
    return {
      ...new AnnexureQuestions(),
      id: this.editForm.get(["id"])!.value,
      formName: this.editForm.get(["formName"])!.value,
      type: this.editForm.get(["type"])!.value,
      subType: this.editForm.get(["subType"])!.value,
      description: this.editForm.get(["description"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      freeField4: this.editForm.get(["freeField4"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
    };
  }
}
