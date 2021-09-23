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
  ITableDetails,
  getTableDetailsIdentifier,
} from "../table-details.model";

export type EntityResponseType = HttpResponse<ITableDetails>;
export type EntityArrayResponseType = HttpResponse<ITableDetails[]>;

@Injectable({ providedIn: "root" })
export class TableDetailsService {
  protected resourceUrl =
    this.applicationConfigService.getEndpointFor("api/table-details");

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(tableDetails: ITableDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tableDetails);
    return this.http
      .post<ITableDetails>(this.resourceUrl, copy, { observe: "response" })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tableDetails: ITableDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tableDetails);
    return this.http
      .put<ITableDetails>(
        `${this.resourceUrl}/${
          getTableDetailsIdentifier(tableDetails) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(tableDetails: ITableDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tableDetails);
    return this.http
      .patch<ITableDetails>(
        `${this.resourceUrl}/${
          getTableDetailsIdentifier(tableDetails) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITableDetails>(`${this.resourceUrl}/${id}`, { observe: "response" })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITableDetails[]>(this.resourceUrl, {
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

  addTableDetailsToCollectionIfMissing(
    tableDetailsCollection: ITableDetails[],
    ...tableDetailsToCheck: (ITableDetails | null | undefined)[]
  ): ITableDetails[] {
    const tableDetails: ITableDetails[] = tableDetailsToCheck.filter(isPresent);
    if (tableDetails.length > 0) {
      const tableDetailsCollectionIdentifiers = tableDetailsCollection.map(
        (tableDetailsItem) => getTableDetailsIdentifier(tableDetailsItem)!
      );
      const tableDetailsToAdd = tableDetails.filter((tableDetailsItem) => {
        const tableDetailsIdentifier =
          getTableDetailsIdentifier(tableDetailsItem);
        if (
          tableDetailsIdentifier == null ||
          tableDetailsCollectionIdentifiers.includes(tableDetailsIdentifier)
        ) {
          return false;
        }
        tableDetailsCollectionIdentifiers.push(tableDetailsIdentifier);
        return true;
      });
      return [...tableDetailsToAdd, ...tableDetailsCollection];
    }
    return tableDetailsCollection;
  }

  protected convertDateFromClient(tableDetails: ITableDetails): ITableDetails {
    return Object.assign({}, tableDetails, {
      createdDate: tableDetails.createdDate?.isValid()
        ? tableDetails.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: tableDetails.lastModified?.isValid()
        ? tableDetails.lastModified.format(DATE_FORMAT)
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
      res.body.forEach((tableDetails: ITableDetails) => {
        tableDetails.createdDate = tableDetails.createdDate
          ? dayjs(tableDetails.createdDate)
          : undefined;
        tableDetails.lastModified = tableDetails.lastModified
          ? dayjs(tableDetails.lastModified)
          : undefined;
      });
    }
    return res;
  }
}
