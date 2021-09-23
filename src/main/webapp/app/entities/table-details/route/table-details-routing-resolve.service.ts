import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import { ITableDetails, TableDetails } from "../table-details.model";
import { TableDetailsService } from "../service/table-details.service";

@Injectable({ providedIn: "root" })
export class TableDetailsRoutingResolveService
  implements Resolve<ITableDetails>
{
  constructor(
    protected service: TableDetailsService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<ITableDetails> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tableDetails: HttpResponse<TableDetails>) => {
          if (tableDetails.body) {
            return of(tableDetails.body);
          } else {
            this.router.navigate(["404"]);
            return EMPTY;
          }
        })
      );
    }
    return of(new TableDetails());
  }
}
