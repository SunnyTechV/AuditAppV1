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
  IOxygenConsumptionData,
  getOxygenConsumptionDataIdentifier,
} from "../oxygen-consumption-data.model";

export type EntityResponseType = HttpResponse<IOxygenConsumptionData>;
export type EntityArrayResponseType = HttpResponse<IOxygenConsumptionData[]>;

@Injectable({ providedIn: "root" })
export class OxygenConsumptionDataService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    "api/oxygen-consumption-data"
  );

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(
    oxygenConsumptionData: IOxygenConsumptionData
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oxygenConsumptionData);
    return this.http
      .post<IOxygenConsumptionData>(this.resourceUrl, copy, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(
    oxygenConsumptionData: IOxygenConsumptionData
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oxygenConsumptionData);
    return this.http
      .put<IOxygenConsumptionData>(
        `${this.resourceUrl}/${
          getOxygenConsumptionDataIdentifier(oxygenConsumptionData) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(
    oxygenConsumptionData: IOxygenConsumptionData
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oxygenConsumptionData);
    return this.http
      .patch<IOxygenConsumptionData>(
        `${this.resourceUrl}/${
          getOxygenConsumptionDataIdentifier(oxygenConsumptionData) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOxygenConsumptionData>(`${this.resourceUrl}/${id}`, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOxygenConsumptionData[]>(this.resourceUrl, {
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

  addOxygenConsumptionDataToCollectionIfMissing(
    oxygenConsumptionDataCollection: IOxygenConsumptionData[],
    ...oxygenConsumptionDataToCheck: (
      | IOxygenConsumptionData
      | null
      | undefined
    )[]
  ): IOxygenConsumptionData[] {
    const oxygenConsumptionData: IOxygenConsumptionData[] =
      oxygenConsumptionDataToCheck.filter(isPresent);
    if (oxygenConsumptionData.length > 0) {
      const oxygenConsumptionDataCollectionIdentifiers =
        oxygenConsumptionDataCollection.map(
          (oxygenConsumptionDataItem) =>
            getOxygenConsumptionDataIdentifier(oxygenConsumptionDataItem)!
        );
      const oxygenConsumptionDataToAdd = oxygenConsumptionData.filter(
        (oxygenConsumptionDataItem) => {
          const oxygenConsumptionDataIdentifier =
            getOxygenConsumptionDataIdentifier(oxygenConsumptionDataItem);
          if (
            oxygenConsumptionDataIdentifier == null ||
            oxygenConsumptionDataCollectionIdentifiers.includes(
              oxygenConsumptionDataIdentifier
            )
          ) {
            return false;
          }
          oxygenConsumptionDataCollectionIdentifiers.push(
            oxygenConsumptionDataIdentifier
          );
          return true;
        }
      );
      return [
        ...oxygenConsumptionDataToAdd,
        ...oxygenConsumptionDataCollection,
      ];
    }
    return oxygenConsumptionDataCollection;
  }

  protected convertDateFromClient(
    oxygenConsumptionData: IOxygenConsumptionData
  ): IOxygenConsumptionData {
    return Object.assign({}, oxygenConsumptionData, {
      createdDate: oxygenConsumptionData.createdDate?.isValid()
        ? oxygenConsumptionData.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: oxygenConsumptionData.lastModified?.isValid()
        ? oxygenConsumptionData.lastModified.format(DATE_FORMAT)
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
      res.body.forEach((oxygenConsumptionData: IOxygenConsumptionData) => {
        oxygenConsumptionData.createdDate = oxygenConsumptionData.createdDate
          ? dayjs(oxygenConsumptionData.createdDate)
          : undefined;
        oxygenConsumptionData.lastModified = oxygenConsumptionData.lastModified
          ? dayjs(oxygenConsumptionData.lastModified)
          : undefined;
      });
    }
    return res;
  }
}
