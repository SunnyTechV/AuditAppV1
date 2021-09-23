import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import * as dayjs from "dayjs";

import { isPresent } from "app/core/util/operators";
import { DATE_FORMAT } from "app/config/input.constants";
import { ApplicationConfigService } from "app/core/config/application-config.service";
import { createRequestOption } from "app/core/request/request-util";
import {
  IAnnexureAnswers,
  getAnnexureAnswersIdentifier,
} from "../annexure-answers.model";

export type EntityResponseType = HttpResponse<IAnnexureAnswers>;
export type EntityArrayResponseType = HttpResponse<IAnnexureAnswers[]>;

@Injectable({ providedIn: "root" })
export class AnnexureAnswersService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    "api/annexure-answers"
  );

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(annexureAnswers: IAnnexureAnswers): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(annexureAnswers);
    return this.http
      .post<IAnnexureAnswers>(this.resourceUrl, copy, { observe: "response" })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(annexureAnswers: IAnnexureAnswers): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(annexureAnswers);
    return this.http
      .put<IAnnexureAnswers>(
        `${this.resourceUrl}/${
          getAnnexureAnswersIdentifier(annexureAnswers) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(
    annexureAnswers: IAnnexureAnswers
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(annexureAnswers);
    return this.http
      .patch<IAnnexureAnswers>(
        `${this.resourceUrl}/${
          getAnnexureAnswersIdentifier(annexureAnswers) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAnnexureAnswers>(`${this.resourceUrl}/${id}`, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAnnexureAnswers[]>(this.resourceUrl, {
        params: options,
        observe: "response",
      })
      .pipe(
        map((res: EntityArrayResponseType) =>
          this.convertDateArrayFromServer(res)
        )
      );
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {
      observe: "response",
    });
  }

  addAnnexureAnswersToCollectionIfMissing(
    annexureAnswersCollection: IAnnexureAnswers[],
    ...annexureAnswersToCheck: (IAnnexureAnswers | null | undefined)[]
  ): IAnnexureAnswers[] {
    const annexureAnswers: IAnnexureAnswers[] =
      annexureAnswersToCheck.filter(isPresent);
    if (annexureAnswers.length > 0) {
      const annexureAnswersCollectionIdentifiers =
        annexureAnswersCollection.map(
          (annexureAnswersItem) =>
            getAnnexureAnswersIdentifier(annexureAnswersItem)!
        );
      const annexureAnswersToAdd = annexureAnswers.filter(
        (annexureAnswersItem) => {
          const annexureAnswersIdentifier =
            getAnnexureAnswersIdentifier(annexureAnswersItem);
          if (
            annexureAnswersIdentifier == null ||
            annexureAnswersCollectionIdentifiers.includes(
              annexureAnswersIdentifier
            )
          ) {
            return false;
          }
          annexureAnswersCollectionIdentifiers.push(annexureAnswersIdentifier);
          return true;
        }
      );
      return [...annexureAnswersToAdd, ...annexureAnswersCollection];
    }
    return annexureAnswersCollection;
  }

  protected convertDateFromClient(
    annexureAnswers: IAnnexureAnswers
  ): IAnnexureAnswers {
    return Object.assign({}, annexureAnswers, {
      createdDate: annexureAnswers.createdDate?.isValid()
        ? annexureAnswers.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: annexureAnswers.lastModified?.isValid()
        ? annexureAnswers.lastModified.format(DATE_FORMAT)
        : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate
        ? dayjs(res.body.createdDate)
        : undefined;
      res.body.lastModified = res.body.lastModified
        ? dayjs(res.body.lastModified)
        : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(
    res: EntityArrayResponseType
  ): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((annexureAnswers: IAnnexureAnswers) => {
        annexureAnswers.createdDate = annexureAnswers.createdDate
          ? dayjs(annexureAnswers.createdDate)
          : undefined;
        annexureAnswers.lastModified = annexureAnswers.lastModified
          ? dayjs(annexureAnswers.lastModified)
          : undefined;
      });
    }
    return res;
  }
}
