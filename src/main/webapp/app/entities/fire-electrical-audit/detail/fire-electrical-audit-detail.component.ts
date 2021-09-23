import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IFireElectricalAudit } from "../fire-electrical-audit.model";

@Component({
  selector: "jhi-fire-electrical-audit-detail",
  templateUrl: "./fire-electrical-audit-detail.component.html",
})
export class FireElectricalAuditDetailComponent implements OnInit {
  fireElectricalAudit: IFireElectricalAudit | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fireElectricalAudit }) => {
      this.fireElectricalAudit = fireElectricalAudit;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
