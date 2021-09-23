import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import {
  IFireElectricalAudit,
  FireElectricalAudit,
} from "../fire-electrical-audit.model";
import { FireElectricalAuditService } from "../service/fire-electrical-audit.service";

@Injectable({ providedIn: "root" })
export class FireElectricalAuditRoutingResolveService
  implements Resolve<IFireElectricalAudit>
{
  constructor(
    protected service: FireElectricalAuditService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<IFireElectricalAudit> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((fireElectricalAudit: HttpResponse<FireElectricalAudit>) => {
          if (fireElectricalAudit.body) {
            return of(fireElectricalAudit.body);
          } else {
            this.router.navigate(["404"]);
            return EMPTY;
          }
        })
      );
    }
    return of(new FireElectricalAudit());
  }
}
