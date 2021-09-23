import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import { IInventoryReport, InventoryReport } from "../inventory-report.model";

import { InventoryReportService } from "./inventory-report.service";

describe("Service Tests", () => {
  describe("InventoryReport Service", () => {
    let service: InventoryReportService;
    let httpMock: HttpTestingController;
    let elemDefault: IInventoryReport;
    let expectedResult: IInventoryReport | IInventoryReport[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(InventoryReportService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        formName: "AAAAAAA",
        type: "AAAAAAA",
        subType: "AAAAAAA",
        tableName: "AAAAAAA",
        description: "AAAAAAA",
        descriptionParameter: "AAAAAAA",
        actualAvailable: "AAAAAAA",
        remark: "AAAAAAA",
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

      it("should create a InventoryReport", () => {
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
          .create(new InventoryReport())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a InventoryReport", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            description: "BBBBBB",
            descriptionParameter: "BBBBBB",
            actualAvailable: "BBBBBB",
            remark: "BBBBBB",
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

      it("should partial update a InventoryReport", () => {
        const patchObject = Object.assign(
          {
            formName: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            description: "BBBBBB",
            actualAvailable: "BBBBBB",
            remark: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
          },
          new InventoryReport()
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

      it("should return a list of InventoryReport", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            description: "BBBBBB",
            descriptionParameter: "BBBBBB",
            actualAvailable: "BBBBBB",
            remark: "BBBBBB",
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

      it("should delete a InventoryReport", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addInventoryReportToCollectionIfMissing", () => {
        it("should add a InventoryReport to an empty array", () => {
          const inventoryReport: IInventoryReport = { id: 123 };
          expectedResult = service.addInventoryReportToCollectionIfMissing(
            [],
            inventoryReport
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(inventoryReport);
        });

        it("should not add a InventoryReport to an array that contains it", () => {
          const inventoryReport: IInventoryReport = { id: 123 };
          const inventoryReportCollection: IInventoryReport[] = [
            {
              ...inventoryReport,
            },
            { id: 456 },
          ];
          expectedResult = service.addInventoryReportToCollectionIfMissing(
            inventoryReportCollection,
            inventoryReport
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a InventoryReport to an array that doesn't contain it", () => {
          const inventoryReport: IInventoryReport = { id: 123 };
          const inventoryReportCollection: IInventoryReport[] = [{ id: 456 }];
          expectedResult = service.addInventoryReportToCollectionIfMissing(
            inventoryReportCollection,
            inventoryReport
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(inventoryReport);
        });

        it("should add only unique InventoryReport to an array", () => {
          const inventoryReportArray: IInventoryReport[] = [
            { id: 123 },
            { id: 456 },
            { id: 68599 },
          ];
          const inventoryReportCollection: IInventoryReport[] = [{ id: 123 }];
          expectedResult = service.addInventoryReportToCollectionIfMissing(
            inventoryReportCollection,
            ...inventoryReportArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const inventoryReport: IInventoryReport = { id: 123 };
          const inventoryReport2: IInventoryReport = { id: 456 };
          expectedResult = service.addInventoryReportToCollectionIfMissing(
            [],
            inventoryReport,
            inventoryReport2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(inventoryReport);
          expect(expectedResult).toContain(inventoryReport2);
        });

        it("should accept null and undefined values", () => {
          const inventoryReport: IInventoryReport = { id: 123 };
          expectedResult = service.addInventoryReportToCollectionIfMissing(
            [],
            null,
            inventoryReport,
            undefined
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(inventoryReport);
        });

        it("should return initial array if no InventoryReport is added", () => {
          const inventoryReportCollection: IInventoryReport[] = [{ id: 123 }];
          expectedResult = service.addInventoryReportToCollectionIfMissing(
            inventoryReportCollection,
            undefined,
            null
          );
          expect(expectedResult).toEqual(inventoryReportCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
