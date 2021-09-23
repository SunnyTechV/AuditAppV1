import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import {
  IAuditFormSHospGenInfo,
  AuditFormSHospGenInfo,
} from "../audit-form-s-hosp-gen-info.model";
import { AuditFormSHospGenInfoService } from "../service/audit-form-s-hosp-gen-info.service";

@Injectable({ providedIn: "root" })
export class AuditFormSHospGenInfoRoutingResolveService
  implements Resolve<IAuditFormSHospGenInfo>
{
  constructor(
    protected service: AuditFormSHospGenInfoService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<IAuditFormSHospGenInfo> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap(
          (auditFormSHospGenInfo: HttpResponse<AuditFormSHospGenInfo>) => {
            if (auditFormSHospGenInfo.body) {
              return of(auditFormSHospGenInfo.body);
            } else {
              this.router.navigate(["404"]);
              return EMPTY;
            }
          }
        )
      );
    }
    return of(new AuditFormSHospGenInfo());
  }
}
