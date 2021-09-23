import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize, map } from "rxjs/operators";

import {
  IFireElectricalAudit,
  FireElectricalAudit,
} from "../fire-electrical-audit.model";
import { FireElectricalAuditService } from "../service/fire-electrical-audit.service";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";

@Component({
  selector: "jhi-fire-electrical-audit-update",
  templateUrl: "./fire-electrical-audit-update.component.html",
})
export class FireElectricalAuditUpdateComponent implements OnInit {
  isSaving = false;

  auditsSharedCollection: IAudit[] = [];

  editForm = this.fb.group({
    id: [],
    fireAuditDoneOrnot: [],
    fireAuditDate: [],
    fireFaults: [],
    fireCorrectiveAction: [],
    fireAuditPlan: [],
    electricalAuditDone: [],
    electricalAuditDate: [],
    electricalFaults: [],
    electricalCorrectiveAction: [],
    electricalAuditInspection: [],
    technicalPersonAppoint: [],
    techPersonname: [],
    techPersonMobNo: [],
    technicalEngineerName: [],
    technicalEngineerAddress: [],
    technicalEngineerMob: [],
    technicalEngineerAlternateMob: [],
    o2HospRequirement: [],
    o2HospProjectedRequirement: [],
    saveO2RequirementPossibleMT: [],
    monitoringO2ValvesPort: [],
    portValvesShutDown: [],
    idPatientDrillDone: [],
    staffCheckingLeakage: [],
    patientO2ReqFinalized: [],
    timeByDoctor: [],
    isLightingInstalled: [],
    locLightningArrerstor: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModified: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    audit: [],
  });

