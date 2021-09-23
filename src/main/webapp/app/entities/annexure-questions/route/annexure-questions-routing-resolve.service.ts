import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { Resolve, ActivatedRouteSnapshot, Router } from "@angular/router";
import { Observable, of, EMPTY } from "rxjs";
import { mergeMap } from "rxjs/operators";

import {
  IAnnexureQuestions,
  AnnexureQuestions,
} from "../annexure-questions.model";
import { AnnexureQuestionsService } from "../service/annexure-questions.service";

@Injectable({ providedIn: "root" })
export class AnnexureQuestionsRoutingResolveService
  implements Resolve<IAnnexureQuestions>
{
  constructor(
    protected service: AnnexureQuestionsService,
    protected router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<IAnnexureQuestions> | Observable<never> {
    const id = route.params["id"];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((annexureQuestions: HttpResponse<AnnexureQuestions>) => {
          if (annexureQuestions.body) {
            return of(annexureQuestions.body);
          } else {
            this.router.navigate(["404"]);
            return EMPTY;
          }
        })
      );
    }
    return of(new AnnexureQuestions());
  }
}
