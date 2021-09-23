import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IAuditPatientMonitoringForm } from "../audit-patient-monitoring-form.model";

@Component({
  selector: "jhi-audit-patient-monitoring-form-detail",
  templateUrl: "./audit-patient-monitoring-form-detail.component.html",
})
export class AuditPatientMonitoringFormDetailComponent implements OnInit {
  auditPatientMonitoringForm: IAuditPatientMonitoringForm | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ auditPatientMonitoringForm }) => {
      this.auditPatientMonitoringForm = auditPatientMonitoringForm;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
