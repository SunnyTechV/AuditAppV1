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
  IInventoryReport,
  getInventoryReportIdentifier,
} from "../inventory-report.model";

export type EntityResponseType = HttpResponse<IInventoryReport>;
export type EntityArrayResponseType = HttpResponse<IInventoryReport[]>;

@Injectable({ providedIn: "root" })
export class InventoryReportService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    "api/inventory-reports"
  );

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(inventoryReport: IInventoryReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventoryReport);
    return this.http
      .post<IInventoryReport>(this.resourceUrl, copy, { observe: "response" })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(inventoryReport: IInventoryReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventoryReport);
    return this.http
      .put<IInventoryReport>(
        `${this.resourceUrl}/${
          getInventoryReportIdentifier(inventoryReport) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(
    inventoryReport: IInventoryReport
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventoryReport);
    return this.http
      .patch<IInventoryReport>(
        `${this.resourceUrl}/${
          getInventoryReportIdentifier(inventoryReport) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInventoryReport>(`${this.resourceUrl}/${id}`, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInventoryReport[]>(this.resourceUrl, {
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

  addInventoryReportToCollectionIfMissing(
    inventoryReportCollection: IInventoryReport[],
    ...inventoryReportsToCheck: (IInventoryReport | null | undefined)[]
  ): IInventoryReport[] {
    const inventoryReports: IInventoryReport[] =
      inventoryReportsToCheck.filter(isPresent);
    if (inventoryReports.length > 0) {
      const inventoryReportCollectionIdentifiers =
        inventoryReportCollection.map(
          (inventoryReportItem) =>
            getInventoryReportIdentifier(inventoryReportItem)!
        );
      const inventoryReportsToAdd = inventoryReports.filter(
        (inventoryReportItem) => {
          const inventoryReportIdentifier =
            getInventoryReportIdentifier(inventoryReportItem);
          if (
            inventoryReportIdentifier == null ||
            inventoryReportCollectionIdentifiers.includes(
              inventoryReportIdentifier
            )
          ) {
            return false;
          }
          inventoryReportCollectionIdentifiers.push(inventoryReportIdentifier);
          return true;
        }
      );
      return [...inventoryReportsToAdd, ...inventoryReportCollection];
    }
    return inventoryReportCollection;
  }

  protected convertDateFromClient(
    inventoryReport: IInventoryReport
  ): IInventoryReport {
    return Object.assign({}, inventoryReport, {
      createdDate: inventoryReport.createdDate?.isValid()
        ? inventoryReport.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: inventoryReport.lastModified?.isValid()
        ? inventoryReport.lastModified.format(DATE_FORMAT)
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
      res.body.forEach((inventoryReport: IInventoryReport) => {
        inventoryReport.createdDate = inventoryReport.createdDate
          ? dayjs(inventoryReport.createdDate)
          : undefined;
        inventoryReport.lastModified = inventoryReport.lastModified
          ? dayjs(inventoryReport.lastModified)
          : undefined;
      });
    }
    return res;
  }
}
