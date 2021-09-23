import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { ITableDetails } from "../table-details.model";

@Component({
  selector: "jhi-table-details-detail",
  templateUrl: "./table-details-detail.component.html",
})
export class TableDetailsDetailComponent implements OnInit {
  tableDetails: ITableDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tableDetails }) => {
      this.tableDetails = tableDetails;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
