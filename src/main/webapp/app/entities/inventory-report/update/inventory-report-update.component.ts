import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize, map } from "rxjs/operators";

import { IInventoryReport, InventoryReport } from "../inventory-report.model";
import { InventoryReportService } from "../service/inventory-report.service";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";

@Component({
  selector: "jhi-inventory-report-update",
  templateUrl: "./inventory-report-update.component.html",
})
export class InventoryReportUpdateComponent implements OnInit {
  isSaving = false;

  auditsSharedCollection: IAudit[] = [];

  editForm = this.fb.group({
    id: [],
    formName: [],
    type: [],
    subType: [],
    tableName: [],
    description: [],
    descriptionParameter: [],
    actualAvailable: [],
    remark: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    createdDate: [],
    createdBy: [],
    lastModified: [],
    lastModifiedBy: [],
    audit: [],
  });

  constructor(
    protected inventoryReportService: InventoryReportService,
    protected auditService: AuditService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inventoryReport }) => {
      this.updateForm(inventoryReport);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inventoryReport = this.createFromForm();
    if (inventoryReport.id !== undefined) {
      this.subscribeToSaveResponse(
        this.inventoryReportService.update(inventoryReport)
      );
    } else {
      this.subscribeToSaveResponse(
        this.inventoryReportService.create(inventoryReport)
      );
    }
  }

  trackAuditById(index: number, item: IAudit): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IInventoryReport>>
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

  protected updateForm(inventoryReport: IInventoryReport): void {
    this.editForm.patchValue({
      id: inventoryReport.id,
      formName: inventoryReport.formName,
      type: inventoryReport.type,
      subType: inventoryReport.subType,
      tableName: inventoryReport.tableName,
      description: inventoryReport.description,
      descriptionParameter: inventoryReport.descriptionParameter,
      actualAvailable: inventoryReport.actualAvailable,
      remark: inventoryReport.remark,
      freeField1: inventoryReport.freeField1,
      freeField2: inventoryReport.freeField2,
      freeField3: inventoryReport.freeField3,
      createdDate: inventoryReport.createdDate,
      createdBy: inventoryReport.createdBy,
      lastModified: inventoryReport.lastModified,
      lastModifiedBy: inventoryReport.lastModifiedBy,
      audit: inventoryReport.audit,
    });

    this.auditsSharedCollection =
      this.auditService.addAuditToCollectionIfMissing(
        this.auditsSharedCollection,
        inventoryReport.audit
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
  }

  protected createFromForm(): IInventoryReport {
    return {
      ...new InventoryReport(),
      id: this.editForm.get(["id"])!.value,
      formName: this.editForm.get(["formName"])!.value,
      type: this.editForm.get(["type"])!.value,
      subType: this.editForm.get(["subType"])!.value,
      tableName: this.editForm.get(["tableName"])!.value,
      description: this.editForm.get(["description"])!.value,
      descriptionParameter: this.editForm.get(["descriptionParameter"])!.value,
      actualAvailable: this.editForm.get(["actualAvailable"])!.value,
      remark: this.editForm.get(["remark"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
      audit: this.editForm.get(["audit"])!.value,
    };
  }
}
