import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize, map } from "rxjs/operators";

import {
  IAuditFormSHospGenInfo,
  AuditFormSHospGenInfo,
} from "../audit-form-s-hosp-gen-info.model";
import { AuditFormSHospGenInfoService } from "../service/audit-form-s-hosp-gen-info.service";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";

@Component({
  selector: "jhi-audit-form-s-hosp-gen-info-update",
  templateUrl: "./audit-form-s-hosp-gen-info-update.component.html",
})
export class AuditFormSHospGenInfoUpdateComponent implements OnInit {
  isSaving = false;

  auditsSharedCollection: IAudit[] = [];

  editForm = this.fb.group({
    id: [],
    hospName: [],
    hospType: [],
    type: [],
    subType: [],
    formName: [],
    inchargeName: [],
    hospAddress: [],
    hospPhoneNo: [],
    normalBeds: [],
    oxygenBeds: [],
    ventilatorBeds: [],
    icuBeds: [],
    onCylinderPatient: [],
    onPipedBedsPatient: [],
    onNIV: [],
    onIntubated: [],
    jumboSystemInstalledType: [],
    availableCylinderTypeD7: [],
    availableCylinderTypeB: [],
    cylinderAgencyName: [],
    cylinderAgencyAddress: [],
    availableLMOCapacityKL: [],
    lmoSupplierName: [],
    lmoSupplierFrequency: [],
    lastLmoSuppliedQuantity: [],
    remark: [],
    createdDate: [],
    createdBy: [],
    lastModified: [],
    lastModifiedBy: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    audit: [],
  });

  constructor(
    protected auditFormSHospGenInfoService: AuditFormSHospGenInfoService,
    protected auditService: AuditService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ auditFormSHospGenInfo }) => {
      this.updateForm(auditFormSHospGenInfo);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const auditFormSHospGenInfo = this.createFromForm();
    if (auditFormSHospGenInfo.id !== undefined) {
      this.subscribeToSaveResponse(
        this.auditFormSHospGenInfoService.update(auditFormSHospGenInfo)
      );
    } else {
      this.subscribeToSaveResponse(
        this.auditFormSHospGenInfoService.create(auditFormSHospGenInfo)
      );
    }
  }

  trackAuditById(index: number, item: IAudit): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IAuditFormSHospGenInfo>>
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

  protected updateForm(auditFormSHospGenInfo: IAuditFormSHospGenInfo): void {
    this.editForm.patchValue({
      id: auditFormSHospGenInfo.id,
      hospName: auditFormSHospGenInfo.hospName,
      hospType: auditFormSHospGenInfo.hospType,
      type: auditFormSHospGenInfo.type,
      subType: auditFormSHospGenInfo.subType,
      formName: auditFormSHospGenInfo.formName,
      inchargeName: auditFormSHospGenInfo.inchargeName,
      hospAddress: auditFormSHospGenInfo.hospAddress,
      hospPhoneNo: auditFormSHospGenInfo.hospPhoneNo,
      normalBeds: auditFormSHospGenInfo.normalBeds,
      oxygenBeds: auditFormSHospGenInfo.oxygenBeds,
      ventilatorBeds: auditFormSHospGenInfo.ventilatorBeds,
      icuBeds: auditFormSHospGenInfo.icuBeds,
      onCylinderPatient: auditFormSHospGenInfo.onCylinderPatient,
      onPipedBedsPatient: auditFormSHospGenInfo.onPipedBedsPatient,
      onNIV: auditFormSHospGenInfo.onNIV,
      onIntubated: auditFormSHospGenInfo.onIntubated,
      jumboSystemInstalledType: auditFormSHospGenInfo.jumboSystemInstalledType,
      availableCylinderTypeD7: auditFormSHospGenInfo.availableCylinderTypeD7,
      availableCylinderTypeB: auditFormSHospGenInfo.availableCylinderTypeB,
      cylinderAgencyName: auditFormSHospGenInfo.cylinderAgencyName,
      cylinderAgencyAddress: auditFormSHospGenInfo.cylinderAgencyAddress,
      availableLMOCapacityKL: auditFormSHospGenInfo.availableLMOCapacityKL,
      lmoSupplierName: auditFormSHospGenInfo.lmoSupplierName,
      lmoSupplierFrequency: auditFormSHospGenInfo.lmoSupplierFrequency,
      lastLmoSuppliedQuantity: auditFormSHospGenInfo.lastLmoSuppliedQuantity,
      remark: auditFormSHospGenInfo.remark,
      createdDate: auditFormSHospGenInfo.createdDate,
      createdBy: auditFormSHospGenInfo.createdBy,
      lastModified: auditFormSHospGenInfo.lastModified,
      lastModifiedBy: auditFormSHospGenInfo.lastModifiedBy,
      freeField1: auditFormSHospGenInfo.freeField1,
      freeField2: auditFormSHospGenInfo.freeField2,
      freeField3: auditFormSHospGenInfo.freeField3,
      freeField4: auditFormSHospGenInfo.freeField4,
      audit: auditFormSHospGenInfo.audit,
    });

    this.auditsSharedCollection =
      this.auditService.addAuditToCollectionIfMissing(
        this.auditsSharedCollection,
        auditFormSHospGenInfo.audit
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

  protected createFromForm(): IAuditFormSHospGenInfo {
    return {
      ...new AuditFormSHospGenInfo(),
      id: this.editForm.get(["id"])!.value,
      hospName: this.editForm.get(["hospName"])!.value,
      hospType: this.editForm.get(["hospType"])!.value,
      type: this.editForm.get(["type"])!.value,
      subType: this.editForm.get(["subType"])!.value,
      formName: this.editForm.get(["formName"])!.value,
      inchargeName: this.editForm.get(["inchargeName"])!.value,
      hospAddress: this.editForm.get(["hospAddress"])!.value,
      hospPhoneNo: this.editForm.get(["hospPhoneNo"])!.value,
      normalBeds: this.editForm.get(["normalBeds"])!.value,
      oxygenBeds: this.editForm.get(["oxygenBeds"])!.value,
      ventilatorBeds: this.editForm.get(["ventilatorBeds"])!.value,
      icuBeds: this.editForm.get(["icuBeds"])!.value,
      onCylinderPatient: this.editForm.get(["onCylinderPatient"])!.value,
      onPipedBedsPatient: this.editForm.get(["onPipedBedsPatient"])!.value,
      onNIV: this.editForm.get(["onNIV"])!.value,
      onIntubated: this.editForm.get(["onIntubated"])!.value,
      jumboSystemInstalledType: this.editForm.get(["jumboSystemInstalledType"])!
        .value,
      availableCylinderTypeD7: this.editForm.get(["availableCylinderTypeD7"])!
        .value,
      availableCylinderTypeB: this.editForm.get(["availableCylinderTypeB"])!
        .value,
      cylinderAgencyName: this.editForm.get(["cylinderAgencyName"])!.value,
      cylinderAgencyAddress: this.editForm.get(["cylinderAgencyAddress"])!
        .value,
      availableLMOCapacityKL: this.editForm.get(["availableLMOCapacityKL"])!
        .value,
      lmoSupplierName: this.editForm.get(["lmoSupplierName"])!.value,
      lmoSupplierFrequency: this.editForm.get(["lmoSupplierFrequency"])!.value,
      lastLmoSuppliedQuantity: this.editForm.get(["lastLmoSuppliedQuantity"])!
        .value,
      remark: this.editForm.get(["remark"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      freeField4: this.editForm.get(["freeField4"])!.value,
      audit: this.editForm.get(["audit"])!.value,
    };
  }
}
