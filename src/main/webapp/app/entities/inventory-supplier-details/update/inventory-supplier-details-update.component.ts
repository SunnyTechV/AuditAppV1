import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize, map } from "rxjs/operators";

import {
  IInventorySupplierDetails,
  InventorySupplierDetails,
} from "../inventory-supplier-details.model";
import { InventorySupplierDetailsService } from "../service/inventory-supplier-details.service";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";

@Component({
  selector: "jhi-inventory-supplier-details-update",
  templateUrl: "./inventory-supplier-details-update.component.html",
})
export class InventorySupplierDetailsUpdateComponent implements OnInit {
  isSaving = false;

  auditsSharedCollection: IAudit[] = [];

  editForm = this.fb.group({
    id: [],
    formName: [],
    type: [],
    subType: [],
    tableName: [],
    supplierName: [],
    supplierAddress: [],
    supplierContactName: [],
    supplierContactNameNumber: [],
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
    protected inventorySupplierDetailsService: InventorySupplierDetailsService,
    protected auditService: AuditService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inventorySupplierDetails }) => {
      this.updateForm(inventorySupplierDetails);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inventorySupplierDetails = this.createFromForm();
    if (inventorySupplierDetails.id !== undefined) {
      this.subscribeToSaveResponse(
        this.inventorySupplierDetailsService.update(inventorySupplierDetails)
      );
    } else {
      this.subscribeToSaveResponse(
        this.inventorySupplierDetailsService.create(inventorySupplierDetails)
      );
    }
  }

  trackAuditById(index: number, item: IAudit): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IInventorySupplierDetails>>
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

  protected updateForm(
    inventorySupplierDetails: IInventorySupplierDetails
  ): void {
    this.editForm.patchValue({
      id: inventorySupplierDetails.id,
      formName: inventorySupplierDetails.formName,
      type: inventorySupplierDetails.type,
      subType: inventorySupplierDetails.subType,
      tableName: inventorySupplierDetails.tableName,
      supplierName: inventorySupplierDetails.supplierName,
      supplierAddress: inventorySupplierDetails.supplierAddress,
      supplierContactName: inventorySupplierDetails.supplierContactName,
      supplierContactNameNumber:
        inventorySupplierDetails.supplierContactNameNumber,
      freeField1: inventorySupplierDetails.freeField1,
      freeField2: inventorySupplierDetails.freeField2,
      freeField3: inventorySupplierDetails.freeField3,
      createdDate: inventorySupplierDetails.createdDate,
      createdBy: inventorySupplierDetails.createdBy,
      lastModified: inventorySupplierDetails.lastModified,
      lastModifiedBy: inventorySupplierDetails.lastModifiedBy,
      audit: inventorySupplierDetails.audit,
    });

    this.auditsSharedCollection =
      this.auditService.addAuditToCollectionIfMissing(
        this.auditsSharedCollection,
        inventorySupplierDetails.audit
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

  protected createFromForm(): IInventorySupplierDetails {
    return {
      ...new InventorySupplierDetails(),
      id: this.editForm.get(["id"])!.value,
      formName: this.editForm.get(["formName"])!.value,
      type: this.editForm.get(["type"])!.value,
      subType: this.editForm.get(["subType"])!.value,
      tableName: this.editForm.get(["tableName"])!.value,
      supplierName: this.editForm.get(["supplierName"])!.value,
      supplierAddress: this.editForm.get(["supplierAddress"])!.value,
      supplierContactName: this.editForm.get(["supplierContactName"])!.value,
      supplierContactNameNumber: this.editForm.get([
        "supplierContactNameNumber",
      ])!.value,
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
