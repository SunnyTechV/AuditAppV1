import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize, map } from "rxjs/operators";

import { IAnnexureAnswers, AnnexureAnswers } from "../annexure-answers.model";
import { AnnexureAnswersService } from "../service/annexure-answers.service";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";
import { IAnnexureQuestions } from "app/entities/annexure-questions/annexure-questions.model";
import { AnnexureQuestionsService } from "app/entities/annexure-questions/service/annexure-questions.service";

@Component({
  selector: "jhi-annexure-answers-update",
  templateUrl: "./annexure-answers-update.component.html",
})
export class AnnexureAnswersUpdateComponent implements OnInit {
  isSaving = false;

  auditsSharedCollection: IAudit[] = [];
  annexureQuestionsSharedCollection: IAnnexureQuestions[] = [];

  editForm = this.fb.group({
    id: [],
    formName: [],
    type: [],
    subType: [],
    compliance: [],
    comment: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    remark: [],
    createdDate: [],
    createdBy: [],
    lastModified: [],
    lastModifiedBy: [],
    audit: [],
    annexureQuestions: [],
  });

  constructor(
    protected annexureAnswersService: AnnexureAnswersService,
    protected auditService: AuditService,
    protected annexureQuestionsService: AnnexureQuestionsService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ annexureAnswers }) => {
      this.updateForm(annexureAnswers);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const annexureAnswers = this.createFromForm();
    if (annexureAnswers.id !== undefined) {
      this.subscribeToSaveResponse(
        this.annexureAnswersService.update(annexureAnswers)
      );
    } else {
      this.subscribeToSaveResponse(
        this.annexureAnswersService.create(annexureAnswers)
      );
    }
  }

  trackAuditById(index: number, item: IAudit): number {
    return item.id!;
  }

  trackAnnexureQuestionsById(index: number, item: IAnnexureQuestions): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IAnnexureAnswers>>
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

  protected updateForm(annexureAnswers: IAnnexureAnswers): void {
    this.editForm.patchValue({
      id: annexureAnswers.id,
      formName: annexureAnswers.formName,
      type: annexureAnswers.type,
      subType: annexureAnswers.subType,
      compliance: annexureAnswers.compliance,
      comment: annexureAnswers.comment,
      freeField1: annexureAnswers.freeField1,
      freeField2: annexureAnswers.freeField2,
      freeField3: annexureAnswers.freeField3,
      freeField4: annexureAnswers.freeField4,
      remark: annexureAnswers.remark,
      createdDate: annexureAnswers.createdDate,
      createdBy: annexureAnswers.createdBy,
      lastModified: annexureAnswers.lastModified,
      lastModifiedBy: annexureAnswers.lastModifiedBy,
      audit: annexureAnswers.audit,
      annexureQuestions: annexureAnswers.annexureQuestions,
    });

    this.auditsSharedCollection =
      this.auditService.addAuditToCollectionIfMissing(
        this.auditsSharedCollection,
        annexureAnswers.audit
      );
    this.annexureQuestionsSharedCollection =
      this.annexureQuestionsService.addAnnexureQuestionsToCollectionIfMissing(
        this.annexureQuestionsSharedCollection,
        annexureAnswers.annexureQuestions
      );
  }

  protected loadRelationshipsOptions(): void {
    this.auditService
      .query()
      .pipe(map((res: HttpResponse<IAudit[]>) => res.body ?? []))
      .pipe(
        map((audits: IAudit[]) =>
          this.auditService.addAuditToCollectionIfMissing(
            audits,
            this.editForm.get("audit")!.value
          )
        )
      )
      .subscribe((audits: IAudit[]) => (this.auditsSharedCollection = audits));

    this.annexureQuestionsService
      .query()
      .pipe(map((res: HttpResponse<IAnnexureQuestions[]>) => res.body ?? []))
      .pipe(
        map((annexureQuestions: IAnnexureQuestions[]) =>
          this.annexureQuestionsService.addAnnexureQuestionsToCollectionIfMissing(
            annexureQuestions,
            this.editForm.get("annexureQuestions")!.value
          )
        )
      )
      .subscribe(
        (annexureQuestions: IAnnexureQuestions[]) =>
          (this.annexureQuestionsSharedCollection = annexureQuestions)
      );
  }

  protected createFromForm(): IAnnexureAnswers {
    return {
      ...new AnnexureAnswers(),
      id: this.editForm.get(["id"])!.value,
      formName: this.editForm.get(["formName"])!.value,
      type: this.editForm.get(["type"])!.value,
      subType: this.editForm.get(["subType"])!.value,
      compliance: this.editForm.get(["compliance"])!.value,
      comment: this.editForm.get(["comment"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      freeField4: this.editForm.get(["freeField4"])!.value,
      remark: this.editForm.get(["remark"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
      audit: this.editForm.get(["audit"])!.value,
      annexureQuestions: this.editForm.get(["annexureQuestions"])!.value,
    };
  }
}
