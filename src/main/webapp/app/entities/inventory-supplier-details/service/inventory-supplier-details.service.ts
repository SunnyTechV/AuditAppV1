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
  IInventorySupplierDetails,
  getInventorySupplierDetailsIdentifier,
} from "../inventory-supplier-details.model";

export type EntityResponseType = HttpResponse<IInventorySupplierDetails>;
export type EntityArrayResponseType = HttpResponse<IInventorySupplierDetails[]>;

@Injectable({ providedIn: "root" })
export class InventorySupplierDetailsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    "api/inventory-supplier-details"
  );

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(
    inventorySupplierDetails: IInventorySupplierDetails
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventorySupplierDetails);
    return this.http
      .post<IInventorySupplierDetails>(this.resourceUrl, copy, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(
    inventorySupplierDetails: IInventorySupplierDetails
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventorySupplierDetails);
    return this.http
      .put<IInventorySupplierDetails>(
        `${this.resourceUrl}/${
          getInventorySupplierDetailsIdentifier(
            inventorySupplierDetails
          ) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(
    inventorySupplierDetails: IInventorySupplierDetails
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(inventorySupplierDetails);
    return this.http
      .patch<IInventorySupplierDetails>(
        `${this.resourceUrl}/${
          getInventorySupplierDetailsIdentifier(
            inventorySupplierDetails
          ) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInventorySupplierDetails>(`${this.resourceUrl}/${id}`, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInventorySupplierDetails[]>(this.resourceUrl, {
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

  addInventorySupplierDetailsToCollectionIfMissing(
    inventorySupplierDetailsCollection: IInventorySupplierDetails[],
    ...inventorySupplierDetailsToCheck: (
      | IInventorySupplierDetails
      | null
      | undefined
    )[]
  ): IInventorySupplierDetails[] {
    const inventorySupplierDetails: IInventorySupplierDetails[] =
      inventorySupplierDetailsToCheck.filter(isPresent);
    if (inventorySupplierDetails.length > 0) {
      const inventorySupplierDetailsCollectionIdentifiers =
        inventorySupplierDetailsCollection.map(
          (inventorySupplierDetailsItem) =>
            getInventorySupplierDetailsIdentifier(inventorySupplierDetailsItem)!
        );
      const inventorySupplierDetailsToAdd = inventorySupplierDetails.filter(
        (inventorySupplierDetailsItem) => {
          const inventorySupplierDetailsIdentifier =
            getInventorySupplierDetailsIdentifier(inventorySupplierDetailsItem);
          if (
            inventorySupplierDetailsIdentifier == null ||
            inventorySupplierDetailsCollectionIdentifiers.includes(
              inventorySupplierDetailsIdentifier
            )
          ) {
            return false;
          }
          inventorySupplierDetailsCollectionIdentifiers.push(
            inventorySupplierDetailsIdentifier
          );
          return true;
        }
      );
      return [
        ...inventorySupplierDetailsToAdd,
        ...inventorySupplierDetailsCollection,
      ];
    }
    return inventorySupplierDetailsCollection;
  }

  protected convertDateFromClient(
    inventorySupplierDetails: IInventorySupplierDetails
  ): IInventorySupplierDetails {
    return Object.assign({}, inventorySupplierDetails, {
      createdDate: inventorySupplierDetails.createdDate?.isValid()
        ? inventorySupplierDetails.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: inventorySupplierDetails.lastModified?.isValid()
        ? inventorySupplierDetails.lastModified.format(DATE_FORMAT)
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
      res.body.forEach(
        (inventorySupplierDetails: IInventorySupplierDetails) => {
          inventorySupplierDetails.createdDate =
            inventorySupplierDetails.createdDate
              ? dayjs(inventorySupplierDetails.createdDate)
              : undefined;
          inventorySupplierDetails.lastModified =
            inventorySupplierDetails.lastModified
              ? dayjs(inventorySupplierDetails.lastModified)
              : undefined;
        }
      );
    }
    return res;
  }
}
