import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize } from "rxjs/operators";

import {
  IAuditPatientMonitoringForm,
  AuditPatientMonitoringForm,
} from "../audit-patient-monitoring-form.model";
import { AuditPatientMonitoringFormService } from "../service/audit-patient-monitoring-form.service";

@Component({
  selector: "jhi-audit-patient-monitoring-form-update",
  templateUrl: "./audit-patient-monitoring-form-update.component.html",
})
export class AuditPatientMonitoringFormUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    wardNo: [],
    patientName: [],
    bedNo: [],
    dateOfAdmission: [],
    oxygenType: [],
    sixToEightAM: [],
    oxySixToEightAM: [],
    eightToTenAM: [],
    oxyEightToTenAM: [],
    tenToTwelveAM: [],
    oxyTenToTwelveAM: [],
    twelveToTowPM: [],
    oxyTwelveToTowPM: [],
    twoToFourPM: [],
    oxyTwoToFourPM: [],
    fourToSixPM: [],
    oxyFourToSixPM: [],
    sixToEightPM: [],
    oxySixToEightPM: [],
    eightToTenPM: [],
    oxyEightToTenPM: [],
    tenToTwelvePM: [],
    oxyTenToTwelvePM: [],
    twelveToTwoAM: [],
    oxyTwelveToTwoAM: [],
    twoToFourAM: [],
    oxyTwoToFourAM: [],
    fourToSixAM: [],
    oxyFourToSixAM: [],
    drName: [],
    nurseName: [],
    createdBy: [],
    createdDate: [],
    lastModifiedBy: [],
    lastModified: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
  });

  constructor(
    protected auditPatientMonitoringFormService: AuditPatientMonitoringFormService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ auditPatientMonitoringForm }) => {
      this.updateForm(auditPatientMonitoringForm);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const auditPatientMonitoringForm = this.createFromForm();
    if (auditPatientMonitoringForm.id !== undefined) {
      this.subscribeToSaveResponse(
        this.auditPatientMonitoringFormService.update(
          auditPatientMonitoringForm
        )
      );
    } else {
      this.subscribeToSaveResponse(
        this.auditPatientMonitoringFormService.create(
          auditPatientMonitoringForm
        )
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<IAuditPatientMonitoringForm>>
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
    auditPatientMonitoringForm: IAuditPatientMonitoringForm
  ): void {
    this.editForm.patchValue({
      id: auditPatientMonitoringForm.id,
      wardNo: auditPatientMonitoringForm.wardNo,
      patientName: auditPatientMonitoringForm.patientName,
      bedNo: auditPatientMonitoringForm.bedNo,
      dateOfAdmission: auditPatientMonitoringForm.dateOfAdmission,
      oxygenType: auditPatientMonitoringForm.oxygenType,
      sixToEightAM: auditPatientMonitoringForm.sixToEightAM,
      oxySixToEightAM: auditPatientMonitoringForm.oxySixToEightAM,
      eightToTenAM: auditPatientMonitoringForm.eightToTenAM,
      oxyEightToTenAM: auditPatientMonitoringForm.oxyEightToTenAM,
      tenToTwelveAM: auditPatientMonitoringForm.tenToTwelveAM,
      oxyTenToTwelveAM: auditPatientMonitoringForm.oxyTenToTwelveAM,
      twelveToTowPM: auditPatientMonitoringForm.twelveToTowPM,
      oxyTwelveToTowPM: auditPatientMonitoringForm.oxyTwelveToTowPM,
      twoToFourPM: auditPatientMonitoringForm.twoToFourPM,
      oxyTwoToFourPM: auditPatientMonitoringForm.oxyTwoToFourPM,
      fourToSixPM: auditPatientMonitoringForm.fourToSixPM,
      oxyFourToSixPM: auditPatientMonitoringForm.oxyFourToSixPM,
      sixToEightPM: auditPatientMonitoringForm.sixToEightPM,
      oxySixToEightPM: auditPatientMonitoringForm.oxySixToEightPM,
      eightToTenPM: auditPatientMonitoringForm.eightToTenPM,
      oxyEightToTenPM: auditPatientMonitoringForm.oxyEightToTenPM,
      tenToTwelvePM: auditPatientMonitoringForm.tenToTwelvePM,
      oxyTenToTwelvePM: auditPatientMonitoringForm.oxyTenToTwelvePM,
      twelveToTwoAM: auditPatientMonitoringForm.twelveToTwoAM,
      oxyTwelveToTwoAM: auditPatientMonitoringForm.oxyTwelveToTwoAM,
      twoToFourAM: auditPatientMonitoringForm.twoToFourAM,
      oxyTwoToFourAM: auditPatientMonitoringForm.oxyTwoToFourAM,
      fourToSixAM: auditPatientMonitoringForm.fourToSixAM,
      oxyFourToSixAM: auditPatientMonitoringForm.oxyFourToSixAM,
      drName: auditPatientMonitoringForm.drName,
      nurseName: auditPatientMonitoringForm.nurseName,
      createdBy: auditPatientMonitoringForm.createdBy,
      createdDate: auditPatientMonitoringForm.createdDate,
      lastModifiedBy: auditPatientMonitoringForm.lastModifiedBy,
      lastModified: auditPatientMonitoringForm.lastModified,
      freeField1: auditPatientMonitoringForm.freeField1,
      freeField2: auditPatientMonitoringForm.freeField2,
      freeField3: auditPatientMonitoringForm.freeField3,
      freeField4: auditPatientMonitoringForm.freeField4,
    });
  }

  protected createFromForm(): IAuditPatientMonitoringForm {
    return {
      ...new AuditPatientMonitoringForm(),
      id: this.editForm.get(["id"])!.value,
      wardNo: this.editForm.get(["wardNo"])!.value,
      patientName: this.editForm.get(["patientName"])!.value,
      bedNo: this.editForm.get(["bedNo"])!.value,
      dateOfAdmission: this.editForm.get(["dateOfAdmission"])!.value,
      oxygenType: this.editForm.get(["oxygenType"])!.value,
      sixToEightAM: this.editForm.get(["sixToEightAM"])!.value,
      oxySixToEightAM: this.editForm.get(["oxySixToEightAM"])!.value,
      eightToTenAM: this.editForm.get(["eightToTenAM"])!.value,
      oxyEightToTenAM: this.editForm.get(["oxyEightToTenAM"])!.value,
      tenToTwelveAM: this.editForm.get(["tenToTwelveAM"])!.value,
      oxyTenToTwelveAM: this.editForm.get(["oxyTenToTwelveAM"])!.value,
      twelveToTowPM: this.editForm.get(["twelveToTowPM"])!.value,
      oxyTwelveToTowPM: this.editForm.get(["oxyTwelveToTowPM"])!.value,
      twoToFourPM: this.editForm.get(["twoToFourPM"])!.value,
      oxyTwoToFourPM: this.editForm.get(["oxyTwoToFourPM"])!.value,
      fourToSixPM: this.editForm.get(["fourToSixPM"])!.value,
      oxyFourToSixPM: this.editForm.get(["oxyFourToSixPM"])!.value,
      sixToEightPM: this.editForm.get(["sixToEightPM"])!.value,
      oxySixToEightPM: this.editForm.get(["oxySixToEightPM"])!.value,
      eightToTenPM: this.editForm.get(["eightToTenPM"])!.value,
      oxyEightToTenPM: this.editForm.get(["oxyEightToTenPM"])!.value,
      tenToTwelvePM: this.editForm.get(["tenToTwelvePM"])!.value,
      oxyTenToTwelvePM: this.editForm.get(["oxyTenToTwelvePM"])!.value,
      twelveToTwoAM: this.editForm.get(["twelveToTwoAM"])!.value,
      oxyTwelveToTwoAM: this.editForm.get(["oxyTwelveToTwoAM"])!.value,
      twoToFourAM: this.editForm.get(["twoToFourAM"])!.value,
      oxyTwoToFourAM: this.editForm.get(["oxyTwoToFourAM"])!.value,
      fourToSixAM: this.editForm.get(["fourToSixAM"])!.value,
      oxyFourToSixAM: this.editForm.get(["oxyFourToSixAM"])!.value,
      drName: this.editForm.get(["drName"])!.value,
      nurseName: this.editForm.get(["nurseName"])!.value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      freeField4: this.editForm.get(["freeField4"])!.value,
    };
  }
}
