import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import {
  IInventorySupplierDetails,
  InventorySupplierDetails,
} from "../inventory-supplier-details.model";

import { InventorySupplierDetailsService } from "./inventory-supplier-details.service";

describe("Service Tests", () => {
  describe("InventorySupplierDetails Service", () => {
    let service: InventorySupplierDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IInventorySupplierDetails;
    let expectedResult:
      | IInventorySupplierDetails
      | IInventorySupplierDetails[]
      | boolean
      | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(InventorySupplierDetailsService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        formName: "AAAAAAA",
        type: "AAAAAAA",
        subType: "AAAAAAA",
        tableName: "AAAAAAA",
        supplierName: "AAAAAAA",
        supplierAddress: "AAAAAAA",
        supplierContactName: "AAAAAAA",
        supplierContactNameNumber: "AAAAAAA",
        freeField1: "AAAAAAA",
        freeField2: "AAAAAAA",
        freeField3: "AAAAAAA",
        createdDate: currentDate,
        createdBy: "AAAAAAA",
        lastModified: currentDate,
        lastModifiedBy: "AAAAAAA",
      };
    });

    describe("Service methods", () => {
      it("should find an element", () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "GET" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it("should create a InventorySupplierDetails", () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .create(new InventorySupplierDetails())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a InventorySupplierDetails", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            supplierName: "BBBBBB",
            supplierAddress: "BBBBBB",
            supplierContactName: "BBBBBB",
            supplierContactNameNumber: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .update(expected)
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "PUT" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should partial update a InventorySupplierDetails", () => {
        const patchObject = Object.assign(
          {
            tableName: "BBBBBB",
            supplierContactName: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
          },
          new InventorySupplierDetails()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .partialUpdate(patchObject)
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "PATCH" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should return a list of InventorySupplierDetails", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            supplierName: "BBBBBB",
            supplierAddress: "BBBBBB",
            supplierContactName: "BBBBBB",
            supplierContactNameNumber: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "GET" });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it("should delete a InventorySupplierDetails", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addInventorySupplierDetailsToCollectionIfMissing", () => {
        it("should add a InventorySupplierDetails to an empty array", () => {
          const inventorySupplierDetails: IInventorySupplierDetails = {
            id: 123,
          };
          expectedResult =
            service.addInventorySupplierDetailsToCollectionIfMissing(
              [],
              inventorySupplierDetails
            );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(inventorySupplierDetails);
        });

        it("should not add a InventorySupplierDetails to an array that contains it", () => {
          const inventorySupplierDetails: IInventorySupplierDetails = {
            id: 123,
          };
          const inventorySupplierDetailsCollection: IInventorySupplierDetails[] =
            [
              {
                ...inventorySupplierDetails,
              },
              { id: 456 },
            ];
          expectedResult =
            service.addInventorySupplierDetailsToCollectionIfMissing(
              inventorySupplierDetailsCollection,
              inventorySupplierDetails
            );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a InventorySupplierDetails to an array that doesn't contain it", () => {
          const inventorySupplierDetails: IInventorySupplierDetails = {
            id: 123,
          };
          const inventorySupplierDetailsCollection: IInventorySupplierDetails[] =
            [{ id: 456 }];
          expectedResult =
            service.addInventorySupplierDetailsToCollectionIfMissing(
              inventorySupplierDetailsCollection,
              inventorySupplierDetails
            );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(inventorySupplierDetails);
        });

        it("should add only unique InventorySupplierDetails to an array", () => {
          const inventorySupplierDetailsArray: IInventorySupplierDetails[] = [
            { id: 123 },
            { id: 456 },
            { id: 51913 },
          ];
          const inventorySupplierDetailsCollection: IInventorySupplierDetails[] =
            [{ id: 123 }];
          expectedResult =
            service.addInventorySupplierDetailsToCollectionIfMissing(
              inventorySupplierDetailsCollection,
              ...inventorySupplierDetailsArray
            );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const inventorySupplierDetails: IInventorySupplierDetails = {
            id: 123,
          };
          const inventorySupplierDetails2: IInventorySupplierDetails = {
            id: 456,
          };
          expectedResult =
            service.addInventorySupplierDetailsToCollectionIfMissing(
              [],
              inventorySupplierDetails,
              inventorySupplierDetails2
            );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(inventorySupplierDetails);
          expect(expectedResult).toContain(inventorySupplierDetails2);
        });

        it("should accept null and undefined values", () => {
          const inventorySupplierDetails: IInventorySupplierDetails = {
            id: 123,
          };
          expectedResult =
            service.addInventorySupplierDetailsToCollectionIfMissing(
              [],
              null,
              inventorySupplierDetails,
              undefined
            );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(inventorySupplierDetails);
        });

        it("should return initial array if no InventorySupplierDetails is added", () => {
          const inventorySupplierDetailsCollection: IInventorySupplierDetails[] =
            [{ id: 123 }];
          expectedResult =
            service.addInventorySupplierDetailsToCollectionIfMissing(
              inventorySupplierDetailsCollection,
              undefined,
              null
            );
          expect(expectedResult).toEqual(inventorySupplierDetailsCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
