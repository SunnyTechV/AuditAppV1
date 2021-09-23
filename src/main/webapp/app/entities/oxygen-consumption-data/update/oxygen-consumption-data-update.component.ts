import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize, map } from "rxjs/operators";

import {
  IOxygenConsumptionData,
  OxygenConsumptionData,
} from "../oxygen-consumption-data.model";
import { OxygenConsumptionDataService } from "../service/oxygen-consumption-data.service";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";
import { ITableDetails } from "app/entities/table-details/table-details.model";
import { TableDetailsService } from "app/entities/table-details/service/table-details.service";

@Component({
  selector: "jhi-oxygen-consumption-data-update",
  templateUrl: "./oxygen-consumption-data-update.component.html",
})
export class OxygenConsumptionDataUpdateComponent implements OnInit {
  isSaving = false;

  auditsSharedCollection: IAudit[] = [];
  tableDetailsSharedCollection: ITableDetails[] = [];

  editForm = this.fb.group({
    id: [],
    formName: [],
    type: [],
    subType: [],
    tableName: [],
    noofPatients: [],
    consumptionLitperMin: [],
    consumptionLitperDay: [],
    consumptionKLitperDay: [],
    consumptionTotal: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    createdDate: [],
    createdBy: [],
    lastModified: [],
    lastModifiedBy: [],
    audit: [],
    tableDetails: [],
  });

  constructor(
    protected oxygenConsumptionDataService: OxygenConsumptionDataService,
    protected auditService: AuditService,
    protected tableDetailsService: TableDetailsService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oxygenConsumptionData }) => {
      this.updateForm(oxygenConsumptionData);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const oxygenConsumptionData = this.createFromForm();
    if (oxygenConsumptionData.id !== undefined) {
      this.subscribeToSaveResponse(
        this.oxygenConsumptionDataService.update(oxygenConsumptionData)
      );
    } else {
      this.subscribeToSaveResponse(
        this.oxygenConsumptionDataService.create(oxygenConsumptionData)
      );
    }
  }

  trackAuditById(index: number, item: IAudit): number {
    return item.id!;
  }

  trackTableDetailsById(index: number, item: ITableDetails): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IOxygenConsumptionData>>
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

  protected updateForm(oxygenConsumptionData: IOxygenConsumptionData): void {
    this.editForm.patchValue({
      id: oxygenConsumptionData.id,
      formName: oxygenConsumptionData.formName,
      type: oxygenConsumptionData.type,
      subType: oxygenConsumptionData.subType,
      tableName: oxygenConsumptionData.tableName,
      noofPatients: oxygenConsumptionData.noofPatients,
      consumptionLitperMin: oxygenConsumptionData.consumptionLitperMin,
      consumptionLitperDay: oxygenConsumptionData.consumptionLitperDay,
      consumptionKLitperDay: oxygenConsumptionData.consumptionKLitperDay,
      consumptionTotal: oxygenConsumptionData.consumptionTotal,
      freeField1: oxygenConsumptionData.freeField1,
      freeField2: oxygenConsumptionData.freeField2,
      freeField3: oxygenConsumptionData.freeField3,
      createdDate: oxygenConsumptionData.createdDate,
      createdBy: oxygenConsumptionData.createdBy,
      lastModified: oxygenConsumptionData.lastModified,
      lastModifiedBy: oxygenConsumptionData.lastModifiedBy,
      audit: oxygenConsumptionData.audit,
      tableDetails: oxygenConsumptionData.tableDetails,
    });

    this.auditsSharedCollection =
      this.auditService.addAuditToCollectionIfMissing(
        this.auditsSharedCollection,
        oxygenConsumptionData.audit
      );
    this.tableDetailsSharedCollection =
      this.tableDetailsService.addTableDetailsToCollectionIfMissing(
        this.tableDetailsSharedCollection,
        oxygenConsumptionData.tableDetails
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

    this.tableDetailsService
      .query()
      .pipe(map((res: HttpResponse<ITableDetails[]>) => res.body ?? []))
      .pipe(
        map((tableDetails: ITableDetails[]) =>
          this.tableDetailsService.addTableDetailsToCollectionIfMissing(
            tableDetails,
            this.editForm.get("tableDetails")!.value
          )
        )
      )
      .subscribe(
        (tableDetails: ITableDetails[]) =>
          (this.tableDetailsSharedCollection = tableDetails)
      );
  }

  protected createFromForm(): IOxygenConsumptionData {
    return {
      ...new OxygenConsumptionData(),
      id: this.editForm.get(["id"])!.value,
      formName: this.editForm.get(["formName"])!.value,
      type: this.editForm.get(["type"])!.value,
      subType: this.editForm.get(["subType"])!.value,
      tableName: this.editForm.get(["tableName"])!.value,
      noofPatients: this.editForm.get(["noofPatients"])!.value,
      consumptionLitperMin: this.editForm.get(["consumptionLitperMin"])!.value,
      consumptionLitperDay: this.editForm.get(["consumptionLitperDay"])!.value,
      consumptionKLitperDay: this.editForm.get(["consumptionKLitperDay"])!
        .value,
      consumptionTotal: this.editForm.get(["consumptionTotal"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
      audit: this.editForm.get(["audit"])!.value,
      tableDetails: this.editForm.get(["tableDetails"])!.value,
    };
  }
}
