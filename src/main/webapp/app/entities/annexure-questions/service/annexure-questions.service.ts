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
  IAnnexureQuestions,
  getAnnexureQuestionsIdentifier,
} from "../annexure-questions.model";

export type EntityResponseType = HttpResponse<IAnnexureQuestions>;
export type EntityArrayResponseType = HttpResponse<IAnnexureQuestions[]>;

@Injectable({ providedIn: "root" })
export class AnnexureQuestionsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    "api/annexure-questions"
  );

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(
    annexureQuestions: IAnnexureQuestions
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(annexureQuestions);
    return this.http
      .post<IAnnexureQuestions>(this.resourceUrl, copy, { observe: "response" })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(
    annexureQuestions: IAnnexureQuestions
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(annexureQuestions);
    return this.http
      .put<IAnnexureQuestions>(
        `${this.resourceUrl}/${
          getAnnexureQuestionsIdentifier(annexureQuestions) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(
    annexureQuestions: IAnnexureQuestions
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(annexureQuestions);
    return this.http
      .patch<IAnnexureQuestions>(
        `${this.resourceUrl}/${
          getAnnexureQuestionsIdentifier(annexureQuestions) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAnnexureQuestions>(`${this.resourceUrl}/${id}`, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAnnexureQuestions[]>(this.resourceUrl, {
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

  addAnnexureQuestionsToCollectionIfMissing(
    annexureQuestionsCollection: IAnnexureQuestions[],
    ...annexureQuestionsToCheck: (IAnnexureQuestions | null | undefined)[]
  ): IAnnexureQuestions[] {
    const annexureQuestions: IAnnexureQuestions[] =
      annexureQuestionsToCheck.filter(isPresent);
    if (annexureQuestions.length > 0) {
      const annexureQuestionsCollectionIdentifiers =
        annexureQuestionsCollection.map(
          (annexureQuestionsItem) =>
            getAnnexureQuestionsIdentifier(annexureQuestionsItem)!
        );
      const annexureQuestionsToAdd = annexureQuestions.filter(
        (annexureQuestionsItem) => {
          const annexureQuestionsIdentifier = getAnnexureQuestionsIdentifier(
            annexureQuestionsItem
          );
          if (
            annexureQuestionsIdentifier == null ||
            annexureQuestionsCollectionIdentifiers.includes(
              annexureQuestionsIdentifier
            )
          ) {
            return false;
          }
          annexureQuestionsCollectionIdentifiers.push(
            annexureQuestionsIdentifier
          );
          return true;
        }
      );
      return [...annexureQuestionsToAdd, ...annexureQuestionsCollection];
    }
    return annexureQuestionsCollection;
  }

  protected convertDateFromClient(
    annexureQuestions: IAnnexureQuestions
  ): IAnnexureQuestions {
    return Object.assign({}, annexureQuestions, {
      createdDate: annexureQuestions.createdDate?.isValid()
        ? annexureQuestions.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: annexureQuestions.lastModified?.isValid()
        ? annexureQuestions.lastModified.format(DATE_FORMAT)
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
      res.body.forEach((annexureQuestions: IAnnexureQuestions) => {
        annexureQuestions.createdDate = annexureQuestions.createdDate
          ? dayjs(annexureQuestions.createdDate)
          : undefined;
        annexureQuestions.lastModified = annexureQuestions.lastModified
          ? dayjs(annexureQuestions.lastModified)
          : undefined;
      });
    }
    return res;
  }
}
