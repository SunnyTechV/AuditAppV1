import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import { IInventoryReport, InventoryReport } from "../inventory-report.model";
import { InventoryReportService } from "../service/inventory-report.service";

@Injectable({ providedIn: "root" })
export class InventoryReportRoutingResolveService
  implements Resolve<IInventoryReport>
{
  constructor(
    protected service: InventoryReportService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<IInventoryReport> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((inventoryReport: HttpResponse<InventoryReport>) => {
          if (inventoryReport.body) {
            return of(inventoryReport.body);
          } else {
            this.router.navigate(["404"]);
            return EMPTY;
          }
        })
      );
    }
    return of(new InventoryReport());
  }
}
