import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import * as dayjs from "dayjs";

import { isPresent } from "app/core/util/operators";
import { DATE_FORMAT } from "app/config/input.constants";
import { ApplicationConfigService } from "app/core/config/application-config.service";
import { createRequestOption } from "app/core/request/request-util";
import { IAudit, getAuditIdentifier } from "../audit.model";

export type EntityResponseType = HttpResponse<IAudit>;
export type EntityArrayResponseType = HttpResponse<IAudit[]>;

@Injectable({ providedIn: "root" })
export class AuditService {
  protected resourceUrl =
    this.applicationConfigService.getEndpointFor("api/audits");

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(audit: IAudit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(audit);
    return this.http
      .post<IAudit>(this.resourceUrl, copy, { observe: "response" })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(audit: IAudit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(audit);
    return this.http
      .put<IAudit>(
        `${this.resourceUrl}/${getAuditIdentifier(audit) as number}`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(audit: IAudit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(audit);
    return this.http
      .patch<IAudit>(
        `${this.resourceUrl}/${getAuditIdentifier(audit) as number}`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAudit>(`${this.resourceUrl}/${id}`, { observe: "response" })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAudit[]>(this.resourceUrl, { params: options, observe: "response" })
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

  addAuditToCollectionIfMissing(
    auditCollection: IAudit[],
    ...auditsToCheck: (IAudit | null | undefined)[]
  ): IAudit[] {
    const audits: IAudit[] = auditsToCheck.filter(isPresent);
    if (audits.length > 0) {
      const auditCollectionIdentifiers = auditCollection.map(
        (auditItem) => getAuditIdentifier(auditItem)!
      );
      const auditsToAdd = audits.filter((auditItem) => {
        const auditIdentifier = getAuditIdentifier(auditItem);
        if (
          auditIdentifier == null ||
          auditCollectionIdentifiers.includes(auditIdentifier)
        ) {
          return false;
        }
        auditCollectionIdentifiers.push(auditIdentifier);
        return true;
      });
      return [...auditsToAdd, ...auditCollection];
    }
    return auditCollection;
  }

  protected convertDateFromClient(audit: IAudit): IAudit {
    return Object.assign({}, audit, {
      auditDate: audit.auditDate?.isValid()
        ? audit.auditDate.format(DATE_FORMAT)
        : undefined,
      createdDate: audit.createdDate?.isValid()
        ? audit.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: audit.lastModified?.isValid()
        ? audit.lastModified.format(DATE_FORMAT)
        : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.auditDate = res.body.auditDate
        ? dayjs(res.body.auditDate)
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
      res.body.forEach((audit: IAudit) => {
        audit.auditDate = audit.auditDate ? dayjs(audit.auditDate) : undefined;
        audit.createdDate = audit.createdDate
          ? dayjs(audit.createdDate)
          : undefined;
        audit.lastModified = audit.lastModified
          ? dayjs(audit.lastModified)
          : undefined;
      });
    }
    return res;
  }
}
