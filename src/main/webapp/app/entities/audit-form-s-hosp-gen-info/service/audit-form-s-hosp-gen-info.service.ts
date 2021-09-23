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
  IAuditFormSHospGenInfo,
  getAuditFormSHospGenInfoIdentifier,
} from "../audit-form-s-hosp-gen-info.model";

export type EntityResponseType = HttpResponse<IAuditFormSHospGenInfo>;
export type EntityArrayResponseType = HttpResponse<IAuditFormSHospGenInfo[]>;

@Injectable({ providedIn: "root" })
export class AuditFormSHospGenInfoService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    "api/audit-form-s-hosp-gen-infos"
  );

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(
    auditFormSHospGenInfo: IAuditFormSHospGenInfo
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditFormSHospGenInfo);
    return this.http
      .post<IAuditFormSHospGenInfo>(this.resourceUrl, copy, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(
    auditFormSHospGenInfo: IAuditFormSHospGenInfo
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditFormSHospGenInfo);
    return this.http
      .put<IAuditFormSHospGenInfo>(
        `${this.resourceUrl}/${
          getAuditFormSHospGenInfoIdentifier(auditFormSHospGenInfo) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(
    auditFormSHospGenInfo: IAuditFormSHospGenInfo
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditFormSHospGenInfo);
    return this.http
      .patch<IAuditFormSHospGenInfo>(
        `${this.resourceUrl}/${
          getAuditFormSHospGenInfoIdentifier(auditFormSHospGenInfo) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAuditFormSHospGenInfo>(`${this.resourceUrl}/${id}`, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAuditFormSHospGenInfo[]>(this.resourceUrl, {
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

  addAuditFormSHospGenInfoToCollectionIfMissing(
    auditFormSHospGenInfoCollection: IAuditFormSHospGenInfo[],
    ...auditFormSHospGenInfosToCheck: (
      | IAuditFormSHospGenInfo
      | null
      | undefined
    )[]
  ): IAuditFormSHospGenInfo[] {
    const auditFormSHospGenInfos: IAuditFormSHospGenInfo[] =
      auditFormSHospGenInfosToCheck.filter(isPresent);
    if (auditFormSHospGenInfos.length > 0) {
      const auditFormSHospGenInfoCollectionIdentifiers =
        auditFormSHospGenInfoCollection.map(
          (auditFormSHospGenInfoItem) =>
            getAuditFormSHospGenInfoIdentifier(auditFormSHospGenInfoItem)!
        );
      const auditFormSHospGenInfosToAdd = auditFormSHospGenInfos.filter(
        (auditFormSHospGenInfoItem) => {
          const auditFormSHospGenInfoIdentifier =
            getAuditFormSHospGenInfoIdentifier(auditFormSHospGenInfoItem);
          if (
            auditFormSHospGenInfoIdentifier == null ||
            auditFormSHospGenInfoCollectionIdentifiers.includes(
              auditFormSHospGenInfoIdentifier
            )
          ) {
            return false;
          }
          auditFormSHospGenInfoCollectionIdentifiers.push(
            auditFormSHospGenInfoIdentifier
          );
          return true;
        }
      );
      return [
        ...auditFormSHospGenInfosToAdd,
        ...auditFormSHospGenInfoCollection,
      ];
    }
    return auditFormSHospGenInfoCollection;
  }

  protected convertDateFromClient(
    auditFormSHospGenInfo: IAuditFormSHospGenInfo
  ): IAuditFormSHospGenInfo {
    return Object.assign({}, auditFormSHospGenInfo, {
      createdDate: auditFormSHospGenInfo.createdDate?.isValid()
        ? auditFormSHospGenInfo.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: auditFormSHospGenInfo.lastModified?.isValid()
        ? auditFormSHospGenInfo.lastModified.format(DATE_FORMAT)
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
      res.body.forEach((auditFormSHospGenInfo: IAuditFormSHospGenInfo) => {
        auditFormSHospGenInfo.createdDate = auditFormSHospGenInfo.createdDate
          ? dayjs(auditFormSHospGenInfo.createdDate)
          : undefined;
        auditFormSHospGenInfo.lastModified = auditFormSHospGenInfo.lastModified
          ? dayjs(auditFormSHospGenInfo.lastModified)
          : undefined;
      });
    }
    return res;
  }
}
