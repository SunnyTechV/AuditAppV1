import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize, map } from "rxjs/operators";

import { IAudit, Audit } from "../audit.model";
import { AuditService } from "../service/audit.service";
import { IAuditPatientMonitoringForm } from "app/entities/audit-patient-monitoring-form/audit-patient-monitoring-form.model";
import { AuditPatientMonitoringFormService } from "app/entities/audit-patient-monitoring-form/service/audit-patient-monitoring-form.service";

@Component({
  selector: "jhi-audit-update",
  templateUrl: "./audit-update.component.html",
})
export class AuditUpdateComponent implements OnInit {
  isSaving = false;

  auditPatientMonitoringFormsCollection: IAuditPatientMonitoringForm[] = [];

  editForm = this.fb.group({
    id: [],
    auditDate: [],
    hospName: [],
    isAuditComplete: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    remark: [],
    createdBy: [],
    createdDate: [],
    lastModified: [],
    lastModifiedBy: [],
    auditPatientMonitoringForm: [],
  });

  constructor(
    protected auditService: AuditService,
    protected auditPatientMonitoringFormService: AuditPatientMonitoringFormService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ audit }) => {
      this.updateForm(audit);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const audit = this.createFromForm();
    if (audit.id !== undefined) {
      this.subscribeToSaveResponse(this.auditService.update(audit));
    } else {
      this.subscribeToSaveResponse(this.auditService.create(audit));
    }
  }

  trackAuditPatientMonitoringFormById(
    index: number,
    item: IAuditPatientMonitoringForm
  ): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IAudit>>
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

  protected updateForm(audit: IAudit): void {
    this.editForm.patchValue({
      id: audit.id,
      auditDate: audit.auditDate,
      hospName: audit.hospName,
      isAuditComplete: audit.isAuditComplete,
      freeField1: audit.freeField1,
      freeField2: audit.freeField2,
      freeField3: audit.freeField3,
      freeField4: audit.freeField4,
      remark: audit.remark,
      createdBy: audit.createdBy,
      createdDate: audit.createdDate,
      lastModified: audit.lastModified,
      lastModifiedBy: audit.lastModifiedBy,
      auditPatientMonitoringForm: audit.auditPatientMonitoringForm,
    });

    this.auditPatientMonitoringFormsCollection =
      this.auditPatientMonitoringFormService.addAuditPatientMonitoringFormToCollectionIfMissing(
        this.auditPatientMonitoringFormsCollection,
        audit.auditPatientMonitoringForm
      );
  }

  protected loadRelationshipsOptions(): void {
    this.auditPatientMonitoringFormService
      .query({ "auditId.specified": "false" })
      .pipe(
        map(
          (res: HttpResponse<IAuditPatientMonitoringForm[]>) => res.body ?? []
        )
      )
      .pipe(
        map((auditPatientMonitoringForms: IAuditPatientMonitoringForm[]) =>
          this.auditPatientMonitoringFormService.addAuditPatientMonitoringFormToCollectionIfMissing(
            auditPatientMonitoringForms,
            this.editForm.get("auditPatientMonitoringForm")!.value
          )
        )
      )
      .subscribe(
        (auditPatientMonitoringForms: IAuditPatientMonitoringForm[]) =>
          (this.auditPatientMonitoringFormsCollection =
            auditPatientMonitoringForms)
      );
  }

  protected createFromForm(): IAudit {
    return {
      ...new Audit(),
      id: this.editForm.get(["id"])!.value,
      auditDate: this.editForm.get(["auditDate"])!.value,
      hospName: this.editForm.get(["hospName"])!.value,
      isAuditComplete: this.editForm.get(["isAuditComplete"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      freeField4: this.editForm.get(["freeField4"])!.value,
      remark: this.editForm.get(["remark"])!.value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
      auditPatientMonitoringForm: this.editForm.get([
        "auditPatientMonitoringForm",
      ])!.value,
    };
  }
}
