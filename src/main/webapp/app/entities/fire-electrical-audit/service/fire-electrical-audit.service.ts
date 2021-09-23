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
  IFireElectricalAudit,
  getFireElectricalAuditIdentifier,
} from "../fire-electrical-audit.model";

export type EntityResponseType = HttpResponse<IFireElectricalAudit>;
export type EntityArrayResponseType = HttpResponse<IFireElectricalAudit[]>;

@Injectable({ providedIn: "root" })
export class FireElectricalAuditService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    "api/fire-electrical-audits"
  );

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(
    fireElectricalAudit: IFireElectricalAudit
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fireElectricalAudit);
    return this.http
      .post<IFireElectricalAudit>(this.resourceUrl, copy, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(
    fireElectricalAudit: IFireElectricalAudit
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fireElectricalAudit);
    return this.http
      .put<IFireElectricalAudit>(
        `${this.resourceUrl}/${
          getFireElectricalAuditIdentifier(fireElectricalAudit) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(
    fireElectricalAudit: IFireElectricalAudit
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fireElectricalAudit);
    return this.http
      .patch<IFireElectricalAudit>(
        `${this.resourceUrl}/${
          getFireElectricalAuditIdentifier(fireElectricalAudit) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFireElectricalAudit>(`${this.resourceUrl}/${id}`, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFireElectricalAudit[]>(this.resourceUrl, {
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

  addFireElectricalAuditToCollectionIfMissing(
    fireElectricalAuditCollection: IFireElectricalAudit[],
    ...fireElectricalAuditsToCheck: (IFireElectricalAudit | null | undefined)[]
  ): IFireElectricalAudit[] {
    const fireElectricalAudits: IFireElectricalAudit[] =
      fireElectricalAuditsToCheck.filter(isPresent);
    if (fireElectricalAudits.length > 0) {
      const fireElectricalAuditCollectionIdentifiers =
        fireElectricalAuditCollection.map(
          (fireElectricalAuditItem) =>
            getFireElectricalAuditIdentifier(fireElectricalAuditItem)!
        );
      const fireElectricalAuditsToAdd = fireElectricalAudits.filter(
        (fireElectricalAuditItem) => {
          const fireElectricalAuditIdentifier =
            getFireElectricalAuditIdentifier(fireElectricalAuditItem);
          if (
            fireElectricalAuditIdentifier == null ||
            fireElectricalAuditCollectionIdentifiers.includes(
              fireElectricalAuditIdentifier
            )
          ) {
            return false;
          }
          fireElectricalAuditCollectionIdentifiers.push(
            fireElectricalAuditIdentifier
          );
          return true;
        }
      );
      return [...fireElectricalAuditsToAdd, ...fireElectricalAuditCollection];
    }
    return fireElectricalAuditCollection;
  }

  protected convertDateFromClient(
    fireElectricalAudit: IFireElectricalAudit
  ): IFireElectricalAudit {
    return Object.assign({}, fireElectricalAudit, {
      fireAuditDate: fireElectricalAudit.fireAuditDate?.isValid()
        ? fireElectricalAudit.fireAuditDate.format(DATE_FORMAT)
        : undefined,
      electricalAuditDate: fireElectricalAudit.electricalAuditDate?.isValid()
        ? fireElectricalAudit.electricalAuditDate.format(DATE_FORMAT)
        : undefined,
      createdDate: fireElectricalAudit.createdDate?.isValid()
        ? fireElectricalAudit.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: fireElectricalAudit.lastModified?.isValid()
        ? fireElectricalAudit.lastModified.format(DATE_FORMAT)
        : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fireAuditDate = res.body.fireAuditDate
        ? dayjs(res.body.fireAuditDate)
        : undefined;
      res.body.electricalAuditDate = res.body.electricalAuditDate
        ? dayjs(res.body.electricalAuditDate)
        : undefined;
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
      res.body.forEach((fireElectricalAudit: IFireElectricalAudit) => {
        fireElectricalAudit.fireAuditDate = fireElectricalAudit.fireAuditDate
          ? dayjs(fireElectricalAudit.fireAuditDate)
          : undefined;
        fireElectricalAudit.electricalAuditDate =
          fireElectricalAudit.electricalAuditDate
            ? dayjs(fireElectricalAudit.electricalAuditDate)
            : undefined;
        fireElectricalAudit.createdDate = fireElectricalAudit.createdDate
          ? dayjs(fireElectricalAudit.createdDate)
          : undefined;
        fireElectricalAudit.lastModified = fireElectricalAudit.lastModified
          ? dayjs(fireElectricalAudit.lastModified)
          : undefined;
      });
    }
    return res;
  }
}
