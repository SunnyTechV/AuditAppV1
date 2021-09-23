import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import { IAnnexureAnswers, AnnexureAnswers } from "../annexure-answers.model";
import { AnnexureAnswersService } from "../service/annexure-answers.service";

@Injectable({ providedIn: "root" })
export class AnnexureAnswersRoutingResolveService
  implements Resolve<IAnnexureAnswers>
{
  constructor(
    protected service: AnnexureAnswersService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<IAnnexureAnswers> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((annexureAnswers: HttpResponse<AnnexureAnswers>) => {
          if (annexureAnswers.body) {
            return of(annexureAnswers.body);
          } else {
            this.router.navigate(["404"]);
            return EMPTY;
          }
        })
      );
    }
    return of(new AnnexureAnswers());
  }
}
