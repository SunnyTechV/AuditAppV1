import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IInventoryReport } from "../inventory-report.model";

@Component({
  selector: "jhi-inventory-report-detail",
  templateUrl: "./inventory-report-detail.component.html",
})
export class InventoryReportDetailComponent implements OnInit {
  inventoryReport: IInventoryReport | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inventoryReport }) => {
      this.inventoryReport = inventoryReport;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
