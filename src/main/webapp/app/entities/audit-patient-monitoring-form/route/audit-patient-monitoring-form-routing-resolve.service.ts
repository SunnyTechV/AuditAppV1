import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import {
  IAuditPatientMonitoringForm,
  AuditPatientMonitoringForm,
} from "../audit-patient-monitoring-form.model";
import { AuditPatientMonitoringFormService } from "../service/audit-patient-monitoring-form.service";

@Injectable({ providedIn: "root" })
export class AuditPatientMonitoringFormRoutingResolveService
  implements Resolve<IAuditPatientMonitoringForm>
{
  constructor(
    protected service: AuditPatientMonitoringFormService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<IAuditPatientMonitoringForm> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap(
          (
            auditPatientMonitoringForm: HttpResponse<AuditPatientMonitoringForm>
          ) => {
            if (auditPatientMonitoringForm.body) {
              return of(auditPatientMonitoringForm.body);
            } else {
              this.router.navigate(["404"]);
              return EMPTY;
            }
          }
        )
      );
    }
    return of(new AuditPatientMonitoringForm());
  }
}
