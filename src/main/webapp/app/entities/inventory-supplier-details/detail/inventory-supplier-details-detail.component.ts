import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IInventorySupplierDetails } from "../inventory-supplier-details.model";

@Component({
  selector: "jhi-inventory-supplier-details-detail",
  templateUrl: "./inventory-supplier-details-detail.component.html",
})
export class InventorySupplierDetailsDetailComponent implements OnInit {
  inventorySupplierDetails: IInventorySupplierDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inventorySupplierDetails }) => {
      this.inventorySupplierDetails = inventorySupplierDetails;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
