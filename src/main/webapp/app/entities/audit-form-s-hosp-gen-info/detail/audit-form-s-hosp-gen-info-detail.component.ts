import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IAuditFormSHospGenInfo } from "../audit-form-s-hosp-gen-info.model";

@Component({
  selector: "jhi-audit-form-s-hosp-gen-info-detail",
  templateUrl: "./audit-form-s-hosp-gen-info-detail.component.html",
})
export class AuditFormSHospGenInfoDetailComponent implements OnInit {
  auditFormSHospGenInfo: IAuditFormSHospGenInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ auditFormSHospGenInfo }) => {
      this.auditFormSHospGenInfo = auditFormSHospGenInfo;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
