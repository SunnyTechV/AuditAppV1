import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import {
  IOxygenConsumptionData,
  OxygenConsumptionData,
} from "../oxygen-consumption-data.model";
import { OxygenConsumptionDataService } from "../service/oxygen-consumption-data.service";

@Injectable({ providedIn: "root" })
export class OxygenConsumptionDataRoutingResolveService
  implements Resolve<IOxygenConsumptionData>
{
  constructor(
    protected service: OxygenConsumptionDataService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<IOxygenConsumptionData> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap(
          (oxygenConsumptionData: HttpResponse<OxygenConsumptionData>) => {
            if (oxygenConsumptionData.body) {
              return of(oxygenConsumptionData.body);
            } else {
              this.router.navigate(["404"]);
              return EMPTY;
            }
          }
        )
      );
    }
    return of(new OxygenConsumptionData());
  }
}
