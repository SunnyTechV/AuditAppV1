import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IOxygenConsumptionData } from "../oxygen-consumption-data.model";

@Component({
  selector: "jhi-oxygen-consumption-data-detail",
  templateUrl: "./oxygen-consumption-data-detail.component.html",
})
export class OxygenConsumptionDataDetailComponent implements OnInit {
  oxygenConsumptionData: IOxygenConsumptionData | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oxygenConsumptionData }) => {
      this.oxygenConsumptionData = oxygenConsumptionData;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