  constructor(
    protected fireElectricalAuditService: FireElectricalAuditService,
    protected auditService: AuditService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fireElectricalAudit }) => {
      this.updateForm(fireElectricalAudit);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fireElectricalAudit = this.createFromForm();
    if (fireElectricalAudit.id !== undefined) {
      this.subscribeToSaveResponse(
        this.fireElectricalAuditService.update(fireElectricalAudit)
      );
    } else {
      this.subscribeToSaveResponse(
        this.fireElectricalAuditService.create(fireElectricalAudit)
      );
    }
  }

  trackAuditById(index: number, item: IAudit): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IFireElectricalAudit>>
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

  protected updateForm(fireElectricalAudit: IFireElectricalAudit): void {
    this.editForm.patchValue({
      id: fireElectricalAudit.id,
      fireAuditDoneOrnot: fireElectricalAudit.fireAuditDoneOrnot,
      fireAuditDate: fireElectricalAudit.fireAuditDate,
      fireFaults: fireElectricalAudit.fireFaults,
      fireCorrectiveAction: fireElectricalAudit.fireCorrectiveAction,
      fireAuditPlan: fireElectricalAudit.fireAuditPlan,
      electricalAuditDone: fireElectricalAudit.electricalAuditDone,
      electricalAuditDate: fireElectricalAudit.electricalAuditDate,
      electricalFaults: fireElectricalAudit.electricalFaults,
      electricalCorrectiveAction:
        fireElectricalAudit.electricalCorrectiveAction,
      electricalAuditInspection: fireElectricalAudit.electricalAuditInspection,
      technicalPersonAppoint: fireElectricalAudit.technicalPersonAppoint,
      techPersonname: fireElectricalAudit.techPersonname,
      techPersonMobNo: fireElectricalAudit.techPersonMobNo,
      technicalEngineerName: fireElectricalAudit.technicalEngineerName,
      technicalEngineerAddress: fireElectricalAudit.technicalEngineerAddress,
      technicalEngineerMob: fireElectricalAudit.technicalEngineerMob,
      technicalEngineerAlternateMob:
        fireElectricalAudit.technicalEngineerAlternateMob,
      o2HospRequirement: fireElectricalAudit.o2HospRequirement,
      o2HospProjectedRequirement:
        fireElectricalAudit.o2HospProjectedRequirement,
      saveO2RequirementPossibleMT:
        fireElectricalAudit.saveO2RequirementPossibleMT,
      monitoringO2ValvesPort: fireElectricalAudit.monitoringO2ValvesPort,
      portValvesShutDown: fireElectricalAudit.portValvesShutDown,
      idPatientDrillDone: fireElectricalAudit.idPatientDrillDone,
      staffCheckingLeakage: fireElectricalAudit.staffCheckingLeakage,
      patientO2ReqFinalized: fireElectricalAudit.patientO2ReqFinalized,
      timeByDoctor: fireElectricalAudit.timeByDoctor,
      isLightingInstalled: fireElectricalAudit.isLightingInstalled,
      locLightningArrerstor: fireElectricalAudit.locLightningArrerstor,
      createdBy: fireElectricalAudit.createdBy,
      createdDate: fireElectricalAudit.createdDate,
      lastModifiedBy: fireElectricalAudit.lastModifiedBy,
      lastModified: fireElectricalAudit.lastModified,
      freeField1: fireElectricalAudit.freeField1,
      freeField2: fireElectricalAudit.freeField2,
      freeField3: fireElectricalAudit.freeField3,
      freeField4: fireElectricalAudit.freeField4,
      audit: fireElectricalAudit.audit,
    });

    this.auditsSharedCollection =
      this.auditService.addAuditToCollectionIfMissing(
        this.auditsSharedCollection,
        fireElectricalAudit.audit
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

  protected createFromForm(): IFireElectricalAudit {
    return {
      ...new FireElectricalAudit(),
      id: this.editForm.get(["id"])!.value,
      fireAuditDoneOrnot: this.editForm.get(["fireAuditDoneOrnot"])!.value,
      fireAuditDate: this.editForm.get(["fireAuditDate"])!.value,
      fireFaults: this.editForm.get(["fireFaults"])!.value,
      fireCorrectiveAction: this.editForm.get(["fireCorrectiveAction"])!.value,
      fireAuditPlan: this.editForm.get(["fireAuditPlan"])!.value,
      electricalAuditDone: this.editForm.get(["electricalAuditDone"])!.value,
      electricalAuditDate: this.editForm.get(["electricalAuditDate"])!.value,
      electricalFaults: this.editForm.get(["electricalFaults"])!.value,
      electricalCorrectiveAction: this.editForm.get([
        "electricalCorrectiveAction",
      ])!.value,
      electricalAuditInspection: this.editForm.get([
        "electricalAuditInspection",
      ])!.value,
      technicalPersonAppoint: this.editForm.get(["technicalPersonAppoint"])!
        .value,
      techPersonname: this.editForm.get(["techPersonname"])!.value,
      techPersonMobNo: this.editForm.get(["techPersonMobNo"])!.value,
      technicalEngineerName: this.editForm.get(["technicalEngineerName"])!
        .value,
      technicalEngineerAddress: this.editForm.get(["technicalEngineerAddress"])!
        .value,
      technicalEngineerMob: this.editForm.get(["technicalEngineerMob"])!.value,
      technicalEngineerAlternateMob: this.editForm.get([
        "technicalEngineerAlternateMob",
      ])!.value,
      o2HospRequirement: this.editForm.get(["o2HospRequirement"])!.value,
      o2HospProjectedRequirement: this.editForm.get([
        "o2HospProjectedRequirement",
      ])!.value,
      saveO2RequirementPossibleMT: this.editForm.get([
        "saveO2RequirementPossibleMT",
      ])!.value,
      monitoringO2ValvesPort: this.editForm.get(["monitoringO2ValvesPort"])!
        .value,
      portValvesShutDown: this.editForm.get(["portValvesShutDown"])!.value,
      idPatientDrillDone: this.editForm.get(["idPatientDrillDone"])!.value,
      staffCheckingLeakage: this.editForm.get(["staffCheckingLeakage"])!.value,
      patientO2ReqFinalized: this.editForm.get(["patientO2ReqFinalized"])!
        .value,
      timeByDoctor: this.editForm.get(["timeByDoctor"])!.value,
      isLightingInstalled: this.editForm.get(["isLightingInstalled"])!.value,
      locLightningArrerstor: this.editForm.get(["locLightningArrerstor"])!
        .value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      freeField4: this.editForm.get(["freeField4"])!.value,
      audit: this.editForm.get(["audit"])!.value,
    };
  }
}
