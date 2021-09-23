import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import {
  IInventorySupplierDetails,
  InventorySupplierDetails,
} from "../inventory-supplier-details.model";
import { InventorySupplierDetailsService } from "../service/inventory-supplier-details.service";

@Injectable({ providedIn: "root" })
export class InventorySupplierDetailsRoutingResolveService
  implements Resolve<IInventorySupplierDetails>
{
  constructor(
    protected service: InventorySupplierDetailsService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<IInventorySupplierDetails> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap(
          (
            inventorySupplierDetails: HttpResponse<InventorySupplierDetails>
          ) => {
            if (inventorySupplierDetails.body) {
              return of(inventorySupplierDetails.body);
            } else {
              this.router.navigate(["404"]);
              return EMPTY;
            }
          }
        )
      );
    }
    return of(new InventorySupplierDetails());
  }
}
